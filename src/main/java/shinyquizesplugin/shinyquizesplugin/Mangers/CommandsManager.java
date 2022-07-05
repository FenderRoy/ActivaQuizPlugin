package shinyquizesplugin.shinyquizesplugin.Mangers;

import shinyquizesplugin.shinyquizesplugin.Commands.*;
import shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands.QuizLeaderboardCommand;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.QuizWinsTabCompleter;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizesPluginTabCompleter;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.startMathQuestionTabCompleter;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class CommandsManager {

    public static void initializePlugins(){
        PLUGIN.getCommand("ShinyQuizes").setExecutor(new ShinyQuizesPluginCommand());
        PLUGIN.getCommand("ShinyQuizes").setTabCompleter(new ShinyQuizesPluginTabCompleter());

        PLUGIN.getCommand("QuizWins").setExecutor(new QuizWinsCommand());
        PLUGIN.getCommand("QuizWins").setTabCompleter(new QuizWinsTabCompleter());

        PLUGIN.getCommand("QuizLeaderboard").setExecutor(new QuizLeaderboardCommand());

        ServerCommunicator.sendConsoleMessage("Commands initialized.");
    }

}
