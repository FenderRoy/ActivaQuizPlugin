package shinyquizesplugin.shinyquizesplugin.rewards;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class RewardManager {


    private static String path;

    public static final List<Reward> rewardList = new ArrayList<>();

    public static Reward getRandomReward() {
        int index = (int)(Math.random() * rewardList.size());
        return copyOfReward(rewardList.get(index));
    }

    public static void initializeRewards() {
        path = PLUGIN.getDataFolder().getAbsolutePath() + "/rewards/Rewards.txt";
        rewardList.clear();

        try {

            if (!fileExists()) {
                File questionFile = new File(path);
                if (questionFile.createNewFile()) {
                    initializeDefaultRewardsInFile(questionFile);
                    ServerCommunicator.sendConsoleMessage("Custom rewards file created.");
                } else {
                    ServerCommunicator.sendConsoleMessage("Custom rewards file couldn't be created.");
                    return;
                }
            }

            FileReader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;


            List<Material> mats = new ArrayList<>();
            Reward reward = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("-")) {
                    if (reward != null) rewardList.add(copyOfReward(reward));
                    reward = new Reward();
                } else {
                    String formattedLine = line.replaceAll("-", "");
                    String[] str = dissectLine(formattedLine);
                    Material mat = Material.getMaterial(str[0].toUpperCase(Locale.ROOT));
                    ItemStack stack = new ItemStack(mat, Integer.parseInt(str[1]));
                    reward.addItemStack(stack);
                }
            }
            rewardList.add(copyOfReward(reward));
            reader.close();

            ServerCommunicator.sendConsoleMessage(ChatColor.GREEN + "Rewards loaded. Total of: " + rewardList.size() + " rewards.");
        } catch (IOException e) {
            e.printStackTrace();
            ServerCommunicator.sendConsoleMessage(ChatColor.RED + "[ERROR] loading of rewards failed.");
        }

    }

    public static Reward copyOfReward(Reward reward){
        Reward newReward = new Reward();

        for(ItemStack material : reward.getRewards()){
            newReward.addItemStack(new ItemStack(material));
        }

        return newReward;
    }

    private static String[] dissectLine(String line){
        return line.split(" ", 2);
    }

    private static Boolean fileExists(){
        File tempFile = new File(path);
        return tempFile.exists();
    }

    public static void initializeDefaultRewardsInFile(File file){
        try {
            FileOutputStream out = new FileOutputStream(file);
            String txt = "RewardOne:\n" +
                    "-diamond_sword 1\n" +
                    "-diamond_block 10\n" +
                    "-diamond 30\n" +
                    "RewardTwo:\n" +
                    "-iron_ingot 64\n" +
                    "-light_blue_stained_glass 2\n" +
                    "RewardThree:\n" +
                    "-quartz_block 300";

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
