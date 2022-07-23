package shinyquizesplugin.shinyquizesplugin.Quiz;

import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class QuestionManager {

    private static int id = -1;
    public static void createQuestion(Question question){
        if(ConfigManager.getConfig().getBoolean("enableVisabilityEmptyLine")) ServerCommunicator.sendEmptyChatLine();

        ServerCommunicator.sendChatMessage(LanguageManager.getLanguage().get("questionPrefix")+question.getQuestion());
        ServerCommunicator.sendConsoleMessage(LanguageManager.getLanguage().get("consoleQuestionAnswered")+ question.getAnswer());
        ActiveQuizInformation.setQuestion(question);

        if(ConfigManager.getConfig().getBoolean("enableVisabilityEmptyLine")) ServerCommunicator.sendEmptyChatLine();

        PLUGIN.getServer().getScheduler().cancelTask(id);
        id = PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, ActiveQuizInformation::cancelQuestion, 20L * ConfigManager.getConfig().getInt("QuestionTimeOutDelay"));

    }

}
