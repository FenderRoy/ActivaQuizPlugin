package shinyquizesplugin.shinyquizesplugin.Quiz.Questions;

import shinyquizesplugin.Languages.LanguageManager;

public abstract class QuestionManager {


    public static String fileCreated(String name){
        return getString("fileCreated",name);
    }

    public static String fileCreatedFailed(String name){
        return getString("fileCreatedFailed",name);
    }

    public static String questionsLoaded(String name, int amount){
        return getString("questionsLoaded",name,amount);
    }

    public static String questionsLoadedFailed(String name){
        return getString("questionsLoadedFailed",name);
    }

    private static String getString(String path, Object... args){
        String str = LanguageManager.getLanguage().get(path);
        return java.text.MessageFormat.format(str, args);
    }

}
