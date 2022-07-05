package shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface subcommand {

    List<String> getSubgroups(CommandSender sender, Command command, String label, String[] args);

}
