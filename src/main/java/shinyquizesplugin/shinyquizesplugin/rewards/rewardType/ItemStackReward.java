package shinyquizesplugin.shinyquizesplugin.rewards.rewardType;

import org.bukkit.inventory.ItemStack;

public class ItemStackReward implements RewardType{

    private final ItemStack itemstack;

    public ItemStackReward(ItemStack itemstack) {
        this.itemstack = itemstack;
    }


    @Override
    public Object get() {
        return itemstack;
    }
}
