package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters;

import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeQuestions.TypeWordQuestionManager;

public class TypedWordGetter implements questionGetter{
    @Override
    public Question getQuestion() {
        return TypeWordQuestionManager.getRandomQuestion();
    }
}
