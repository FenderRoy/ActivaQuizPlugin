package shinyquizesplugin.shinyquizesplugin.Quiz.Questions;

import org.bukkit.ChatColor;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class TypeWordQuestionManager {

    public static List<typeWord> typeWordList = new ArrayList<>();
    public static List<typeWord> typeWordListOriginal = new ArrayList<>();
    private static String path;

    public static Question getRandomQuestion(){
        if(typeWordList.size() <= 0){
            typeWordList.addAll(typeWordListOriginal);
        }

        int index = (int)(Math.random()* typeWordList.size());
        Question question = typeWordList.get(index);
        typeWordList.remove(index);
        return question;
    }
    public static void initialize(){
        path = PLUGIN.getDataFolder().getAbsolutePath()+ "/questions/TypeWords.txt";
        typeWordList.clear();
        typeWordListOriginal.clear();

        try {

            if (!fileExists()) {
                File questionFile = new File(path);
                if (questionFile.createNewFile()) {
                    initializeDefaultQuestionsInFile(questionFile);
                    ServerCommunicator.sendConsoleMessage("Typed words file aangemaakt.");
                } else {
                    ServerCommunicator.sendConsoleMessage("Typed words file niet kunnen aanmaken.");
                    return;
                }
            }

            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                typeWordList.add(new typeWord(line));
            }
            reader.close();

            typeWordListOriginal.addAll(typeWordList);

            ServerCommunicator.sendConsoleMessage(ChatColor.GREEN+"Typed Words ingeladen. Totaal van: "+ typeWordList.size());
        } catch (IOException e) {
            e.printStackTrace();
            ServerCommunicator.sendConsoleMessage(ChatColor.RED+"[ERROR] Inladen van Typed words is fout gegaan.");
        }
    }

    private static Boolean fileExists(){
        File tempFile = new File(path);
        return tempFile.exists();
    }

    public static void initializeDefaultQuestionsInFile(File file){
        try {
            FileOutputStream out = new FileOutputStream(file);
            String txt = "qwerty\n" +
                    "pogchamp\n" +
                    "iamafasttyper\n" +
                    "shinyquizes\n" +
                    "abcdefghijklmnopqrstuvwxyz\n" +
                    "ababababababababa\n" +
                    "1029384756\n" +
                    "asdfghjkl;";

            for(Byte bit : txt.getBytes(Charset.defaultCharset())){
                out.write(bit);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
