package shinyquizesplugin.shinyquizesplugin.Mangers;

import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.handlers.CheckChatMessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class HandlersManager {

    public static void initializeHandlers(){
        List<Listener> handlers = Arrays.asList(
                new CheckChatMessageHandler()
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
