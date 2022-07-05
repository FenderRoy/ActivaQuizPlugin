package shinyquizesplugin.shinyquizesplugin.Mangers;

import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

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
    }
    public static void initializeConfig(){

        PLUGIN.saveDefaultConfig();
        ConfigManager.config = PLUGIN.getConfig();

        writeConfig();

        config.options().copyDefaults(true);
        PLUGIN.saveConfig();

        ServerCommunicator.sendConsoleMessage(ChatColor.GREEN+"Config loaded succesfully.");
    }



    public static FileConfiguration getConfig() {
        return config;
    }

    public static void setConfig(FileConfiguration config) {
        ConfigManager.config = config;
    }
}
