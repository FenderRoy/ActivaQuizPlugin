package shinyquizesplugin.shinyquizesplugin.rewards.rewardType;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class CustomCommandReward implements RewardType{


    private String command;

    public CustomCommandReward(String command){
        this.command = command;
    }

    @Override
    public Object get() {
        return command;
    }

    @Override
    public void execute(Player player) {
        String comm = initializePlaceholders(player);
        ServerCommunicator.sendDebugMessage("Command Reward: "+comm);
        Bukkit.getScheduler().scheduleSyncDelayedTask(PLUGIN, () -> Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(),comm));
    }

    public String initializePlaceholders(Player player){
        String comm;
        comm = command.replaceAll("%player%", player.getName());
        comm = comm.replaceAll("%playerX%", String.valueOf(player.getLocation().getBlockX()));
        comm = comm.replaceAll("%playerY%", String.valueOf(player.getLocation().getBlockY()));
        comm = comm.replaceAll("%playerZ%", String.valueOf(player.getLocation().getBlockZ()));
        return comm;
    }
}
