package shinyquizesplugin.shinyquizesplugin.Quiz;

import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class QuestionManager {

    public static void createQuestion(Question question){
        ServerCommunicator.sendChatMessage("Snel! "+question.getQuestion());
        ServerCommunicator.sendConsoleMessage("Het juiste antwoord is: "+ question.getAnswer());
        ActiveQuizInformation.setQuestion(question);

        PLUGIN.getServer().getScheduler().cancelTasks(PLUGIN);
        PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, ActiveQuizInformation::cancelQuestion, 20L * ConfigManager.getConfig().getInt("QuestionTimeOutDelay"));

    }

}
