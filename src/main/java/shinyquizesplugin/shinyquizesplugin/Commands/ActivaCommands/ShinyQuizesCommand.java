package shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands;

import org.bukkit.command.CommandSender;

public interface ShinyQuizesCommand {

    boolean executeCommand(CommandSender sender, String label, String[] args);
}
