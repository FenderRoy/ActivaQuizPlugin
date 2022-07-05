package shinyquizesplugin.shinyquizesplugin.Quiz.Questions;

public class CustomQuestion implements Question{

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
}
