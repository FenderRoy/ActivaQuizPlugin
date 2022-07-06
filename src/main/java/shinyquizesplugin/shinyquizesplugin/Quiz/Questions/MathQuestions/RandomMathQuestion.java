package shinyquizesplugin.shinyquizesplugin.Quiz.Questions.MathQuestions;

import org.bukkit.ChatColor;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.ConfigManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

public class RandomMathQuestion implements Question {

    private int num1;
    private int num2;
    private String operator;

    private int answer;

    public void generateRandomValues(){
        int operator = (int)(Math.random()*5)+1;
        generateRandomValues(operator);
    }

    public void generateRandomValues(int operator){
        setOperatorFromNumber(operator);
    }

    private void setOperatorFromNumber(int num){
        switch(num){
            case 1:
            case 4:
                setNumberValues(ConfigManager.getConfig().getInt("PlusQuestionMaxValue"));
                this.operator = "+";
                this.answer = num1+num2;
                break;
            case 2:
            case 5:
                setNumberValues(ConfigManager.getConfig().getInt("MinusQuestionMaxValue"), ConfigManager.getConfig().getBoolean("MinusQuestionAnswerCanBeNegative"));
                this.operator = "-";
                this.answer = num1-num2;
                break;
            case 3:
                this.operator = "*";
                setNumberValues(ConfigManager.getConfig().getInt("MultiplyQuestionMaxValue"));
                this.answer = num1*num2;
                break;
        }
    }

    private void setNumberValues(int maxValues){
        this.num1 = (int)(Math.random()*maxValues)+1;
        this.num2 = (int)(Math.random()*maxValues)+1;
    }

    private void setNumberValues(int maxValues, boolean canBeNegative){
        this.num1 = (int)(Math.random()*maxValues)+1;

        if(canBeNegative) {
            this.num2 = (int) (Math.random() * maxValues) + 1;
        } else {
            this.num2 = (int)(Math.random()*this.num1)+1;
        }
    }

    @Override
    public String getAnswer() {
        return String.valueOf(answer);
    }

    @Override
    public String getQuestion() {
        String color = ConfigManager.getConfig().getString("HighlightedWordColor");
        String str = LanguageManager.getLanguage().get("mathQuestionAsker");
        return java.text.MessageFormat.format(str, color+num1 + " "+operator+" "+num2+ChatColor.WHITE);
    }

    @Override
    public String getFailedMessage(String highlightColor) {
        String str = LanguageManager.getLanguage().get("mathQuestionCancelled");
        return java.text.MessageFormat.format(str, highlightColor+answer+ ChatColor.WHITE);
    }
}
