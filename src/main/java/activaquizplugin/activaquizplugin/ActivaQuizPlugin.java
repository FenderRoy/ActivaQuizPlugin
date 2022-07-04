package activaquizplugin.activaquizplugin;

import activaquizplugin.activaquizplugin.Mangers.CommandsManager;
import activaquizplugin.activaquizplugin.Mangers.ConfigManager;
import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.Mangers.HandlersManager;
import activaquizplugin.activaquizplugin.Mangers.QuestionAskerManager;
import activaquizplugin.activaquizplugin.Quiz.CustomQuestionsManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class ActivaQuizPlugin extends JavaPlugin {

    public static ActivaQuizPlugin PLUGIN = null;

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
