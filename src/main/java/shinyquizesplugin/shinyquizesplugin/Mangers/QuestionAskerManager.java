package shinyquizesplugin.shinyquizesplugin.Mangers;

import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;
import shinyquizesplugin.shinyquizesplugin.Quiz.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.QuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.RandomMathQuestion;

import java.util.Random;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class QuestionAskerManager {


    private static int percentChance;
    public static void start(){
        ActiveQuizInformation.cancelQuestion();
        PLUGIN.getServer().getScheduler().cancelTasks(PLUGIN);

        int delay = ConfigManager.getConfig().getInt("DelayBetweenQuestions");
        percentChance = ConfigManager.getConfig().getInt("PercentChanceForQuestion");

        if(ConfigManager.getConfig().getBoolean("enableRandomQuestions")) {
            PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, () -> askRandomQuestionWithRepeat(delay), 20L *delay);
        }
    }


    private static void askRandomQuestionWithRepeat(int delay){

        if(ActiveQuizInformation.isActive()) return;

        askRandomQuestion(percentChance);

        PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, () -> askRandomQuestionWithRepeat(delay), 20L *delay);
    }

    public static void askRandomQuestion(int chance){
        int randomNumber = (int)(Math.random()*100) +1;

        if(randomNumber > chance) return;

        boolean customVragen = ConfigManager.getConfig().getBoolean("enableCustomQuestions");
        boolean wiskundeVragen = ConfigManager.getConfig().getBoolean("enableMathQuestions");

        if(CustomQuestionsManager.getCustomQuestionList().isEmpty()) customVragen = false;

        if(customVragen && wiskundeVragen) {
            Random random = new Random();

            if (random.nextBoolean()) {
                QuestionManager.createQuestion(CustomQuestionsManager.getRandomQuestion());
            } else {
                RandomMathQuestion question = new RandomMathQuestion();
                question.generateRandomValues();
                QuestionManager.createQuestion(question);
            }
        } else if (customVragen){
            QuestionManager.createQuestion(CustomQuestionsManager.getRandomQuestion());
        } else if (wiskundeVragen){
            RandomMathQuestion question = new RandomMathQuestion();
            question.generateRandomValues();
            QuestionManager.createQuestion(question);
        }

    }
}
