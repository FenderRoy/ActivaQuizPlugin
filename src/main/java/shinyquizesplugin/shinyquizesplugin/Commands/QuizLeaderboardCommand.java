package shinyquizesplugin.shinyquizesplugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.LeaderboardManager;

public class QuizLeaderboardCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            LeaderboardManager.printLeaderboard(player);
        }
        return true;
    }
}
