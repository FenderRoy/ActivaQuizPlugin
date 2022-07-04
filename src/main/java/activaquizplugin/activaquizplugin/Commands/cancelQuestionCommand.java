package activaquizplugin.activaquizplugin.Commands;

import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.Quiz.ActiveQuizInformation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class cancelQuestionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if (ActiveQuizInformation.isActive()) {
                ActiveQuizInformation.cancelQuestion();
            } else {
                ServerCommunicator.sendChatMessageToPlayer(player, "Er is geen actieve vraag.");
            }
        }
        return true;
    }
}
