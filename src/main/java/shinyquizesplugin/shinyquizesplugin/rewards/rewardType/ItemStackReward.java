package shinyquizesplugin.shinyquizesplugin.rewards.rewardType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class ItemStackReward implements RewardType{

    private final ItemStack itemstack;

    public ItemStackReward(ItemStack itemstack) {
        this.itemstack = itemstack;
    }


    @Override
    public Object get() {
        return itemstack;
    }

    @Override
    public void execute(Player player) {
        String item = itemstack.getType().name().toLowerCase().replaceAll("_"," ");
        if(ConfigManager.getConfig().getBoolean("enableRewardNotifier")){
            ServerCommunicator.sendChatMessageToPlayer(player,
                    ConfigManager.getConfig().getString("rewardNotifierColor")
                            +"+"+itemstack.getAmount()+" "+item);
        }
        if(player.getInventory().firstEmpty() != -1) {
            player.getInventory().addItem(itemstack);
        } else {
            Bukkit.getScheduler().scheduleSyncDelayedTask(PLUGIN, () -> player.getWorld().dropItemNaturally(player.getLocation(), itemstack));
        }
    }
}
