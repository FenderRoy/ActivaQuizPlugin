package activaquizplugin.activaquizplugin.Commands.ActivaCommands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ActivaCommand {

    boolean executeCommand(CommandSender sender, String label, String[] args);
}
