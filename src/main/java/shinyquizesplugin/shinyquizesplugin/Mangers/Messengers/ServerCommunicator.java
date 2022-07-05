package shinyquizesplugin.shinyquizesplugin.Mangers.Messengers;

import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ServerCommunicator {

    private static final String consolePrefix =
             ChatColor.GRAY+"["
            + ChatColor.LIGHT_PURPLE +"ShinyQuizes"
            +ChatColor.GRAY+"]"
            +ChatColor.WHITE+": ";

    private static String chatPrefix;

    public static void initialize(){

        String prefix = ConfigManager.getConfig().getString("ChatPrefix");
        String coloredPrefix = ChatColor.translateAlternateColorCodes('&',prefix);
        ServerCommunicator.chatPrefix = ChatColor.GRAY+"["+coloredPrefix+ChatColor.GRAY+"]"+ChatColor.WHITE+": ";
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
