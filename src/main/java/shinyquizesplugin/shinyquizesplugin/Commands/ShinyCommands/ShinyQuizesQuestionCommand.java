package shinyquizesplugin.shinyquizesplugin.Commands.ShinyCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;

public class ShinyQuizesQuestionCommand implements ShinyQuizesCommand {
    @Override
    public boolean executeCommand(CommandSender sender, String label, String[] args) {
        if (ActiveQuizInformation.isActive()) {
            ActiveQuizInformation.cancelQuestion();
        } else {
            if(sender instanceof Player) {
                ServerCommunicator.sendChatMessageToPlayer((Player) sender, LanguageManager.getLanguage().get("quizNotActive"));
            } else {
                ServerCommunicator.sendConsoleMessage(LanguageManager.getLanguage().get("quizNotActive"));
            }
        }
        return true;
    }
}
