package shinyquizesplugin.shinyquizesplugin.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ShinyQuizesPluginTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            List<String> completions = new ArrayList<>();

            completions.add("reload");
            completions.add("askRandomQuestion");
            completions.add("cancelQuestion");

            return completions;
        }

        return null;
    }
}
