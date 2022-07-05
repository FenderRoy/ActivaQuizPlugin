package shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class askQuestionTabCompleter implements subcommand{
    @Override
    public List<String> getSubgroups(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if(args.length <= 2) {

            completions.add("CustomQuestion");
            completions.add("MathQuestion");
            completions.add("ShuffledQuestion");
            completions.add("TypeQuestion");

        }
        switch(args[1].toLowerCase()){
            case "mathquestion":
                completions.add("plus");
                completions.add("minus");
                completions.add("multiply");
                completions.add("random");
                break;
        }

        return completions;
    }
}
