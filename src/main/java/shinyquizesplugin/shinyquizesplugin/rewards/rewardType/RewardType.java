package shinyquizesplugin.shinyquizesplugin.rewards.rewardType;

import org.bukkit.entity.Player;

public interface RewardType {

    Object get();

    void execute(Player player);

}
