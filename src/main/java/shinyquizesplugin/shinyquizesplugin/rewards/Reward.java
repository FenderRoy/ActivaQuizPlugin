package shinyquizesplugin.shinyquizesplugin.rewards;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reward {

    public final List<ItemStack> rewards = new ArrayList<>();

    public List<ItemStack> getRewards() {
        return Collections.unmodifiableList(rewards);
    }
    public void addItemStack(ItemStack mat){
        this.rewards.add(mat);
    }
}
