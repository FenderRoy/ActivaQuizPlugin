package shinyquizesplugin.shinyquizesplugin.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizes.askQuestionTabCompleter;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizes.originalSubgroupSubcompletions;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizes.reloadCommandSubcompletions;

import java.util.ArrayList;
import java.util.List;

public class ShinyQuizesPluginTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length <= 1) {
                return new originalSubgroupSubcompletions().getSubgroups(sender,command,label,args);
            } else {
                switch(args[0].toLowerCase()){
                    case "reload":
                        if(player.hasPermission("ShinyQuizes.reload")) return new reloadCommandSubcompletions().getSubgroups(sender,command,label,args);
                    case "wins":
                        if(player.hasPermission("ShinyQuizes.QuizWins")) return new QuizWinsTabCompleter().getSubgroups(sender,command,label,args);
                    case "askquestion":
                        if(player.hasPermission("ShinyQuizes.askQuestions"))return new askQuestionTabCompleter().getSubgroups(sender,command,label,args);
                }
            }
        }
        return completions;
    }
}
