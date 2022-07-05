package shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class reloadCommandSubcompletions implements subcommand{
    @Override
    public List<String> getSubgroups(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();
        completions.add("all");
        completions.add("config");
        completions.add("Rewards");
        completions.add("CustomQuestions");
        completions.add("ShuffledWords");
        completions.add("TypeWords");
        return completions;
    }
}
