package shinyquizesplugin.shinyquizesplugin.Quiz.Questions;

import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;

public class typeWord implements Question{

    private final String word;

    public typeWord(String word){
        this.word = word;
    }
    @Override
    public String getAnswer() {
        return word;
    }

    @Override
    public String getQuestion() {

        String color = ConfigManager.getConfig().getString("HighlightedWordColor");
        return "type het woord "+color+word+"§f!";
    }

    @Override
    public String getFailedMessage(String highlightColor) {
        return "Helaas, niemand was snel genoeg.";
    }
}
