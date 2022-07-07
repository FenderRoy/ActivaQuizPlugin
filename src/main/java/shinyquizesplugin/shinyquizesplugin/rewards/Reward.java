package shinyquizesplugin.shinyquizesplugin.rewards;

import org.bukkit.inventory.ItemStack;
import shinyquizesplugin.shinyquizesplugin.rewards.rewardType.ItemStackReward;
import shinyquizesplugin.shinyquizesplugin.rewards.rewardType.RewardType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reward {

    public final List<RewardType> rewards = new ArrayList<>();

    public List<RewardType> getRewards() {
        return Collections.unmodifiableList(rewards);
    }
    public void addItemStack(ItemStack mat){
        this.rewards.add(new ItemStackReward(mat));
    }
}
