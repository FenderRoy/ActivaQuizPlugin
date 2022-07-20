package shinyquizesplugin.shinyquizesplugin.rewards.rewardType;

import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.econ;
import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.vaultEnabled;

public class MoneyReward implements RewardType{

    private final double amount;

    public MoneyReward(double amount){
        this.amount = amount;
    }

    @Override
    public Object get() {
        return amount;
    }

    @Override
    public void execute(Player player) {
        if(!vaultEnabled) return;
        ServerCommunicator.sendChatMessageToPlayer(player,
                ConfigManager.getConfig().getString("rewardNotifierColor")
                        +"+"+
                        ConfigManager.getConfig().getString("rewardMoneySymbol")+amount);
        econ.depositPlayer(player, amount);
    }
}
