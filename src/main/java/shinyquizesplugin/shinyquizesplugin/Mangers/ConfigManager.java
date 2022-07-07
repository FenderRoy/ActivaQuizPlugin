package shinyquizesplugin.shinyquizesplugin.Mangers;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.LeaderboardManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class ConfigManager {

    private static FileConfiguration config;

    private static void writeConfig(){
        config.addDefault("ChatPrefix", "§5Shiny §dQuizes");
        config.addDefault("PlusQuestionMaxValue", 150);
        config.addDefault("MinusQuestionMaxValue", 100);
        config.addDefault("MinusQuestionAnswerCanBeNegative", true);
        config.addDefault("MultiplyQuestionMaxValue", 25);
        config.addDefault("QuestionTimeOutDelay", 30);
        config.addDefault("DelayBetweenQuestions", 90);
        config.addDefault("PercentChanceForQuestion", 33);
        config.addDefault("enableMathQuestions", true);
        config.addDefault("enableCustomQuestions", true);
        config.addDefault("enableRandomQuestions", true);
        config.addDefault("GiveRandomRewardOnCorrectAnswer", true);
        config.addDefault("enableTypeWordQuestions", true);
        config.addDefault("enableShuffledWordQuestions", true);
        config.addDefault("enableAcronymWordQuestions", true);
        config.addDefault("HighlightedWordColor", "§b");
        config.addDefault("EnableLeaderboard", true);

        config.addDefault("enableRandomQuestionAnnouncement", true);
        config.addDefault("RandomQuestionAnnouncementTimer", 15);

        config.addDefault("enableMySQL", false);
        config.addDefault("DatabaseIPAdress", "1.1.1.1:3306");
        config.addDefault("DatabaseName", "ShinyQuizesLeaderboard");
        config.addDefault("DatabaseUsername", "Username");
        config.addDefault("DatabasePassword", "Password");

        config.addDefault("Language", "english.properties");


    }
    public static void initializeConfig(){

        PLUGIN.saveDefaultConfig();
        ConfigManager.config = PLUGIN.getConfig();

        writeConfig();

        config.options().copyDefaults(true);
        PLUGIN.saveConfig();

        LeaderboardManager.initialize();

        ServerCommunicator.sendConsoleMessage("Config loaded succesfully.");
    }



    public static FileConfiguration getConfig() {
        return config;
    }

    public static void setConfig(FileConfiguration config) {
        ConfigManager.config = config;
    }
}
