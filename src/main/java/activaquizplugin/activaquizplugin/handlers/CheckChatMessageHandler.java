package activaquizplugin.activaquizplugin.handlers;

import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.Quiz.ActiveQuizInformation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Locale;

public class CheckChatMessageHandler implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onChatMessageSend(AsyncPlayerChatEvent event){

        if(!ActiveQuizInformation.isActive()) return;
        String msg = event.getMessage().toLowerCase(Locale.ROOT);

        if(msg.equals(ActiveQuizInformation.getQuestion().getAnswer().toLowerCase())){
            ServerCommunicator.sendChatMessage(event.getPlayer().getName()+ " heeft het correct!");
            event.setCancelled(true);
            ActiveQuizInformation.setActive(false);
        }
    }

}
