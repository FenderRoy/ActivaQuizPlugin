package shinyquizesplugin.shinyquizesplugin.Commands.ShinyCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;
import shinyquizesplugin.shinyquizesplugin.Quiz.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.QuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.RandomMathQuestion;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeWordQuestionManager;

public class ShinyQuizAskQuestionCommand implements ShinyQuizesCommand{
    @Override
    public boolean executeCommand(CommandSender sender, String label, String[] args) {
        if (ActiveQuizInformation.isActive()) {
            if(sender instanceof Player) {
                ServerCommunicator.sendChatMessageToPlayer((Player) sender, LanguageManager.getLanguage().get("quizActive"));
            } else {
                ServerCommunicator.sendConsoleMessage(LanguageManager.getLanguage().get("quizActive"));
            }
            return true;
        }

        if(args.length <= 1) {
            QuestionAskerManager.askRandomQuestion();
            return true;
        }

        switch(args[1].toLowerCase()){
            case "customquestion":
                askCustomQuestion(sender);
                break;
            case "mathquestion":
                askMathQuestion(args);
                break;
            case "shuffledquestion":
                askShuffledQuestion(sender);
                break;
            case "typequestion":
                askTypeQuestion(sender);
                break;
        }
        return true;
    }

    private void askCustomQuestion(CommandSender sender){
        if(!CustomQuestionsManager.getCustomQuestionList().isEmpty()){
            QuestionManager.createQuestion(CustomQuestionsManager.getRandomQuestion());
        } else {
            if(sender instanceof Player) ServerCommunicator.sendChatMessageToPlayer((Player) sender, getMessageString("custom"));
            else ServerCommunicator.sendConsoleMessage(getMessageString("custom"));
        }
    }
    private void askMathQuestion(String[] args){

        RandomMathQuestion question = new RandomMathQuestion();

        if(args.length <= 2) {
            question.generateRandomValues();
        } else {
            switch(args[2].toLowerCase()){
                case "plus":
                    question.generateRandomValues(1);
                    break;
                case "minus":
                    question.generateRandomValues(2);
                    break;
                case "multiply":
                    question.generateRandomValues(3);
                    break;
                case "random":
                    question.generateRandomValues();
                    break;
            }
        }
        QuestionManager.createQuestion(question);
    }

    private void askShuffledQuestion(CommandSender sender){
        if(!ShuffledWordQuestionManager.shuffledWordListOriginal.isEmpty()){
            QuestionManager.createQuestion(ShuffledWordQuestionManager.getRandomQuestion());
        } else {
            if(sender instanceof Player) ServerCommunicator.sendChatMessageToPlayer((Player) sender, getMessageString("shuffled"));
            else ServerCommunicator.sendConsoleMessage(getMessageString("shuffled"));
        }
    }

    private void askTypeQuestion(CommandSender sender){
        if(!TypeWordQuestionManager.typeWordListOriginal.isEmpty()){
            QuestionManager.createQuestion(TypeWordQuestionManager.getRandomQuestion());
        } else {
            if(sender instanceof Player) ServerCommunicator.sendChatMessageToPlayer((Player) sender, getMessageString("type"));
            else ServerCommunicator.sendConsoleMessage(getMessageString("type"));
        }
    }

    private String getMessageString(String type){
        String message = LanguageManager.getLanguage().get("noAvailableQuestions");
        return java.text.MessageFormat.format(message, type);
    }

}
