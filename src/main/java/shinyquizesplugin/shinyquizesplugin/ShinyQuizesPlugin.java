package shinyquizesplugin.shinyquizesplugin;

import shinyquizesplugin.shinyquizesplugin.Mangers.CommandsManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Mangers.HandlersManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.CustomQuestionsManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ShinyQuizesPlugin extends JavaPlugin {

    public static ShinyQuizesPlugin PLUGIN = null;

    @Override
    public void onEnable() {
        PLUGIN = this;
        ConfigManager.initializeConfig();
        ServerCommunicator.initialize();

        HandlersManager.initializeHandlers();
        CommandsManager.initializePlugins();

        CustomQuestionsManager.getCustomQuestionsFromFile();

        ServerCommunicator.sendConsoleMessage(ChatColor.GREEN + "Plugin loaded.");

        QuestionAskerManager.start();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        ServerCommunicator.sendConsoleMessage(ChatColor.RED + "Shutting down.");
    }
}
