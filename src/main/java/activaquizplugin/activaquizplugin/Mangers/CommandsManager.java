package activaquizplugin.activaquizplugin.Mangers;

import activaquizplugin.activaquizplugin.Commands.ActivaQuizPluginCommand;
import activaquizplugin.activaquizplugin.Commands.cancelQuestionCommand;
import activaquizplugin.activaquizplugin.Commands.randomCustomQuestionCommand;
import activaquizplugin.activaquizplugin.Commands.startMathQuestionCommand;
import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.TabCompleters.ActivaQuizPluginTabCompleter;
import activaquizplugin.activaquizplugin.TabCompleters.startMathQuestionTabCompleter;

import static activaquizplugin.activaquizplugin.ActivaQuizPlugin.PLUGIN;

public class CommandsManager {

    public static void initializePlugins(){

        PLUGIN.getCommand("randomWiskundeVraag").setExecutor(new startMathQuestionCommand());
        PLUGIN.getCommand("randomWiskundeVraag").setTabCompleter(new startMathQuestionTabCompleter());

        PLUGIN.getCommand("ActivaQuizPlugin").setExecutor(new ActivaQuizPluginCommand());
        PLUGIN.getCommand("ActivaQuizPlugin").setTabCompleter(new ActivaQuizPluginTabCompleter());

        PLUGIN.getCommand("annuleerHuidigeVraag").setExecutor(new cancelQuestionCommand());
        PLUGIN.getCommand("randomCustomVraag").setExecutor(new randomCustomQuestionCommand());

        ServerCommunicator.sendConsoleMessage("Commands initialized.");
    }

}
