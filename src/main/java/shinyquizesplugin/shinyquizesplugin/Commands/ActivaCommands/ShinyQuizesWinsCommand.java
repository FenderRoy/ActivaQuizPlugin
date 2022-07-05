package shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

public class ShinyQuizesWinsCommand implements ShinyQuizesCommand{
    @Override
    public boolean executeCommand(CommandSender sender, String label, String[] args) {

        if(sender instanceof Player) {

            if(args.length <= 1) {
                Player player = (Player) sender;
                int wins = PlayerWinManager.playerWinData.get(player.getUniqueId());
                String color = ConfigManager.getConfig().getString("HighlightedWordColor");
                ServerCommunicator.sendChatMessage("Jij hebt in totaal " + color + wins + "§f keer gewonnen!");
            } else {
                Player player = Bukkit.getPlayer(args[1]);
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
