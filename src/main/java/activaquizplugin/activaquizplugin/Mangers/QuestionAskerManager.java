package activaquizplugin.activaquizplugin.Mangers;

import activaquizplugin.activaquizplugin.Quiz.ActiveQuizInformation;
import activaquizplugin.activaquizplugin.Quiz.CustomQuestionsManager;
import activaquizplugin.activaquizplugin.Quiz.QuestionManager;
import activaquizplugin.activaquizplugin.Quiz.Questions.RandomMathQuestion;

import java.util.Random;

import static activaquizplugin.activaquizplugin.ActivaQuizPlugin.PLUGIN;

public class QuestionAskerManager {

    public static boolean askQuestions = true;

    public static void start(){
        ActiveQuizInformation.cancelQuestion();
        PLUGIN.getServer().getScheduler().cancelTasks(PLUGIN);

        int delay = ConfigManager.getConfig().getInt("DelayTussenVragen");

        PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, () -> askRandomQuestionWithRepeat(delay), 20L *delay);
    }


    private static void askRandomQuestionWithRepeat(int delay){

        if(ActiveQuizInformation.isActive()) return;

        askRandomQuestion();

        PLUGIN.getServer().getScheduler().scheduleSyncDelayedTask(PLUGIN, () -> askRandomQuestionWithRepeat(delay), 20L *delay);
    }

    public static void askRandomQuestion(){
        int percentChance = ConfigManager.getConfig().getInt("ProcentKansOpVraag");
        int randomNumber = (int)(Math.random()*100) +1;

        boolean customVragen = ConfigManager.getConfig().getBoolean("enableCustomVragen");
        boolean wiskundeVragen = ConfigManager.getConfig().getBoolean("enableWiskundeVragen");


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
