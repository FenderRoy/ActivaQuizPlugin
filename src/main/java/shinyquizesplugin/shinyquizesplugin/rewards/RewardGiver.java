package shinyquizesplugin.shinyquizesplugin.rewards;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;

public class RewardGiver {

    public static void giveReward(Player player){
        if(!ConfigManager.getConfig().getBoolean("GiveRandomRewardOnCorrectAnswer")) return;
        if(RewardManager.rewardList.size() == 0) return;

        Reward reward = RewardManager.getRandomReward();

        for(ItemStack stack : reward.getRewards()){
            player.getInventory().addItem(stack);
        }

    }

}
