package shinyquizesplugin.shinyquizesplugin.Quiz;

import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class QuestionManager {

    public static void createQuestion(Question question){
        ServerCommunicator.sendChatMessage(LanguageManager.getLanguage().get("questionPrefix")+question.getQuestion());
        ServerCommunicator.sendConsoleMessage(LanguageManager.getLanguage().get("consoleQuestionAnswered")+ question.getAnswer());
        ActiveQuizInformation.setQuestion(question);

        PLUGIN.getServer().getScheduler().cancelTasks(PLUGIN);
        PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, ActiveQuizInformation::cancelQuestion, 20L * ConfigManager.getConfig().getInt("QuestionTimeOutDelay"));

    }

}
