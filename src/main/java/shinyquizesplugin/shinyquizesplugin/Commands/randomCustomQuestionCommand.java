package shinyquizesplugin.shinyquizesplugin.Commands;

import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;
import shinyquizesplugin.shinyquizesplugin.Quiz.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.QuestionManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class randomCustomQuestionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (ActiveQuizInformation.isActive()) {
                ServerCommunicator.sendChatMessageToPlayer(player, "Er is al een quiz bezig!");
                return true;
            }

            if(!CustomQuestionsManager.getCustomQuestionList().isEmpty()){
                QuestionManager.createQuestion(CustomQuestionsManager.getRandomQuestion());
            } else {
                ServerCommunicator.sendChatMessageToPlayer(player, "Er zijn geen custom vragen.");
            }

        }
        return true;
    }
}
