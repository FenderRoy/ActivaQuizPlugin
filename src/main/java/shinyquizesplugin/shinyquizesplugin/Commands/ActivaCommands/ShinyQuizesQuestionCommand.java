package shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;

public class ShinyQuizesQuestionCommand implements ShinyQuizesCommand {
    @Override
    public boolean executeCommand(CommandSender sender, String label, String[] args) {
        if (ActiveQuizInformation.isActive()) {
            ActiveQuizInformation.cancelQuestion();
        } else {
            if(sender instanceof Player) {
                ServerCommunicator.sendChatMessageToPlayer((Player) sender, "Er is geen actieve vraag.");
            } else {
                ServerCommunicator.sendConsoleMessage("Er is geen actieve vraag.");
            }
        }
        return true;
    }
}
