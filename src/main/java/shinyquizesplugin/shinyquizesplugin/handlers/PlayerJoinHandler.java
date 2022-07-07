package shinyquizesplugin.shinyquizesplugin.handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.isUpToDate;

public class PlayerJoinHandler implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        PlayerWinManager.playerWinData.putIfAbsent(player.getUniqueId(), 0);
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void checkForUpdates(PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(player.isOp()){
            if(!isUpToDate){
                ServerCommunicator.sendChatMessageToPlayer(player, ChatColor.RED+"Shiny Quizes is not up to date. Please consider updating.");
            }
        }
    }

}
