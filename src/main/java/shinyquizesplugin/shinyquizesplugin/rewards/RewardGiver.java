package shinyquizesplugin.shinyquizesplugin.rewards;

import org.bukkit.entity.Player;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.rewards.rewardType.RewardType;

public class RewardGiver {

    public static void giveReward(Player player){
        if(!ConfigManager.getConfig().getBoolean("enableRewards")) return;
        for(int i = 0; i < ConfigManager.getConfig().getInt("amountOfRewards"); i++) {
            giveRewardToPlayer(player);
        }

        if(Math.random()*100 < ConfigManager.getConfig().getInt("percentChanceForExtraReward")){
            ServerCommunicator.sendChatMessageToPlayer(player, ConfigManager.getConfig().getString("rewardNotifierColor")
                    + LanguageManager.getLanguage().get("extraReward"));
            giveRewardToPlayer(player);
        }

    }

    private static void giveRewardToPlayer(Player player){
        if (RewardManager.rewardList.size() == 0) return;
        Reward reward = RewardManager.getRandomReward();
        ServerCommunicator.sendDebugMessage("Gave reward: \""+reward.getName()+"\"");

        for (RewardType rewardType : reward.getRewards()) {
            rewardType.execute(player);
        }
    }

}
