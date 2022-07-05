package shinyquizesplugin.Languages;


import java.io.File;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class FileManager {

    public static void createFiles(){
        File questions = new File(PLUGIN.getDataFolder().getAbsolutePath()+"/questions/");
        questions.mkdir();
        File rewards = new File(PLUGIN.getDataFolder().getAbsolutePath()+"/rewards/");
        rewards.mkdir();
        File languages = new File(PLUGIN.getDataFolder().getAbsolutePath()+"/languages/");
        languages.mkdir();
        File playerData = new File(PLUGIN.getDataFolder().getAbsolutePath()+"/playerdata/");
        playerData.mkdir();
    }

}
