package shinyquizesplugin.shinyquizesplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import shinyquizesplugin.Languages.FileManager;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.PlayerWinManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.CommandsManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.HandlersManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.AcronymQuestion.AcronymQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.CustomQuestions.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions.ShuffledWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeQuestions.TypeWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters.RandomQuestionManager;
import shinyquizesplugin.shinyquizesplugin.rewards.RewardManager;

public final class ShinyQuizesPlugin extends JavaPlugin {

    public static ShinyQuizesPlugin PLUGIN = null;

    @Override
    public void onEnable() {
        PLUGIN = this;
        ConfigManager.initializeConfig();
        FileManager.createFiles();
        LanguageManager.initialize();

        LanguageManager.loadLanguage(ConfigManager.getConfig().getString("Language"));
        ServerCommunicator.initialize();

        HandlersManager.initializeHandlers();
        CommandsManager.initializePlugins();

        CustomQuestionsManager.getCustomQuestionsFromFile();
        ShuffledWordQuestionManager.initialize();
        TypeWordQuestionManager.initialize();
        AcronymQuestionsManager.getCustomQuestionsFromFile();
        RewardManager.initializeRewards();
        RandomQuestionManager.initialize();

        PlayerWinManager.loadWinValues();

        QuestionAskerManager.start();

        ServerCommunicator.sendConsoleMessage(ChatColor.GREEN + "Plugin loaded.");


    }

    @Override
    public void onDisable() {
        PlayerWinManager.saveWinValues();
        // Plugin shutdown logic
        ServerCommunicator.sendConsoleMessage(ChatColor.RED + "Shutting down.");
    }
}
