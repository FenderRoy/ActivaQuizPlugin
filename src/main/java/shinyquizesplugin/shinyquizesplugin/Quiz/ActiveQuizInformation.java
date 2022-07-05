package shinyquizesplugin.shinyquizesplugin.Quiz;

import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.Question;

public class ActiveQuizInformation {

    private static Question question;
    private static boolean active;

    public static void setQuestion(Question question) {
        ActiveQuizInformation.question = question;
        setActive(true);
    }

    public static void cancelQuestion(){
        if(active){
            ServerCommunicator.sendChatMessage("Helaas! Niemand heeft het geraden. Het juiste antwoord was: "+getQuestion().getAnswer()+".");
            active = false;
            question = null;
        }
    }
    public static void setActive(boolean active) {
        ActiveQuizInformation.active = active;
    }

    public static boolean isActive() {
        return active;
    }

    public static Question getQuestion() {
        return question;
    }
}
