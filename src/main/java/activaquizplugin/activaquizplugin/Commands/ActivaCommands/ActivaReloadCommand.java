package activaquizplugin.activaquizplugin.Commands.ActivaCommands;

import activaquizplugin.activaquizplugin.Mangers.ConfigManager;
import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.Mangers.QuestionAskerManager;
import activaquizplugin.activaquizplugin.Quiz.CustomQuestionsManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static activaquizplugin.activaquizplugin.ActivaQuizPlugin.PLUGIN;

public class ActivaReloadCommand implements ActivaCommand{
    @Override
    public boolean executeCommand(CommandSender sender, String label, String[] args) {
        CustomQuestionsManager.getCustomQuestionsFromFile();
        ServerCommunicator.sendChatMessageToPlayer((Player) sender, "Vragen gereloaded. Er zijn in totaal "+CustomQuestionsManager.getCustomQuestionList().size()+" custom vragen.");

        PLUGIN.reloadConfig();

        PLUGIN.saveDefaultConfig();
        ConfigManager.setConfig(PLUGIN.getConfig());
        ConfigManager.getConfig().options().copyDefaults(true);
        PLUGIN.saveConfig();

        QuestionAskerManager.start();

        return true;
    }
}
