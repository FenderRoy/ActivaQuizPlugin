package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.AcronymQuestion;

import org.bukkit.ChatColor;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.CustomQuestions.CustomQuestion;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.QuestionManager;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class AcronymQuestionsManager {


    private static String path;

    private static final List<AcronymQuestion> AcronymQuestionList = new ArrayList<>();
    private static final List<AcronymQuestion> AcronymQuestionListOriginal = new ArrayList<>();

    public static void getCustomQuestionsFromFile(){

        path = PLUGIN.getDataFolder().getAbsolutePath()+ "/questions/AcronymQuestions.txt";
        AcronymQuestionList.clear();
        AcronymQuestionListOriginal.clear();

        try {

            if(!fileExists()){
                File questionFile = new File(path);
                if(questionFile.createNewFile()){
                    initializeDefaultQuestionsInFile(questionFile);
                    ServerCommunicator.sendConsoleMessage(QuestionManager.fileCreated("Acronym"));
                } else {
                    ServerCommunicator.sendConsoleMessage(QuestionManager.fileCreatedFailed("Acronym"));
                    return;
                }
            }

            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if(line.trim().isEmpty() || line.startsWith("#")) continue;
                createQuestion(dissectLine(line));
            }
            reader.close();

            AcronymQuestionList.addAll(AcronymQuestionListOriginal);


            ServerCommunicator.sendConsoleMessage(QuestionManager.questionsLoaded("Acronym",AcronymQuestionListOriginal.size()));
        } catch (IOException e) {
            e.printStackTrace();
            ServerCommunicator.sendConsoleMessage(ChatColor.RED+QuestionManager.questionsLoadedFailed("Acronym"));
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
        AcronymQuestionListOriginal.add(new AcronymQuestion(args[0],args[1]));
    }

    public static List<AcronymQuestion> getCustomQuestionList() {
        return Collections.unmodifiableList(AcronymQuestionListOriginal);
    }

    public static Question getRandomQuestion(){

        if(AcronymQuestionList.size() <= 0){
            AcronymQuestionList.addAll(AcronymQuestionListOriginal);
        }

        int index = (int)(Math.random()*AcronymQuestionList.size());
        Question question = AcronymQuestionList.get(index);
        AcronymQuestionList.remove(index);
        return question;
    }

    public static void initializeDefaultQuestionsInFile(File file){
        try {
            FileOutputStream out = new FileOutputStream(file);
            String txt = getDefaultAcronyms();

            for(Byte bit : txt.getBytes(Charset.defaultCharset())){
                out.write(bit);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getDefaultAcronyms(){
        return "#Blank lines and lines that start with # are ignored.\n" +
                "# To add a new acronym, type out the acronym\n" +
                "# followed by \" -/- \" *[SPACE SENSITIVE]*\n" +
                "# followed by the answer.\n" +
                "WB -/- Welcome Back\n" +
                "WYD -/- What you doing\n" +
                "LMAO -/- Laughin my ass off\n" +
                "LOL -/- Laugh out Loud\n" +
                "OMG -/- oh my god\n" +
                "GG -/- Good Game\n" +
                "EZ -/- easy\n" +
                "GGEZ -/- Good Game easy\n" +
                "LMK -/- let me know\n" +
                "TBH -/- to be honest\n" +
                "IDK -/- I dont know\n" +
                "IDC -/- I dont care\n" +
                "TLDR -/- too long didn't read\n" +
                "NVM -/- nevermind\n" +
                "TBF -/- to be fair\n" +
                "BRB -/- be right back\n" +
                "GTG -/- got to go\n" +
                "TTYL -/- talk to you later\n" +
                "IMO -/- in my opinion\n" +
                "IMHO -/- in my honest opinion\n" +
                "AKA -/- also known as\n" +
                "FAQ -/- frequenly asked questions\n" +
                "DIY -/- do it yourself\n" +
                "ASAP -/- as soon as possible\n" +
                "ILY -/- i love you\n" +
                "MC -/- Minecraft";
    }
}
