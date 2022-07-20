package shinyquizesplugin.Languages.defaultCreators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class createDefaultEnglishLanguage implements IDefaultLanguageCreator{
    @Override
    public Properties create(String version) {

        Properties p = new Properties();

        p.setProperty("_version_", version);

        p.setProperty("languageReloaded", "Language is set to English.");

        p.setProperty("language","English");
        p.setProperty("quizActive", "There is already an ongoing quiz.");
        p.setProperty("quizNotActive", "There is no quiz currently active.");

        p.setProperty("noAvailableQuestions", "There are no {0} questions..");
        p.setProperty("questionReloaded","{0} questions reloaded. Total of: {1} {0} questions.");
        p.setProperty("rewardsReloaded","Rewards reloaded. Total of: {0} rewards.");

        p.setProperty("winAmountSelf","You have won {0} times total.");
        p.setProperty("winAmountOther","{0} has won {1} times total.");
        p.setProperty("noValidPlayer","Please enter a valid user.");

        p.setProperty("correctAnswer", "{0} answered correctly!");
        p.setProperty("extraReward", "You got an extra reward!");

        p.setProperty("mostQuizWins", "Most quiz wins: ");

        p.setProperty("questionAnnouncement", "There will be a question in {0} seconds, get ready!");

        p.setProperty("customQuestionCancelled","Noone answered correctly. The correct answer was: {0}.");
        p.setProperty("mathQuestionCancelled","I guess this one was too hard, the answer was: {0}.");
        p.setProperty("shuffledQuestionCancelled","Noone deciphered it.. The word was: {0}.");
        p.setProperty("typeQuestionFailed","Noone was fast enough.");
        p.setProperty("AcronymQuestionFailed","Noone knew this one, it means: {0}");

        p.setProperty("mathQuestionAsker","What is {0}?");
        p.setProperty("shuffledQuestionAsker","Decipher the word {0}.");
        p.setProperty("typeQuestionAsker","Type the word {0} as fast as possible!");
        p.setProperty("AcronymQuestionAsker","What is the meaning of the acronym {0}?");

        p.setProperty("questionPrefix","Quickly! ");
        p.setProperty("consoleQuestionAnswered","The correct answer is: ");

        p.setProperty("fileCreated", "{0} questions file created.");
        p.setProperty("fileCreatedFailed", "{0} questions file couldn't be created.");
        p.setProperty("questionsLoaded", "{0} questions loaded. Total of {1}.");
        p.setProperty("questionsLoadedFailed", "{0} questions couldn't be loaded.");

        p.setProperty("noConsoleCommand","This command can't be executed from within console.");
        try {
            p.store(Files.newOutputStream(Paths.get(PLUGIN.getDataFolder().getAbsolutePath()+"/languages/"+getFileName())), "The english language file.");
            return p;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getFileName() {
        return "english.properties";
    }
}
