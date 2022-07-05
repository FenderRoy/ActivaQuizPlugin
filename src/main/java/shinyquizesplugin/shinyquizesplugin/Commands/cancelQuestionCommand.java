package shinyquizesplugin.shinyquizesplugin.Commands;

import shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands.ShinyQuizesQuestionCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cancelQuestionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            new ShinyQuizesQuestionCommand().executeCommand(sender,label,args);
        }
        return true;
    }
}
