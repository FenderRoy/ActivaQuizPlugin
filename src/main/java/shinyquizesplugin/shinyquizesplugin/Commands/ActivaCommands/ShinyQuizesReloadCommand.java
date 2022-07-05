package shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands;

import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.CustomQuestionsManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class ShinyQuizesReloadCommand implements ShinyQuizesCommand {
    @Override
    public boolean executeCommand(CommandSender sender, String label, String[] args) {
        CustomQuestionsManager.getCustomQuestionsFromFile();

        PLUGIN.reloadConfig();

        PLUGIN.saveDefaultConfig();
        ConfigManager.setConfig(PLUGIN.getConfig());
        ConfigManager.getConfig().options().copyDefaults(true);
        PLUGIN.saveConfig();

        QuestionAskerManager.start();
        ServerCommunicator.initialize();
        if(sender instanceof Player) {
            ServerCommunicator.sendChatMessageToPlayer((Player) sender, "ShinyQuizes reloaded. Er zijn in totaal " + CustomQuestionsManager.getCustomQuestionList().size() + " custom vragen.");
        } else {
            ServerCommunicator.sendConsoleMessage("ShinyQuizes reloaded. Er zijn in totaal " + CustomQuestionsManager.getCustomQuestionList().size() + " custom vragen.");
        }

        return true;
    }
}
