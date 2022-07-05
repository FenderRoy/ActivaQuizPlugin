package shinyquizesplugin.shinyquizesplugin.Quiz.Questions;

import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;

import java.util.Random;

public class shuffledWord implements Question{

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
        return "Eerste persoon die het woord: "+color+scramble(word)+"§f ontcijferd wint!";
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
        return "Helaas, niemand heeft het ontcijferd. Het woord was: "+highlightColor+getAnswer()+"§f.";
    }
}
