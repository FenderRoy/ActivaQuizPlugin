package shinyquizesplugin.shinyquizesplugin.Leaderboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class LeaderboardManager {

    public static boolean useLeaderboard;

    public static void initialize(){
        useLeaderboard = ConfigManager.getConfig().getBoolean("EnableLeaderboard");
    }

    public static void printLeaderboard(Player player){

        if(!useLeaderboard){
            ServerCommunicator.sendChatMessageToPlayer(player, "Leaderboard staat niet aan op deze server.");
            return;
        }

        //Run in different thread to avoid lagg
        PLUGIN.getServer().getScheduler().runTaskAsynchronously(PLUGIN, () -> {

            List<PlayerHolder> players = getPlayerLeaderboard();

            player.sendMessage(ChatColor.GRAY + "---------- " + ServerCommunicator.getChatPrefix() + ChatColor.GRAY + " ----------");
            player.sendMessage(ChatColor.GRAY + LanguageManager.getLanguage().get("mostQuizWins"));
            int max = 0;
            for (int i = 0; i < players.size(); i++) {
                if (max >= 10) break;
                if(players.get(i).getPlayer() == null) continue;
                player.sendMessage(ChatColor.GRAY + String.valueOf(max + 1) + ". " + ChatColor.WHITE + players.get(i).getPlayer().getName() + ": " + players.get(i).getWins());
                max++;
            }

        });
    }

    private static List<PlayerHolder> getPlayerLeaderboard() {

        List<PlayerHolder> players = new ArrayList<>();

        for(OfflinePlayer player : Bukkit.getOfflinePlayers()){
            players.add(new PlayerHolder(player, PlayerWinManager.getValue(player.getUniqueId())));
        }

        Collections.sort(players);

        return players;
    }

}
