package activaquizplugin.activaquizplugin.Commands;

import activaquizplugin.activaquizplugin.Commands.ActivaCommands.ActivaReloadCommand;
import activaquizplugin.activaquizplugin.Mangers.ConfigManager;
import activaquizplugin.activaquizplugin.Mangers.QuestionAskerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class ActivaQuizPluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        switch(args[0].toLowerCase()){
            case "reload":
                new ActivaReloadCommand().executeCommand(sender,label,args);
                break;
            case "stelrandomvraag":
                QuestionAskerManager.askRandomQuestion();
                break;
            default:
                return false;
        }

        return true;
    }
}
