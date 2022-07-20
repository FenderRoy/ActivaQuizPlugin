package shinyquizesplugin.shinyquizesplugin.rewards.rewardType;

import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

public class MessageToPlayerReward implements RewardType{

    private final String message;

    public MessageToPlayerReward(String message){
        this.message = message;
    }

    @Override
    public Object get() {
        return message;
    }

    @Override
    public void execute(Player player) {
        ServerCommunicator.sendChatMessageToPlayer(player, ConfigManager.getConfig().getString("rewardNotifierColor")+ message);
    }
}
