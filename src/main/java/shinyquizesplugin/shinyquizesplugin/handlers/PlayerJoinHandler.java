package shinyquizesplugin.shinyquizesplugin.handlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;

public class PlayerJoinHandler implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        PlayerWinManager.playerWinData.putIfAbsent(player.getUniqueId(), 0);
    }

}
