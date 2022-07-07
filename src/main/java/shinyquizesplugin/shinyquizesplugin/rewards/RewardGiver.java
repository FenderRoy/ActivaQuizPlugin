package shinyquizesplugin.shinyquizesplugin.rewards;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.rewards.rewardType.ItemStackReward;
import shinyquizesplugin.shinyquizesplugin.rewards.rewardType.MoneyReward;
import shinyquizesplugin.shinyquizesplugin.rewards.rewardType.RewardType;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.*;

public class RewardGiver {

    public static void giveReward(Player player){
        if(!ConfigManager.getConfig().getBoolean("GiveRandomRewardOnCorrectAnswer")) return;
        if(RewardManager.rewardList.size() == 0) return;

        Reward reward = RewardManager.getRandomReward();

        for(RewardType rewardType : reward.getRewards()){
            if(rewardType instanceof ItemStackReward) {
                ItemStackReward reward1 = (ItemStackReward) rewardType;
                player.getInventory().addItem((ItemStack) reward1.get());
            }
            if(rewardType instanceof MoneyReward && vaultEnabled) {
                ServerCommunicator.sendChatMessageToPlayer(player, "+$"+(double)rewardType.get());
                econ.depositPlayer(player, (Double) rewardType.get());
            }
        }

    }

}
