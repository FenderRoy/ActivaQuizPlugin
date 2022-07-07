package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters;

import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.MathQuestions.RandomMathQuestion;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

public class MathQuestionGetter implements questionGetter{
    @Override
    public Question getQuestion() {
        RandomMathQuestion question = new RandomMathQuestion();
        question.generateRandomValues();
        return question;
    }
}
