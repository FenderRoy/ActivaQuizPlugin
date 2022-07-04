package activaquizplugin.activaquizplugin.Quiz;

import activaquizplugin.activaquizplugin.Mangers.ConfigManager;
import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import activaquizplugin.activaquizplugin.Quiz.Questions.Question;

import static activaquizplugin.activaquizplugin.ActivaQuizPlugin.PLUGIN;

public class QuestionManager {

    public static void createQuestion(Question question){
        ServerCommunicator.sendChatMessage("Vraag! "+question.getQuestion());
        ServerCommunicator.sendConsoleMessage("Het juiste antwoord is: "+ question.getAnswer());
        ActiveQuizInformation.setQuestion(question);

        PLUGIN.getServer().getScheduler().cancelTasks(PLUGIN);
        PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, ActiveQuizInformation::cancelQuestion, 20L * ConfigManager.getConfig().getInt("VraagTimeOutDelay"));

    }

}
