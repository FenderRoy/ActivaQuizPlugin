package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters;

import org.bukkit.configuration.file.FileConfiguration;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.AcronymQuestion.AcronymQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.CustomQuestions.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions.ShuffledWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeQuestions.TypeWordQuestionManager;

import java.util.ArrayList;
import java.util.List;

public class RandomQuestionManager {

    public static List<questionGetter> activeQuestions = new ArrayList<>();

    public static void initialize(){

        activeQuestions.clear();
        FileConfiguration config = ConfigManager.getConfig();

        if(config.getBoolean("enableMathQuestions")) activeQuestions.add(new MathQuestionGetter());
        if(config.getBoolean("enableCustomQuestions") && !CustomQuestionsManager.getCustomQuestionList().isEmpty()) activeQuestions.add(new CustomQuestionGetter());
        if(config.getBoolean("enableShuffledWordQuestions") && !ShuffledWordQuestionManager.shuffledWordList.isEmpty()) activeQuestions.add(new ShuffledWordGetter());
        if(config.getBoolean("enableTypeWordQuestions") && !TypeWordQuestionManager.typeWordList.isEmpty()) activeQuestions.add(new TypedWordGetter());
        if(config.getBoolean("enableAcronymWordQuestions") && !AcronymQuestionsManager.getCustomQuestionList().isEmpty()) activeQuestions.add(new AcronymQuestionGetter());


        String str = LanguageManager.getLanguage().get("questionReloaded");
        String formatted = java.text.MessageFormat.format(str, "Config", activeQuestions.size());
        ServerCommunicator.sendConsoleMessage(formatted);
    }

}
