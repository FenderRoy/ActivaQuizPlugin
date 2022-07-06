package shinyquizesplugin.Languages.defaultCreators;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class createDefaultDutchLangues implements IDefaultLanguageCreator{
    @Override
    public void create() {

        Properties p = new Properties();


        p.setProperty("languageReloaded", "De taal is gezet naar het Nederlands");

        p.setProperty("language","Nederlands");
        p.setProperty("quizActive", "Er is al een quiz bezig!");
        p.setProperty("quizNotActive", "Er is geen actieve vraag.");

        p.setProperty("noAvailableQuestions", "Er zijn geen {0} vragen.");
        p.setProperty("questionReloaded","{0} vragen ge-reload. Totaal van: {1} {0} vragen.");
        p.setProperty("rewardsReloaded","Beloningen ge-reload. Totaal van: {0} beloningen.");

        p.setProperty("winAmountSelf","Jij hebt in totaal {0} keer gewonnen!");
        p.setProperty("winAmountOther","{0} heeft in totaal {1} keer gewonnen!");
        p.setProperty("noValidPlayer","Vul een geldige player in.");

        p.setProperty("correctAnswer", "{0} heeft het correct!");

        p.setProperty("mostQuizWins", "Meeste quiz wins: ");

        p.setProperty("questionAnnouncement", "Er komt een vraag over {0} seconden.");

        p.setProperty("customQuestionCancelled","Helaas, niemand heeft het geraden. Het juiste antwoord was: {0}.");
        p.setProperty("mathQuestionCancelled","Helaas, was net iets te moeilijk, het juiste antwoord was: {0}.");
        p.setProperty("shuffledQuestionCancelled","Helaas, niemand heeft het ontcijferd. Het woord was: {0}.");
        p.setProperty("typeQuestionFailed","Helaas, niemand was snel genoeg.");
        p.setProperty("AcronymQuestionFailed","Helaas, niemand wist de betekenis, het is: {0}");

        p.setProperty("mathQuestionAsker","Wat is {0}?");
        p.setProperty("shuffledQuestionAsker","Ontcijfer het woord {0}.");
        p.setProperty("typeQuestionAsker","Type het woord {0} zo snel mogelijk!");
        p.setProperty("AcronymQuestionAsker","Wat staat de afkorting {0} voor?");

        p.setProperty("questionPrefix","Snel! ");
        p.setProperty("consoleQuestionAnswered","Het juiste antwoord is: ");

        p.setProperty("fileCreated", "{0} vragen bestand aangemaakt.");
        p.setProperty("fileCreatedFailed", "{0} vragen bestand kon niet aangemaakt worden.");
        p.setProperty("questionsLoaded", "{0} vragen geladen. Totaal van: {1}");
        p.setProperty("questionsLoadedFailed", "{0} vragen niet kunnen inladen.");

        p.setProperty("noConsoleCommand","Deze command kan niet uitgevoerd worden vanuit de console.");

        try {
            p.store(Files.newOutputStream(Paths.get(PLUGIN.getDataFolder().getAbsolutePath()+"/languages/"+getFileName())), "The Dutch language file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getFileName() {
        return "dutch.properties";
    }
}
