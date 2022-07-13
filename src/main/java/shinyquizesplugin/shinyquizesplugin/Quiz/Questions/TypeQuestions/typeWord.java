package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeQuestions;

import org.bukkit.ChatColor;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

public class typeWord implements Question {

    private final String word;

    public typeWord(String word){
        this.word = word;
    }
    @Override
    public String getAnswer() {
        return word;
    }

    @Override
    public String getQuestion() {
        String color = ConfigManager.getConfig().getString("HighlightedWordColor");
        String str = LanguageManager.getLanguage().get("typeQuestionAsker");
        return java.text.MessageFormat.format(str, color+word+ ChatColor.WHITE);
    }

    @Override
    public String getFailedMessage(String highlightColor) {
        return LanguageManager.getLanguage().get("typeQuestionFailed");
    }

    @Override
    public String toString() {
        return word;
    }
}
