package activaquizplugin.activaquizplugin.Commands;

import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.Quiz.ActiveQuizInformation;
import activaquizplugin.activaquizplugin.Quiz.CustomQuestionsManager;
import activaquizplugin.activaquizplugin.Quiz.QuestionManager;
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

            QuestionManager.createQuestion(CustomQuestionsManager.getRandomQuestion());

        }
        return true;
    }
}
