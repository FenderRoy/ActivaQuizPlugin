package shinyquizesplugin.shinyquizesplugin.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
                String color = ConfigManager.getConfig().getString("HighlightedWordColor");
                ServerCommunicator.sendChatMessage("Jij hebt in totaal " + color + wins + "§f keer gewonnen!");
            } else {
                Player player = Bukkit.getPlayer(args[0]);
                if(player == null){
                    ServerCommunicator.sendChatMessageToPlayer((Player) sender, "Vul een geldige player in.");
                    return true;
                }
                int wins = PlayerWinManager.playerWinData.get(player.getUniqueId());
                String color = ConfigManager.getConfig().getString("HighlightedWordColor");
                ServerCommunicator.sendChatMessage(player.getName()+" heeft in totaal " + color + wins + "§f keer gewonnen!");
            }
        } else {
            ServerCommunicator.sendConsoleMessage(ChatColor.RED+"This command can't be executed from within console.");
        }
        return true;
    }
}
