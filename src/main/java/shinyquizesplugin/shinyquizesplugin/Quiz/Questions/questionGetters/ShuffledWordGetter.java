package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters;

import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions.ShuffledWordQuestionManager;

public class ShuffledWordGetter implements questionGetter{
    @Override
    public Question getQuestion() {
        return ShuffledWordQuestionManager.getRandomQuestion();
    }
}
