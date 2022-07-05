package shinyquizesplugin.shinyquizesplugin.Quiz.Questions;

public interface Question {

    String getAnswer();

    String getQuestion();

    String getFailedMessage(String highlightColor);
}
