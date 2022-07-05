package shinyquizesplugin.shinyquizesplugin.Commands.ActivaCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.LeaderboardManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.questionGetters.RandomQuestionManager;
import shinyquizesplugin.shinyquizesplugin.rewards.RewardManager;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class ShinyQuizesReloadCommand implements ShinyQuizesCommand {

    private CommandSender sender;
    @Override
    public boolean executeCommand(CommandSender sender, String label, String[] args) {
        this.sender = sender;
        if(args.length <= 1) {
            reloadAll();
            sendMessage("Shiny Quizes reloaded.");
            return true;
        }

        switch(args[1].toLowerCase()){
            case "all":
                reloadAll();
                sendMessage("Shiny Quizes reloaded.");
                break;
            case "config":
                reloadConfig();
                sendMessage("Config reloaded.");
                break;
            case "rewards":
                reloadRewards();
                sendMessage("Rewards reloaded. Total of: "+RewardManager.rewardList.size()+" rewards.");
                break;
            case "customquestions":
                reloadCustomQuestions();
                sendMessage("Custom questions reloaded. Total of: "+CustomQuestionsManager.getCustomQuestionList().size()+" custom questions.");
                break;
            case "shuffledwords":
                reloadShuffledWordQuestions();
                sendMessage("Shuffled word questions reloaded. Total of: "+ShuffledWordQuestionManager.shuffledWordListOriginal.size()+" shuffled questions.");
                break;
            case "typewords":
                reloadTypeWordQuestions();
                sendMessage("Type words reloaded. Total of: "+TypeWordQuestionManager.typeWordList.size()+" type words.");
                break;
        }
        return true;
    }

    private void sendMessage(String message){
        if(sender instanceof Player) {
            ServerCommunicator.sendChatMessageToPlayer((Player) sender, message);
        } else {
            ServerCommunicator.sendConsoleMessage(message);
        }
    }

    private void reloadConfig(){
        PLUGIN.reloadConfig();

        PLUGIN.saveDefaultConfig();
        ConfigManager.setConfig(PLUGIN.getConfig());
        ConfigManager.getConfig().options().copyDefaults(true);
        PLUGIN.saveConfig();
        LeaderboardManager.initialize();
        QuestionAskerManager.start();

        ServerCommunicator.initialize();
        RandomQuestionManager.initialize();
    }

    private void reloadCustomQuestions(){
        CustomQuestionsManager.getCustomQuestionsFromFile();
    }

    private void reloadShuffledWordQuestions(){
        ShuffledWordQuestionManager.initialize();
    }

    private void reloadTypeWordQuestions(){
        TypeWordQuestionManager.initialize();
    }

    private void reloadRewards(){
        RewardManager.initializeRewards();
    }

    private void reloadAll(){
        CustomQuestionsManager.getCustomQuestionsFromFile();
        ShuffledWordQuestionManager.initialize();
        TypeWordQuestionManager.initialize();

        PLUGIN.reloadConfig();

        PLUGIN.saveDefaultConfig();
        ConfigManager.setConfig(PLUGIN.getConfig());
        ConfigManager.getConfig().options().copyDefaults(true);
        PLUGIN.saveConfig();
        LeaderboardManager.initialize();

        QuestionAskerManager.start();


        RewardManager.initializeRewards();
        ServerCommunicator.initialize();
        RandomQuestionManager.initialize();
    }
}
