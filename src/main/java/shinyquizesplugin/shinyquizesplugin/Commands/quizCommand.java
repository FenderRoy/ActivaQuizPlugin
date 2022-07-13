package shinyquizesplugin.shinyquizesplugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.handlers.gui.ShinyQuizesGUI;

public class quizCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            ShinyQuizesGUI inv = new ShinyQuizesGUI();
            if(sender.hasPermission("ShinyQuizes.askQuestions")) inv.openInventory((Player) sender);
            return true;
        }
        return false;
    }
}
