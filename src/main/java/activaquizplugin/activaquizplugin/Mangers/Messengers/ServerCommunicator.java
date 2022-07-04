package activaquizplugin.activaquizplugin.Mangers.Messengers;

import activaquizplugin.activaquizplugin.Mangers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ServerCommunicator {

    private static final String consolePrefix =
             ChatColor.GRAY+"["
            +ChatColor.YELLOW+"ActivaQuizPlugin"
            +ChatColor.GRAY+"]"
            +ChatColor.WHITE+": ";

    private static String chatPrefix;


    public static void initialize(){
        String prefix = ConfigManager.getConfig().getString("ChatPrefix");
        ServerCommunicator.chatPrefix =
                ChatColor.GRAY+"["
                        +ChatColor.GOLD+prefix
                        +ChatColor.GRAY+"]"
                        +ChatColor.WHITE+": ";
    }
    public static void sendConsoleMessage(String message){
        Bukkit.getServer().getConsoleSender().sendMessage(consolePrefix + message);
    }

    public static void sendChatMessage(String message){
        Bukkit.getServer().broadcastMessage(chatPrefix + message);
    }

    public static void sendChatMessageToPlayer(Player player, String message){
        player.sendMessage(chatPrefix + message);
    }

}
