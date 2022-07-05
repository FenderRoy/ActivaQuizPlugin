package shinyquizesplugin.shinyquizesplugin.Commands;

import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;
import shinyquizesplugin.shinyquizesplugin.Quiz.QuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.RandomMathQuestion;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class startMathQuestionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ActiveQuizInformation.isActive()) {
                ServerCommunicator.sendChatMessageToPlayer(player, "Er is al een quiz bezig!");
                return true;
            }

            RandomMathQuestion question = new RandomMathQuestion();

            if(args.length == 0) {
                question.generateRandomValues();
            } else {
                switch(args[0].toLowerCase()){
                    case "plussom":
                        question.generateRandomValues(1);
                        break;
                    case "minsom":
                        question.generateRandomValues(2);
                        break;
                    case "keersom":
                        question.generateRandomValues(3);
                        break;
                    case "randomsom":
                        question.generateRandomValues();
                        break;
                    default:
                        ServerCommunicator.sendChatMessageToPlayer(player, "Geen geldig argument, Laat argumenten leeg of gebruik: plusSom, minSom, keerSom of randomSom.");
                        return false;
                }
            }
            QuestionManager.createQuestion(question);
            return true;
        }
        return false;
    }
}
