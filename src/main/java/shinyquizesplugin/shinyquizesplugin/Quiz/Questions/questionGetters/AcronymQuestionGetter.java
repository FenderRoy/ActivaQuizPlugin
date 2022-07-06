package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters;

import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.AcronymQuestion.AcronymQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;


public class AcronymQuestionGetter implements questionGetter {

    @Override
    public Question getQuestion() {
        return AcronymQuestionsManager.getRandomQuestion();
    }
}
