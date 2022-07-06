package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions;

import org.bukkit.ChatColor;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

import java.util.Random;

public class shuffledWord implements Question {

    private final String word;

    public shuffledWord(String word){
        this.word = word;
    }

    @Override
    public String getAnswer() {
        return word;
    }

    @Override
    public String getQuestion() {
        String color = ConfigManager.getConfig().getString("HighlightedWordColor");
        String str = LanguageManager.getLanguage().get("shuffledQuestionAsker");
        return java.text.MessageFormat.format(str, color+scramble(word)+ChatColor.WHITE);
    }

    private String scramble(String inputString)
    {
        Random random = new Random();
        // Convert your string into a simple char array:
        char[] a = inputString.toCharArray();

        // Scramble the letters using the standard Fisher-Yates shuffle,
        for( int i=0 ; i<a.length ; i++ )
        {
            int j = random.nextInt(a.length);
            // Swap letters
            char temp = a[i]; a[i] = a[j];  a[j] = temp;
        }

        return new String(a);
    }

    @Override
    public String getFailedMessage(String highlightColor) {
        String str = LanguageManager.getLanguage().get("shuffledQuestionCancelled");
        return java.text.MessageFormat.format(str, highlightColor+word+ ChatColor.WHITE);
    }
}
