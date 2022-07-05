package shinyquizesplugin.shinyquizesplugin.Commands;

import shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands.ShinyQuizesQuestionCommand;
import shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands.ShinyQuizesReloadCommand;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ShinyQuizesPluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) return false;

        switch(args[0].toLowerCase()){
            case "reload":
                new ShinyQuizesReloadCommand().executeCommand(sender,label,args);
                break;
            case "askrandomquestion":
                QuestionAskerManager.askRandomQuestion(100);
                break;
            case "cancelquestion":
                new ShinyQuizesQuestionCommand().executeCommand(sender,label,args);
                break;
            default:
                return false;
        }

        return true;
    }
}
