package shinyquizesplugin.shinyquizesplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

public class QuizWinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {

            if(args.length < 1) {
                Player player = (Player) sender;
                int wins = PlayerWinManager.playerWinData.get(player.getUniqueId());
                ServerCommunicator.sendChatMessage(getSelfString(wins));
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if(player == null){
                    ServerCommunicator.sendChatMessageToPlayer((Player) sender, LanguageManager.getLanguage().get("noValidPlayer"));
                    return true;
                }
                int wins = PlayerWinManager.playerWinData.get(player.getUniqueId());
                ServerCommunicator.sendChatMessage(getOtherString(player.getName(),wins));
            }
        } else {
            ServerCommunicator.sendConsoleMessage(ChatColor.RED+LanguageManager.getLanguage().get("noConsoleCommand"));
        }
        return true;
    }

    private String getSelfString(int wins){
        String color = ConfigManager.getConfig().getString("HighlightedWordColor");
        String string = LanguageManager.getLanguage().get("winAmountSelf");
        return java.text.MessageFormat.format(string, color+wins+ChatColor.WHITE);
    }

    private String getOtherString(String player, int wins){
        String color = ConfigManager.getConfig().getString("HighlightedWordColor");
        String string = LanguageManager.getLanguage().get("winAmountOther");
        return java.text.MessageFormat.format(string, player, color+wins+ChatColor.WHITE);
    }
}
