package shinyquizesplugin.shinyquizesplugin.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;
import shinyquizesplugin.shinyquizesplugin.rewards.RewardGiver;

import java.util.Locale;

public class CheckChatMessageHandler implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onChatMessageSend(AsyncPlayerChatEvent event){

        if(!ActiveQuizInformation.isActive()) return;
        String msg = event.getMessage().toLowerCase(Locale.ROOT);

        if(msg.equals(ActiveQuizInformation.getQuestion().getAnswer().toLowerCase())){
            ServerCommunicator.sendChatMessage(event.getPlayer().getName()+ " heeft het correct!");
            event.setCancelled(true);
            PlayerWinManager.addWin(event.getPlayer(), 1);
            RewardGiver.giveReward(event.getPlayer());
            ActiveQuizInformation.setActive(false);
        }
    }
}
