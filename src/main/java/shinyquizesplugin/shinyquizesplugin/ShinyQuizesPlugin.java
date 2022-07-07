package shinyquizesplugin.shinyquizesplugin;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import shinyquizesplugin.Languages.FileManager;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.UpdateChecker;
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
    public static Economy econ = null;
    public static Boolean vaultEnabled = false;
    public static boolean isUpToDate = true;

    @Override
    public void onEnable() {

        PLUGIN = this;
        ConfigManager.initializeConfig();
        FileManager.createFiles();
        LanguageManager.initialize();

        LanguageManager.loadLanguage(ConfigManager.getConfig().getString("Language"));
        ServerCommunicator.initialize();

        if (setupEconomy() ) {
            vaultEnabled = true;
            ServerCommunicator.sendConsoleMessage("Vault detected, money enabled.");
        } else {
            ServerCommunicator.sendConsoleMessage("No vault detected, money disabled.");
        }

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

        checkForUpdates();

        ServerCommunicator.sendConsoleMessage(ChatColor.GREEN + "Plugin loaded. Running on version "+this.getDescription().getVersion()+".");


    }

    @Override
    public void onDisable() {
        PlayerWinManager.saveWinValues();
        // Plugin shutdown logic
        ServerCommunicator.sendConsoleMessage(ChatColor.RED + "Shutting down.");
    }

    private void checkForUpdates(){
        new UpdateChecker(this, 103142).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                ServerCommunicator.sendConsoleMessage("Shiny Quizes is up to date.");
            } else {
                ServerCommunicator.sendConsoleMessage(ChatColor.RED + "There is a new version of Shiny Quizes available. Please consider updating.");
                ServerCommunicator.sendConsoleMessage(ChatColor.RED +"You are on version: "+this.getDescription().getVersion()+", please update to version: "+version);
                isUpToDate = false;
            }
        });
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
