package shinyquizesplugin.shinyquizesplugin.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizes.subcommand;

import java.util.ArrayList;
import java.util.List;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class QuizWinsTabCompleter implements TabCompleter, subcommand {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            List<String> completions = new ArrayList<>();

            for(Player player : PLUGIN.getServer().getOnlinePlayers()){
                completions.add(player.getName());
            }

            return completions;
        }

        return null;
    }

    @Override
    public List<String> getSubgroups(CommandSender sender, Command command, String label, String[] args) {
        return onTabComplete(sender,command,label,args);
    }
}
