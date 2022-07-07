package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions;

import org.bukkit.ChatColor;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.QuestionManager;

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
        path = PLUGIN.getDataFolder().getAbsolutePath()+ "/questions/ShuffledWords.txt";
        shuffledWordList.clear();
        shuffledWordListOriginal.clear();

        try {

            if (!fileExists()) {
                File questionFile = new File(path);
                if (questionFile.createNewFile()) {
                    initializeDefaultQuestionsInFile(questionFile);
                    ServerCommunicator.sendConsoleMessage(QuestionManager.fileCreated("Shuffled"));
                } else {
                    ServerCommunicator.sendConsoleMessage(QuestionManager.fileCreatedFailed("Shuffled"));
                    return;
                }
            }

            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if(line.trim().isEmpty() || line.startsWith("#")) continue;
                shuffledWordList.add(new shuffledWord(line));
            }
            reader.close();

            shuffledWordListOriginal.addAll(shuffledWordList);

            ServerCommunicator.sendConsoleMessage(QuestionManager.questionsLoaded("Shuffled",shuffledWordListOriginal.size()));
        } catch (IOException e) {
            e.printStackTrace();
            ServerCommunicator.sendConsoleMessage(ChatColor.RED+QuestionManager.questionsLoadedFailed("Shuffled"));
        }
    }

    private static Boolean fileExists(){
        File tempFile = new File(path);
        return tempFile.exists();
    }

    public static void initializeDefaultQuestionsInFile(File file){
        try {
            FileOutputStream out = new FileOutputStream(file);
            String txt = getShuffledWords();

            for(Byte bit : txt.getBytes(Charset.defaultCharset())){
                out.write(bit);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getShuffledWords(){
        return "#Blank lines and lines that start with # are ignored.\n" +
                "#To add a new word, start a new line and enter the word.\n" +
                "#Space sensitive, dont add spaces at the end of a word.\n" +
                "Dandelion\n" +
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
                "Peony\n" +
                "Creeper\n" +
                "Zombie\n" +
                "Zombiepigman\n" +
                "Enderman\n" +
                "Allay\n" +
                "Axolotl\n" +
                "Bat\n" +
                "Bee\n" +
                "Blaze\n" +
                "Cat\n" +
                "Cave Spider\n" +
                "Chicken\n" +
                "Cod\n" +
                "Cow\n" +
                "Dolphin\n" +
                "Donkey\n" +
                "Drowned\n" +
                "Elder Guardian\n" +
                "Guardian\n" +
                "Endermite\n" +
                "Evoker\n" +
                "Fox\n" +
                "Frog\n" +
                "Ghast\n" +
                "Glow Squid\n" +
                "Goat\n" +
                "Hoglin\n" +
                "Horse\n" +
                "Husk\n" +
                "Llama\n" +
                "Magma Cube\n" +
                "Mooshroom\n" +
                "Mule\n" +
                "Ocelot\n" +
                "Panda\n" +
                "Parrot\n" +
                "Phantom\n" +
                "Pig\n" +
                "Piglin\n" +
                "Piglin\n" +
                "Pillager\n" +
                "Polar Bear\n" +
                "Pufferfish\n" +
                "Rabbit\n" +
                "Ravager\n" +
                "Salmon\n" +
                "Sheep\n" +
                "Shulker\n" +
                "Silverfish\n" +
                "Skeleton\n" +
                "Slime\n" +
                "Spider\n" +
                "Squid\n" +
                "Stray\n" +
                "Strider\n" +
                "Tadpole\n" +
                "Tropical Fish\n" +
                "Turtle\n" +
                "Vex\n" +
                "Villager\n" +
                "Vindicator\n" +
                "Warden\n" +
                "Witch\n" +
                "Wither Skeleton\n" +
                "Wolf\n" +
                "Zoglin\n" +
                "Music Disc\n" +
                "Heart of the Sea\n" +
                "Nautilus Shell\n" +
                "Goat Horn\n" +
                "Honeycomb\n" +
                "Honey\n" +
                "Prismarine\n" +
                "Nether Brick\n" +
                "Firework\n" +
                "Nether Star\n" +
                "Beacon\n" +
                "Fire Charge\n" +
                "Bottle o' Enchanting\n" +
                "Diamond\n" +
                "Emerald\n" +
                "Lapis Lazuli\n" +
                "Nether Quartz\n" +
                "Amethyst\n" +
                "Iron Ingot\n" +
                "Gold Ingot\n" +
                "Copper Ingot\n" +
                "Netherite Ingot\n" +
                "Bowl\n" +
                "Gunpowder\n" +
                "Feather\n" +
                "Wheat\n" +
                "Snowball\n" +
                "Brick\n" +
                "Clay Ball\n" +
                "Slimeball\n" +
                "Bone\n" +
                "Bone Meal\n" +
                "Blaze Rod\n" +
                "Eye of Ender\n" +
                "Ender Pearl\n" +
                "Redstone Dust\n" +
                "Observer\n" +
                "Piston\n" +
                "Sticky Piston\n" +
                "Slime Block\n" +
                "Honey Block\n" +
                "Pressure Plate\n" +
                "Chest\n" +
                "Crafting Table\n" +
                "Furnace\n" +
                "Ladder\n" +
                "Cactus\n" +
                "Jukebox\n" +
                "Note Block\n" +
                "Anvil\n" +
                "Carpet\n" +
                "Scaffolding\n" +
                "Painting\n" +
                "Item Frame\n" +
                "Armor Stand\n" +
                "End Crystal\n" +
                "Shroomlight\n" +
                "Apple\n" +
                "Bread\n" +
                "Golden Apple\n" +
                "Steak\n" +
                "Cooked Chicken\n" +
                "Cooked Rabbit\n" +
                "Carrot\n" +
                "Beetroot\n" +
                "Raw Beef\n" +
                "Cooked Porkchop\n" +
                "Sweet Berries\n" +
                "Glow Berries\n" +
                "Lectern\n" +
                "Dispenser\n" +
                "Dropper\n" +
                "Elytra\n" +
                "Crossbow\n" +
                "Shield\n" +
                "Trident\n" +
                "Totem of Undying\n" +
                "Compass\n" +
                "Clock\n" +
                "Spyglass\n" +
                "Shears\n" +
                "Stone\n" +
                "Cobblestone\n" +
                "Sand\n" +
                "Gravel\n" +
                "Bedrock\n" +
                "Sandstone\n" +
                "Sponge\n" +
                "Bookshelf\n" +
                "Pumpkin\n" +
                "Netherrack\n" +
                "Basalt\n" +
                "Stone Bricks\n" +
                "Melon\n" +
                "End Stone\n" +
                "Mycelium\n" +
                "Sea Lantern\n" +
                "Concrete\n" +
                "Coral\n" +
                "Blackstone\n" +
                "Deepslate\n" +
                "Obsidian\n" +
                "Calcite\n" +
                "Dripstone\n" +
                "Dirt\n" +
                "Podzol\n" +
                "Mud\n" +
                "Coal\n" +
                "Granite\n" +
                "Diorite\n" +
                "Andesite\n";
    }

}
