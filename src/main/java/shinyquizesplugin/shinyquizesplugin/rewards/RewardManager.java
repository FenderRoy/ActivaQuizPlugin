package shinyquizesplugin.shinyquizesplugin.rewards;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.rewards.rewardType.*;

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

            Reward reward = null;
            boolean rewardCancelled = false;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("-")) {
                    if(line.trim().isEmpty() || line.startsWith("#")) continue;
                    if (reward != null && !rewardCancelled) rewardList.add(copyOfReward(reward));

                    reward = new Reward();
                    rewardCancelled = false;
                } else {
                    String formattedLine = line.substring(1);

                    if(formattedLine.toLowerCase().startsWith("money ")){
                        String[] str = dissectLine(formattedLine);
                        reward.rewards.add(new MoneyReward(Double.parseDouble(str[1])));
                        continue;
                    }
                    if(formattedLine.toLowerCase().startsWith("custom ")){
                        reward.rewards.add(new CustomCommandReward(formattedLine.substring(7)));
                        continue;
                    }
                    if(formattedLine.toLowerCase().startsWith("say ")){
                        reward.rewards.add(new MessageToPlayerReward(formattedLine.substring(4)));
                        continue;
                    }

                    try {
                        String[] str = dissectLine(formattedLine);
                        Material mat = Material.getMaterial(str[0].toUpperCase(Locale.ROOT));
                        ItemStack stack = new ItemStack(mat, Integer.parseInt(str[1]));
                        reward.addItemStack(stack);
                    } catch (Exception e){
                        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"Error loading reward: ["+formattedLine+"]");
                        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"This reward is disabled. please fix any syntax errors.");
                        rewardCancelled = true;
                    }
                }
            }
            rewardList.add(copyOfReward(reward));
            reader.close();

            ServerCommunicator.sendConsoleMessage("Rewards loaded. Total of: " + rewardList.size() + " rewards.");
        } catch (IOException e) {
            e.printStackTrace();
            ServerCommunicator.sendConsoleMessage(ChatColor.RED + "[ERROR] loading of rewards failed.");
        }

    }

    public static Reward copyOfReward(Reward reward){
        Reward newReward = new Reward();
        for(RewardType material : reward.getRewards()){
            if(material instanceof ItemStackReward) {
                newReward.addItemStack(new ItemStack((ItemStack) material.get()));
            } else {
                newReward.rewards.add(material);
            }
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
            String txt = getDefaultRewards();

            for(Byte bit : txt.getBytes(Charset.defaultCharset())){
                out.write(bit);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getDefaultRewards(){
        return "#Names of rewards dont matter. I've called them Reward (number).\n" +
                "#Blank lines are ignored.\n" +
                "#\n" +
                "# To create a new reward:\n" +
                "#\n" +
                "# Name of the reward\n" +
                "# -[itemid] [amount]\n" +
                "# -[itemid] [amount]\n" +
                "# etc.\n" +
                "#\n" +
                "# To create a new money reward:\n" +
                "# -money 100\n" +
                "# -money 500\n" +
                "#\n" +
                "# To create a custom command:\n" +
                "# -custom summon minecraft:creeper %playerX% %playerY% %playerZ%\n" +
                "# -custom give %player% minecraft:diamond 10\n" +
                "#\n" +
                "# To send a chat message to the winning player:\n" +
                "# -say You won!\n" +
                "# -say congrats.\n"+
                "#\n" +
                "Reward 1:\n" +
                "-diamond 1\n" +
                "-money 100\n" +
                "\n" +
                "Reward 2:\n" +
                "-iron_ingot 8\n" +
                "-money 10.50\n" +
                "\n" +
                "Reward 3:\n" +
                "-quartz_block 16\n" +
                "\n" +
                "Reward 4:\n" +
                "-coal 16\n" +
                "\n" +
                "Reward 5:\n" +
                "-Emerald 1\n" +
                "\n" +
                "Reward 6:\n" +
                "-gold_ingot 6\n" +
                "\n" +
                "Reward 7:\n" +
                "-frog_spawn_egg 1\n" +
                "-money 20\n" +
                "\n" +
                "Reward 8:\n" +
                "-cobblestone 16\n" +
                "-money 30\n" +
                "\n" +
                "Reward 9:\n" +
                "-arrow 16\n" +
                "\n" +
                "Reward 10:\n" +
                "-spectral_arrow 4\n" +
                "\n" +
                "Reward 11:\n" +
                "-say You got a dog!\n" +
                "-custom summon minecraft:wolf %playerX% %playerY% %playerZ%";
    }
}
