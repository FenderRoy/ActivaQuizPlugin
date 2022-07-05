package shinyquizesplugin.shinyquizesplugin.Quiz;

import org.bukkit.ChatColor;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.CustomQuestion;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class CustomQuestionsManager {


    private static String path;

    private static final List<CustomQuestion> customQuestionList = new ArrayList<>();
    private static final List<CustomQuestion> customQuestionListOriginal = new ArrayList<>();

    public static void getCustomQuestionsFromFile(){

        path = PLUGIN.getDataFolder().getAbsolutePath()+ "/CustomQuestions.txt";
        customQuestionList.clear();
        customQuestionListOriginal.clear();

        try {

            if(!fileExists()){
                File questionFile = new File(path);
                if(questionFile.createNewFile()){
                    initializeDefaultQuestionsInFile(questionFile);
                    ServerCommunicator.sendConsoleMessage("Custom questions file aangemaakt.");
                } else {
                    ServerCommunicator.sendConsoleMessage("Custom questions file niet kunnen aanmaken.");
                    return;
                }
            }

            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                createQuestion(dissectLine(line));
            }
            reader.close();

            customQuestionList.addAll(customQuestionListOriginal);


            ServerCommunicator.sendConsoleMessage(ChatColor.GREEN+"Custom vragen ingeladen. Totaal van: "+customQuestionListOriginal.size());
        } catch (IOException e) {
            e.printStackTrace();
            ServerCommunicator.sendConsoleMessage(ChatColor.RED+"[ERROR] Inladen van custom vragen is fout gegaan.");
        }
    }

    private static Boolean fileExists(){
        File tempFile = new File(path);
        return tempFile.exists();
    }

    private static String[] dissectLine(String line){
        return line.split(" -/- ", 2);
    }

    private static void createQuestion(String[] args){
        if(args[0].startsWith("#")) return;
        customQuestionListOriginal.add(new CustomQuestion(args[0],args[1]));
    }

    public static List<CustomQuestion> getCustomQuestionList() {
        return Collections.unmodifiableList(customQuestionListOriginal);
    }

    public static Question getRandomQuestion(){

        if(customQuestionList.size() <= 0){
            customQuestionList.addAll(customQuestionListOriginal);
        }

        int index = (int)(Math.random()*customQuestionList.size());
        Question question = customQuestionList.get(index);
        customQuestionList.remove(index);
        return question;

    }

    public static void initializeDefaultQuestionsInFile(File file){
        try {
            FileOutputStream out = new FileOutputStream(file);
            String txt = "# Use '#' for comments.\n" +
                    "# Type out the question, write \" -/- \" after (SPACE SENSITIVE) and then the answer.\n" +
                    "# example: Under what names is the developer known? -/- Fender and Pogasuna\n" +
                    "What comes after G in the alphabet? -/- H\n" +
                    "What comes after Q in the alphabet? -/- R\n" +
                    "Who made this plugin? -/- Fender\n" +
                    "What is the default screenshot button. -/- F2";

            for(Byte bit : txt.getBytes(Charset.defaultCharset())){
                out.write(bit);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
