package shinyquizesplugin.Languages;

import shinyquizesplugin.Languages.defaultCreators.IDefaultLanguageCreator;
import shinyquizesplugin.Languages.defaultCreators.createDefaultDutchLangues;
import shinyquizesplugin.Languages.defaultCreators.createDefaultEnglishLanguage;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class LanguageManager {

    private static final Map<String, String> languageFile = new HashMap<>();

    private static final List<IDefaultLanguageCreator> languages = Arrays.asList(
            new createDefaultDutchLangues(),
            new createDefaultEnglishLanguage()
    );

    public static void initialize(){
        for(IDefaultLanguageCreator language : languages){
            language.create();
        }

    }

    public static Map<String, String> getLanguage() {
        return Collections.unmodifiableMap(languageFile);
    }

    public static void loadLanguage(String language){

        File[] files = new File(PLUGIN.getDataFolder().getAbsolutePath()+ "/languages").listFiles();

        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                System.out.println("File " + file.getName());
                System.out.println("File " + file.getName());
                System.out.println("File " + file.getName());
                System.out.println("File " + file.getName());
                System.out.println("File " + file.getName());
                if(file.getName().equals(language)){
                    languageFile.clear();
                    Properties properties = new Properties();
                    try {
                        properties.load(Files.newInputStream(Paths.get(PLUGIN.getDataFolder().getAbsolutePath()+ "/languages/"+file.getName())));
                    } catch (IOException e) {
                        ServerCommunicator.sendConsoleMessage("Loading language failed.");
                        throw new RuntimeException(e);
                    }

                    for (String key : properties.stringPropertyNames()) {
                        languageFile.put(key, properties.get(key).toString());
                    }
                    ServerCommunicator.sendConsoleMessage(properties.getProperty("languageReloaded"));
                    return;
                }
            }
        }

//        for(IDefaultLanguageCreator languageCreator : languages){
//            if(languageCreator.getFileName().equals(language)){
//                languageFile.clear();
//                Properties properties = new Properties();
//                try {
//                    properties.load(Files.newInputStream(Paths.get(PLUGIN.getDataFolder().getAbsolutePath()+ "/languages/"+languageCreator.getFileName())));
//                } catch (IOException e) {
//                    ServerCommunicator.sendConsoleMessage("Loading language failed.");
//                    throw new RuntimeException(e);
//                }
//
//                for (String key : properties.stringPropertyNames()) {
//                    languageFile.put(key, properties.get(key).toString());
//                }
//                ServerCommunicator.sendConsoleMessage(properties.getProperty("languageReloaded"));
//                return;
//            }
//        }
        ServerCommunicator.sendConsoleMessage("Loading language failed.");
    }



}
