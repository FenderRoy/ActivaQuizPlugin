package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters;

import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

import static shinyquizesplugin.shinyquizesplugin.Quiz.Questions.CustomQuestions.CustomQuestionsManager.getRandomQuestion;

public class CustomQuestionGetter implements questionGetter {

    @Override
    public Question getQuestion() {
        return getRandomQuestion();
    }
}
