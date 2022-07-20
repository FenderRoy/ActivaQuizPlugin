package shinyquizesplugin.Languages;

import org.bukkit.ChatColor;
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

    private static final String CURRENT_LANGUAGE_VERSION = "1.1";

    private static final List<IDefaultLanguageCreator> languages = Arrays.asList(
            new createDefaultEnglishLanguage(),
            new createDefaultDutchLangues()

    );

    public static void initialize(){
        for(IDefaultLanguageCreator language : languages){
            language.create(CURRENT_LANGUAGE_VERSION);
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
                if(file.getName().equals(language)){
                    languageFile.clear();
                    Properties properties = new Properties();
                    try {
                        properties.load(Files.newInputStream(Paths.get(PLUGIN.getDataFolder().getAbsolutePath()+ "/languages/"+file.getName())));
                    } catch (IOException e) {
                        ServerCommunicator.sendConsoleMessage("Loading language failed.");
                        throw new RuntimeException(e);
                    }

                    try{
                        if(!properties.getProperty("_version_").equals(CURRENT_LANGUAGE_VERSION)){
                            wrongVersionError();
                            return;
                        }
                    } catch (NullPointerException e){
                        wrongVersionError();
                        return;
                    }


                    for (String key : properties.stringPropertyNames()) {
                        languageFile.put(key, properties.get(key).toString());
                    }
                    ServerCommunicator.sendConsoleMessage(properties.getProperty("languageReloaded"));
                    return;
                }
            }
        }

        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"Loading language failed.");
        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"Without a language file this plugin doesn't work. Please enter a valid language file in the config.");
        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"Without a valid language file you will get errors in the console.");
        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"To fix, please enter a valid language file.");
    }

    private static void wrongVersionError() {
        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"Your selected language file is not up to date. Defaulted to English.");
        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"If you are using an officially supported language please restart the server.");
        ServerCommunicator.sendConsoleMessage(ChatColor.RED+"If you are using a custom made language please add the missing sentences and update the _version_ variable to: "+CURRENT_LANGUAGE_VERSION+" and reload the plugin.");
        Properties defaultProperties = new createDefaultEnglishLanguage().create(CURRENT_LANGUAGE_VERSION);
        for(String key : defaultProperties.stringPropertyNames()) {
            languageFile.put(key, defaultProperties.get(key).toString());
        }
    }


}
