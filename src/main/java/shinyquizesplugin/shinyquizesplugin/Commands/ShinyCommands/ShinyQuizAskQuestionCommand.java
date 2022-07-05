package shinyquizesplugin.shinyquizesplugin.Commands.ShinyCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
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
                ServerCommunicator.sendChatMessageToPlayer((Player) sender, "Er is al een quiz bezig!");
            } else {
                ServerCommunicator.sendConsoleMessage("Er is al een quiz bezig!");
            }
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
            if(sender instanceof Player) ServerCommunicator.sendChatMessageToPlayer((Player) sender, "Er zijn geen custom vragen.");
            else ServerCommunicator.sendConsoleMessage("Er zijn geen custom vragen.");
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
            if(sender instanceof Player) ServerCommunicator.sendChatMessageToPlayer((Player) sender, "Er zijn geen shuffled vragen.");
            else ServerCommunicator.sendConsoleMessage("Er zijn geen shuffled vragen.");
        }
    }

    private void askTypeQuestion(CommandSender sender){
        if(!TypeWordQuestionManager.typeWordListOriginal.isEmpty()){
            QuestionManager.createQuestion(TypeWordQuestionManager.getRandomQuestion());
        } else {
            if(sender instanceof Player) ServerCommunicator.sendChatMessageToPlayer((Player) sender, "Er zijn geen type vragen.");
            else ServerCommunicator.sendConsoleMessage("Er zijn geen type vragen.");
        }
    }

}
