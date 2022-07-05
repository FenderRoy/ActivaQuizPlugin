package shinyquizesplugin.shinyquizesplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.LeaderboardManager;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.CommandsManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.HandlersManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters.RandomQuestionManager;
import shinyquizesplugin.shinyquizesplugin.rewards.RewardManager;

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
        ShuffledWordQuestionManager.initialize();
        TypeWordQuestionManager.initialize();
        RewardManager.initializeRewards();
        RandomQuestionManager.initialize();

        PlayerWinManager.loadWinValues();

        ServerCommunicator.sendConsoleMessage(ChatColor.GREEN + "Plugin loaded.");

        QuestionAskerManager.start();
    }

    @Override
    public void onDisable() {
        PlayerWinManager.saveWinValues();
        // Plugin shutdown logic
        ServerCommunicator.sendConsoleMessage(ChatColor.RED + "Shutting down.");
    }
}
