package shinyquizesplugin.shinyquizesplugin.Mangers;

import shinyquizesplugin.shinyquizesplugin.Commands.ShinyQuizesPluginCommand;
import shinyquizesplugin.shinyquizesplugin.Commands.cancelQuestionCommand;
import shinyquizesplugin.shinyquizesplugin.Commands.randomCustomQuestionCommand;
import shinyquizesplugin.shinyquizesplugin.Commands.startMathQuestionCommand;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.ShinyQuizesPluginTabCompleter;
import shinyquizesplugin.shinyquizesplugin.TabCompleters.startMathQuestionTabCompleter;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class CommandsManager {

    public static void initializePlugins(){

        PLUGIN.getCommand("randomMathQuestion").setExecutor(new startMathQuestionCommand());
        PLUGIN.getCommand("randomMathQuestion").setTabCompleter(new startMathQuestionTabCompleter());

        PLUGIN.getCommand("ShinyQuizes").setExecutor(new ShinyQuizesPluginCommand());
        PLUGIN.getCommand("ShinyQuizes").setTabCompleter(new ShinyQuizesPluginTabCompleter());

        PLUGIN.getCommand("cancelCurrentQuestion").setExecutor(new cancelQuestionCommand());
        PLUGIN.getCommand("randomCustomQuestion").setExecutor(new randomCustomQuestionCommand());

        ServerCommunicator.sendConsoleMessage("Commands initialized.");
    }

}
