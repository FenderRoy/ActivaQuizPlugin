package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeQuestions;

import org.bukkit.ChatColor;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.QuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.sortFunction;

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
                    ServerCommunicator.sendConsoleMessage(QuestionManager.fileCreated("Type"));
                } else {
                    ServerCommunicator.sendConsoleMessage(QuestionManager.fileCreatedFailed("Type"));
                    return;
                }
            }

            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if(line.trim().isEmpty() || line.startsWith("#")) continue;
                typeWordList.add(new typeWord(line));
            }
            reader.close();

            typeWordListOriginal.addAll(typeWordList);

            sortFunction.sort(typeWordListOriginal);

            ServerCommunicator.sendConsoleMessage(QuestionManager.questionsLoaded("Type",typeWordListOriginal.size()));
        } catch (IOException e) {
            e.printStackTrace();
            ServerCommunicator.sendConsoleMessage(ChatColor.RED+QuestionManager.questionsLoadedFailed("Type"));
        }
    }

    private static void sort(List<typeWord> words){
        for(int i = 0; i<words.size()-1; i++) {
            for (int j = i+1; j<words.size(); j++) {
                if(words.get(i).toString().compareTo(words.get(j).toString())>0) {
                    typeWord temp = words.get(i);
                    words.set(i, words.get(j));
                    words.set(j, temp);
                }
            }
        }
    }

    private static Boolean fileExists(){
        File tempFile = new File(path);
        return tempFile.exists();
    }

    public static void initializeDefaultQuestionsInFile(File file){
        try {
            FileOutputStream out = new FileOutputStream(file);
            String txt = getDefaultTypeWords();

            for(Byte bit : txt.getBytes(Charset.defaultCharset())){
                out.write(bit);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDefaultTypeWords(){
        return "#Blank lines and lines that start with # are ignored.\n" +
                "#To add a new word, start a new line and enter the word.\n" +
                "#Space sensitive, dont add spaces at the end of a word.\n" +
                "qwerty\n" +
                "pogchamp\n" +
                "iamafasttyper\n" +
                "shinyquizes\n" +
                "abcdefghijklmnopqrstuvwxyz\n" +
                "ababababababababa\n" +
                "1029384756\n" +
                "asdfghjkl;\n" +
                "The Alphabet\n" +
                "Cobblestone\n" +
                "Granite\n" +
                "Diorite\n" +
                "Andesite\n" +
                "Cobbled Deepslate\n" +
                "Polished Deepslate\n" +
                "Bedrock\n" +
                "Block of Amethyst\n" +
                "Waxed Cut Copper\n" +
                "Waxed Exposed Cut Copper\n" +
                "Waxed Oxidized Cut Copper\n" +
                "Muddy Mangrove Roots\n" +
                "Waxed Oxidized Cut Copper Stairs\n" +
                "Block of Diamond\n" +
                "Stripped Warped Stem\n" +
                "Warped Hyphae\n" +
                "Prismarine\n" +
                "Mossy Cobblestone\n" +
                "Obsidian\n" +
                "Bookshelf\n" +
                "Purpur\n" +
                "Jack o'Lantern\n" +
                "Bottle o' Enchanting\n" +
                "Chiseled Nether Bricks\n" +
                "Aesthetics\n" +
                "Ambient\n" +
                "Flammable\n" +
                "Prestigious\n" +
                "Encyclopedia\n" +
                "Blockopedia\n" +
                "Specification\n" +
                "Provacative\n" +
                "Archaic\n" +
                "Inaccessible\n" +
                "Inventory\n" +
                "Pizzas\n" +
                "Suburban\n" +
                "Dark Oak Boat with Chest\n" +
                "Redstone Comparator\n" +
                "Chorus Flower\n" +
                "Weeping Vines\n" +
                "Acacia Fence\n" +
                "Infested Deepslate\n" +
                "Sculk Shrieker\n" +
                "Sculk Catalyst\n" +
                "Enchanting Table\n" +
                "Painting\n" +
                "Scaffolding\n" +
                "Ender Dragon\n" +
                "Armor Stand\n" +
                "IAmACoolPerson\n" +
                "End Crystal\n" +
                "Lodestone\n" +
                "Respawn Anchor\n" +
                "Shroomlight\n" +
                "Bee Nest\n" +
                "Smithing Table\n" +
                "Composter\n" +
                "Grindstone\n" +
                "Blaze Powder\n" +
                "Azure Bluet\n" +
                "Azalea\n" +
                "Flowering Azalea\n" +
                "Netherite Chestplate\n" +
                "Blast Protection IV\n" +
                "Riptide III\n" +
                "Flame\n" +
                "Quick Charge III\n" +
                "Aqua Affinity\n" +
                "Respiration III\n" +
                "Projectile Protection IV\n" +
                "Impaling V\n" +
                "Unbreaking III\n" +
                "Sweeping Edge III\n" +
                "Channeling\n" +
                "Bane of Arthropods V\n" +
                "Potion of the Turtle Master\n" +
                "Lingering Potion\n" +
                "Redstone Lamp\n" +
                "Enchanted Golden Apple\n" +
                "Pufferfish\n" +
                "Rotten Flesh\n" +
                "Pumpkin Pie\n" +
                "Wither Rose\n" +
                "Amethyst Shard\n" +
                "Bucket of Axolotl\n" +
                "White Dye\n" +
                "Orange Dye\n" +
                "Magenta Dye\n" +
                "Light Blue Dye\n" +
                "Yellow Dye\n" +
                "Lime Dye\n" +
                "Pink Dye\n" +
                "Gray Dye\n" +
                "Light Gray Dye\n" +
                "Cyan Dye\n" +
                "Purple Dye\n" +
                "Blue Dye\n" +
                "Brown Dye\n" +
                "Green Dye\n" +
                "Red Dye\n" +
                "Black Dye\n" +
                "Egg\n" +
                "Eye of Ender\n" +
                "Heart of the Sea\n" +
                "Diamond Horse Armor\n" +
                "Gold Horse Armor\n" +
                "Leather Horse Armor\n" +
                "Iron Horse Armor\n" +
                "Prismarine Crystals\n" +
                "Prismarine Shard\n" +
                "Firework Star";
    }

}
