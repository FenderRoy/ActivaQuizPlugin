package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.AcronymQuestion;

import org.bukkit.ChatColor;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

public class AcronymQuestion implements Question {

    private final String acronym;
    private final String answer;

    public AcronymQuestion(String acronym, String answer) {
        this.acronym = acronym;
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    public String getAcronym() {
        return acronym;
    }

    @Override
    public String getQuestion() {
        String color = ConfigManager.getConfig().getString("HighlightedWordColor");
        String str = LanguageManager.getLanguage().get("AcronymQuestionAsker");
        return java.text.MessageFormat.format(str, color+acronym+ChatColor.WHITE);
    }

    @Override
    public String getFailedMessage(String highlightColor) {
        String str = LanguageManager.getLanguage().get("AcronymQuestionFailed");
        return java.text.MessageFormat.format(str, highlightColor+answer+ ChatColor.WHITE);
    }

    @Override
    public String toString() {
        return answer;
    }
}
