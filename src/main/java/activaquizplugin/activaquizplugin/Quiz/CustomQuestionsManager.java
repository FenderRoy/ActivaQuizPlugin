package activaquizplugin.activaquizplugin.Quiz;

import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.Quiz.Questions.CustomQuestion;
import activaquizplugin.activaquizplugin.Quiz.Questions.Question;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static activaquizplugin.activaquizplugin.ActivaQuizPlugin.PLUGIN;

public class CustomQuestionsManager {


    private static String path;

    private static final List<CustomQuestion> customQuestionList = new ArrayList<>();
    private static final List<CustomQuestion> customQuestionListOriginal = new ArrayList<>();

    public static void getCustomQuestionsFromFile(){

        path = PLUGIN.getDataFolder().getAbsolutePath()+"/customQuestions.txt";
        customQuestionList.clear();
        customQuestionListOriginal.clear();

        try {

            if(!fileExists()){
                File questionFile = new File(path);
                if(questionFile.createNewFile()){
                    ServerCommunicator.sendConsoleMessage("Custom questions file aangemaakt.");
                } else {
                    ServerCommunicator.sendConsoleMessage("Custom questions file niet kunnen aanmaken.");
                }
                return;
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
        String temp = line.replaceAll(" -/- ", "\n");
        return line.split(" -/- ", 2);
    }

    private static void createQuestion(String[] args){
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
}
