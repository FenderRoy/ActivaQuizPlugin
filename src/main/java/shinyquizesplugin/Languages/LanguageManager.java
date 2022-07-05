package shinyquizesplugin.Languages;

import org.bukkit.event.Listener;
import shinyquizesplugin.Languages.defaultCreators.IDefaultLanguageCreator;
import shinyquizesplugin.Languages.defaultCreators.createDefaultEnglishLanguage;
import shinyquizesplugin.shinyquizesplugin.handlers.CheckChatMessageHandler;
import shinyquizesplugin.shinyquizesplugin.handlers.PlayerJoinHandler;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class LanguageManager {

    public static Map<String, String> languageFile = new HashMap<>();

    private static final List<IDefaultLanguageCreator> languages = Arrays.asList(
            new createDefaultEnglishLanguage(),
            new createDefaultEnglishLanguage()
    );

    public static void initialize(){
        for(IDefaultLanguageCreator language : languages){
            language.create();
        }

    }

    public static void loadLanguage(String language){

        for(IDefaultLanguageCreator languageCreator : languages){
            if(languageCreator.getFileName().equals(language)){
                Properties properties = new Properties();
                try {
                    properties.load(Files.newInputStream(Paths.get(PLUGIN.getDataFolder().getAbsolutePath()+ "/languages/"+languageCreator.getFileName())));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                for (String key : properties.stringPropertyNames()) {
                    languageFile.put(key, properties.get(key).toString());
                }

            }
        }
    }



}
