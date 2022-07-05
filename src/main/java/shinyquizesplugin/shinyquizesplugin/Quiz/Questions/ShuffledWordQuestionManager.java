package shinyquizesplugin.shinyquizesplugin.Quiz.Questions;

import org.bukkit.ChatColor;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class ShuffledWordQuestionManager {

    public static List<shuffledWord> shuffledWordList = new ArrayList<>();
    public static List<shuffledWord> shuffledWordListOriginal = new ArrayList<>();
    private static String path;

    public static Question getRandomQuestion(){
        if(shuffledWordList.size() <= 0){
            shuffledWordList.addAll(shuffledWordListOriginal);
        }

        int index = (int)(Math.random()*shuffledWordList.size());
        Question question = shuffledWordList.get(index);
        shuffledWordList.remove(index);
        return question;
    }
    public static void initialize(){
        path = PLUGIN.getDataFolder().getAbsolutePath()+ "/ShuffledWords.txt";
        shuffledWordList.clear();
        shuffledWordListOriginal.clear();

        try {

            if (!fileExists()) {
                File questionFile = new File(path);
                if (questionFile.createNewFile()) {
                    initializeDefaultQuestionsInFile(questionFile);
                    ServerCommunicator.sendConsoleMessage("Shuffled words file aangemaakt.");
                } else {
                    ServerCommunicator.sendConsoleMessage("Shuffled words file niet kunnen aanmaken.");
                    return;
                }
            }

            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                shuffledWordList.add(new shuffledWord(line));
            }
            reader.close();

            shuffledWordListOriginal.addAll(shuffledWordList);

            ServerCommunicator.sendConsoleMessage(ChatColor.GREEN+"Shuffled Words ingeladen. Totaal van: "+shuffledWordList.size());
        } catch (IOException e) {
            e.printStackTrace();
            ServerCommunicator.sendConsoleMessage(ChatColor.RED+"[ERROR] Inladen van Shuffled words is fout gegaan.");
        }
    }

    private static Boolean fileExists(){
        File tempFile = new File(path);
        return tempFile.exists();
    }

    public static void initializeDefaultQuestionsInFile(File file){
        try {
            FileOutputStream out = new FileOutputStream(file);
            String txt = "Dandelion\n" +
                    "Poppy\n" +
                    "Blue orchid\n" +
                    "Allium\n" +
                    "Azure bluet\n" +
                    "Tulip\n" +
                    "Oxeye daisy\n" +
                    "Cornflower\n" +
                    "Lily of the Valley\n" +
                    "Wither rose\n" +
                    "Sunflower\n" +
                    "Lilac\n" +
                    "Rose bush\n" +
                    "Peony";

            for(Byte bit : txt.getBytes(Charset.defaultCharset())){
                out.write(bit);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
