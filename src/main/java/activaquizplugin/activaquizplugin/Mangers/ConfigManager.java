package activaquizplugin.activaquizplugin.Mangers;

import activaquizplugin.activaquizplugin.Mangers.Messengers.ServerCommunicator;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import static activaquizplugin.activaquizplugin.ActivaQuizPlugin.PLUGIN;

public class ConfigManager {

    private static FileConfiguration config;

    private static void writeConfig(){
        config.addDefault("ChatPrefix", "Activa Kingdoms");
        config.addDefault("PlusGetalMaxValue", 150);
        config.addDefault("MinGetalMaxValue", 100);
        config.addDefault("MinSomAntwoordMagInDeMin", true);
        config.addDefault("KeerGetalMaxValue", 25);
        config.addDefault("VraagTimeOutDelay", 30);
        config.addDefault("DelayTussenVragen", 300);
        config.addDefault("ProcentKansOpVraag", 100);
        config.addDefault("enableWiskundeVragen", true);
        config.addDefault("enableCustomVragen", true);
    }
    public static void initializeConfig(){

        PLUGIN.saveDefaultConfig();
        ConfigManager.config = PLUGIN.getConfig();

        writeConfig();

        config.options().copyDefaults(true);
        PLUGIN.saveConfig();

        ServerCommunicator.sendConsoleMessage(ChatColor.GREEN+"Config loaded succesfully.");
    }



    public static FileConfiguration getConfig() {
        return config;
    }

    public static void setConfig(FileConfiguration config) {
        ConfigManager.config = config;
    }
}
