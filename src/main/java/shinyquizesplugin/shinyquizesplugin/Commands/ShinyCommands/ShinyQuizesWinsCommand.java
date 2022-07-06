package shinyquizesplugin.shinyquizesplugin.Commands.ShinyCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

public class ShinyQuizesWinsCommand implements ShinyQuizesCommand{
    @Override
    public boolean executeCommand(CommandSender sender, String label, String[] args) {

        if(sender instanceof Player) {

            if(args.length <= 1) {
                Player player = (Player) sender;
                int wins = PlayerWinManager.get(player.getUniqueId());
                ServerCommunicator.sendChatMessage(getSelfString(wins));
            } else {
                Player player = Bukkit.getPlayer(args[1]);
                if(player == null){
                    ServerCommunicator.sendChatMessageToPlayer((Player) sender, LanguageManager.getLanguage().get("noValidPlayer"));
                    return true;
                }
                int wins = PlayerWinManager.get(player.getUniqueId());

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
