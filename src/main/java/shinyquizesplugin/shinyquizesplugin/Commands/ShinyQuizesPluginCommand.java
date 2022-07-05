package shinyquizesplugin.shinyquizesplugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands.*;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.LeaderboardManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;

public class ShinyQuizesPluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 0) return false;

        switch(args[0].toLowerCase()){
            case "reload":
                if(sender.hasPermission("ShinyQuizes.reload")) new ShinyQuizesReloadCommand().executeCommand(sender,label,args);
                break;
            case "askrandomquestion":
                if(sender.hasPermission("ShinyQuizes.askQuestions")) QuestionAskerManager.askRandomQuestion();
                break;
            case "cancelquestion":
                if(sender.hasPermission("ShinyQuizes.cancelQuestions")) new ShinyQuizesQuestionCommand().executeCommand(sender,label,args);
                break;
            case "wins":
                if(sender.hasPermission("ShinyQuizes.QuizWins")) new ShinyQuizesWinsCommand().executeCommand(sender,label,args);
                break;
            case "leaderboard":
                if(sender.hasPermission("ShinyQuizes.QuizLeaderboard")) new QuizLeaderboardCommand().onCommand(sender,command,label,args);
                break;
            case "askquestion":
                if(sender.hasPermission("ShinyQuizes.askQuestions")) new ShinyQuizAskQuestionCommand().executeCommand(sender,label,args);
                break;
            default:
                return false;
        }

        return true;
    }
}
