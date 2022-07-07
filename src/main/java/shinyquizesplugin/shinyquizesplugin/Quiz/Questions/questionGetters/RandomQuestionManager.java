package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters;

import org.bukkit.configuration.file.FileConfiguration;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.AcronymQuestion.AcronymQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.CustomQuestions.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions.ShuffledWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeQuestions.TypeWordQuestionManager;

import java.util.ArrayList;
import java.util.List;

public class RandomQuestionManager {

    private static List<questionGetter> activeQuestionsOriginal = new ArrayList<>();
    public static List<questionGetter> activeQuestions = new ArrayList<>();

    public static void initialize(){

        activeQuestions.clear();
        activeQuestionsOriginal.clear();

        FileConfiguration config = ConfigManager.getConfig();
        int activeQuetsionsAmount = 0;

        if(config.getBoolean("enableMathQuestions")) {
            activeQuestions.add(new MathQuestionGetter());
        }
        if(config.getBoolean("enableCustomQuestions") && !CustomQuestionsManager.getCustomQuestionList().isEmpty()) {
            activeQuestions.add(new CustomQuestionGetter());
            activeQuetsionsAmount+=CustomQuestionsManager.getCustomQuestionList().size();
        }
        if(config.getBoolean("enableShuffledWordQuestions") && !ShuffledWordQuestionManager.shuffledWordList.isEmpty()) {
            activeQuestions.add(new ShuffledWordGetter());
            activeQuetsionsAmount+=ShuffledWordQuestionManager.shuffledWordList.size();
        }
        if(config.getBoolean("enableTypeWordQuestions") && !TypeWordQuestionManager.typeWordList.isEmpty()) {
            activeQuestions.add(new TypedWordGetter());
            activeQuetsionsAmount+=TypeWordQuestionManager.typeWordList.size();
        }
        if(config.getBoolean("enableAcronymWordQuestions") && !AcronymQuestionsManager.getCustomQuestionList().isEmpty()) {
            activeQuestions.add(new AcronymQuestionGetter());
            activeQuetsionsAmount+=AcronymQuestionsManager.getCustomQuestionList().size();
        }

        activeQuestionsOriginal.addAll(activeQuestions);

        String str = LanguageManager.getLanguage().get("questionReloaded");
        String formatted = java.text.MessageFormat.format(str, "ActiveTypes", activeQuestions.size());
        String formatted2 = java.text.MessageFormat.format(str, "Active", activeQuetsionsAmount);
        ServerCommunicator.sendConsoleMessage(formatted);
        ServerCommunicator.sendConsoleMessage(formatted2);
    }

    private static int previousQuestion = -1;
    public static Question generateRandomQuestion() {
        if (ConfigManager.getConfig().getBoolean("enableRepeatingQuestionTypeProtection")) {
            if (activeQuestionsOriginal.size() > 3) {
                if (activeQuestions.size() > 2) {
                    int index = (int) (Math.random() * RandomQuestionManager.activeQuestions.size());
                    Question question = RandomQuestionManager.activeQuestions.get(index).getQuestion();
                    activeQuestions.remove(index);
                    return question;
                } else {
                    activeQuestions.clear();
                    activeQuestions.addAll(activeQuestionsOriginal);
                    return generateRandomQuestion();
                }
            } else {
                int index = (int) (Math.random() * RandomQuestionManager.activeQuestionsOriginal.size());
                while (index == previousQuestion && activeQuestionsOriginal.size() > 1) {
                    index = (int) (Math.random() * RandomQuestionManager.activeQuestionsOriginal.size());
                }
                previousQuestion = index;
                return RandomQuestionManager.activeQuestionsOriginal.get(index).getQuestion();
            }
        } else {
            int index = (int) (Math.random() * RandomQuestionManager.activeQuestionsOriginal.size());
            return RandomQuestionManager.activeQuestionsOriginal.get(index).getQuestion();
        }
    }

}
