package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.CustomQuestions;

import org.bukkit.ChatColor;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

public class CustomQuestion implements Question {

    private final String question;
    private final String answer;

    public CustomQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getFailedMessage(String highlightColor) {
        String str = LanguageManager.getLanguage().get("customQuestionCancelled");
        return java.text.MessageFormat.format(str, highlightColor+answer+ ChatColor.WHITE);
    }

    @Override
    public String toString() {
        return question;
    }
}
