package shinyquizesplugin.shinyquizesplugin.Commands.ShinyCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Leaderboard.LeaderboardManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.AcronymQuestion.AcronymQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.CustomQuestions.CustomQuestionsManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions.ShuffledWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeQuestions.TypeWordQuestionManager;
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
                String msg = LanguageManager.getLanguage().get("rewardsReloaded");
                sendMessage(java.text.MessageFormat.format(msg,String.valueOf(RewardManager.rewardList.size())));
                break;
            case "customquestions":
                reloadCustomQuestions();
                sendMessage(getFormattedMessage("Custom", CustomQuestionsManager.getCustomQuestionList().size()));
                break;
            case "shuffledwords":
                reloadShuffledWordQuestions();
                sendMessage(getFormattedMessage("Shuffled", ShuffledWordQuestionManager.shuffledWordListOriginal.size()));
                break;
            case "typewords":
                reloadTypeWordQuestions();
                sendMessage(getFormattedMessage("Type", TypeWordQuestionManager.typeWordListOriginal.size()));
                break;
            case "acronymquestions":
                reloadAcronymQuestions();
                sendMessage(getFormattedMessage("Acronym",AcronymQuestionsManager.getCustomQuestionList().size()));
                break;
        }
        return true;
    }

    private String getFormattedMessage(String type, int amount){
        String str = LanguageManager.getLanguage().get("questionReloaded");
        return java.text.MessageFormat.format(str, type, String.valueOf(amount));
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

        LanguageManager.loadLanguage(ConfigManager.getConfig().getString("Language"));

        LeaderboardManager.initialize();
        QuestionAskerManager.start();

        ServerCommunicator.initialize();
        RandomQuestionManager.initialize();
    }

    private void reloadCustomQuestions(){
        CustomQuestionsManager.getCustomQuestionsFromFile();
    }

    private void reloadAcronymQuestions(){
        AcronymQuestionsManager.getCustomQuestionsFromFile();
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

    public void reloadAll(){
        CustomQuestionsManager.getCustomQuestionsFromFile();
        AcronymQuestionsManager.getCustomQuestionsFromFile();
        ShuffledWordQuestionManager.initialize();
        TypeWordQuestionManager.initialize();

        PLUGIN.reloadConfig();

        PLUGIN.saveDefaultConfig();
        ConfigManager.setConfig(PLUGIN.getConfig());
        ConfigManager.getConfig().options().copyDefaults(true);
        PLUGIN.saveConfig();
        LanguageManager.loadLanguage(ConfigManager.getConfig().getString("Language"));
        LeaderboardManager.initialize();

        QuestionAskerManager.start();


        RewardManager.initializeRewards();
        ServerCommunicator.initialize();
        RandomQuestionManager.initialize();
    }
}
