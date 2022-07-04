package activaquizplugin.activaquizplugin.Mangers;

import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.handlers.CheckChatMessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

import static activaquizplugin.activaquizplugin.ActivaQuizPlugin.PLUGIN;

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
