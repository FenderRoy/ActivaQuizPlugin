package shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class originalSubgroupSubcompletions implements subcommand {
    @Override
    public List<String> getSubgroups(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        List<String> completions = new ArrayList<>();
        if(sender.hasPermission("ShinyQuizes.reload")) completions.add("reload");
        if(sender.hasPermission("ShinyQuizes.QuizWins")) completions.add("wins");
        if(sender.hasPermission("ShinyQuizes.QuizLeaderboard")) completions.add("leaderboard");
        if(sender.hasPermission("ShinyQuizes.askQuestions")) completions.add("askRandomQuestion");
        if(sender.hasPermission("ShinyQuizes.askQuestions")) completions.add("askQuestion");
        if(sender.hasPermission("ShinyQuizes.cancelQuestions")) completions.add("cancelQuestion");
        return completions;
    }
}
