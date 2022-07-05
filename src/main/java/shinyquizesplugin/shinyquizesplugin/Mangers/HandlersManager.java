package shinyquizesplugin.shinyquizesplugin.Mangers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.handlers.CheckChatMessageHandler;
import shinyquizesplugin.shinyquizesplugin.handlers.PlayerJoinHandler;

import java.util.Arrays;
import java.util.List;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class HandlersManager {

    public static void initializeHandlers(){
        List<Listener> handlers = Arrays.asList(
                new CheckChatMessageHandler(),
                new PlayerJoinHandler()
        );
        register(handlers);
        ServerCommunicator.sendConsoleMessage("Handlers initialized.");
    }

    private static void register(List<Listener> handlers){
        for(Listener event : handlers) {
            Bukkit.getPluginManager().registerEvents(event, PLUGIN);
        }
    }
}
