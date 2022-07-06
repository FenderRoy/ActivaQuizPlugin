package shinyquizesplugin.shinyquizesplugin.handlers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;
import shinyquizesplugin.shinyquizesplugin.rewards.RewardGiver;

import java.util.Locale;

public class CheckChatMessageHandler implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onChatMessageSend(AsyncPlayerChatEvent event){

        if(!ActiveQuizInformation.isActive()) return;
        String msg = event.getMessage().toLowerCase(Locale.ROOT);

        if(msg.equals(ActiveQuizInformation.getQuestion().getAnswer().toLowerCase())){
            String string = LanguageManager.getLanguage().get("correctAnswer");
            String stringformatted = java.text.MessageFormat.format(string, event.getPlayer().getName());

            ServerCommunicator.sendChatMessage(stringformatted);
            event.setCancelled(true);
            PlayerWinManager.addWin(event.getPlayer(), 1);
            RewardGiver.giveReward(event.getPlayer());
            ActiveQuizInformation.setActive(false);
        }
    }
}
