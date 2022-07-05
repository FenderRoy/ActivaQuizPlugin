package shinyquizesplugin.shinyquizesplugin.Leaderboard;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static shinyquizesplugin.shinyquizesplugin.ShinyQuizesPlugin.PLUGIN;

public class PlayerWinManager {

    public static Map<UUID, Integer> playerWinData = new HashMap<>();

    public static int getValue(UUID id){
        playerWinData.putIfAbsent(id, 0);
        return playerWinData.get(id);
    }

    public static void addWin(Player player, int amount){
        UUID uuid = player.getUniqueId();
        playerWinData.putIfAbsent(uuid, 0);

        int currentAmount = playerWinData.get(uuid);
        int newAmount = currentAmount + amount;
        playerWinData.put(uuid, newAmount);
    }

    public static void removeWin(Player player, int amount){
        UUID uuid = player.getUniqueId();
        if(playerWinData.get(uuid) != null){
            int currentAmount = playerWinData.get(uuid);
            int newAmount = currentAmount - amount;
            playerWinData.put(uuid, newAmount);
        } else playerWinData.put(uuid, 0);
    }

    public static void setWins(Player player, int amount){
        playerWinData.put(player.getUniqueId(), amount);
    }

    public static int getWins(Player player){
        playerWinData.putIfAbsent(player.getUniqueId(), 0);
        return playerWinData.get(player.getUniqueId());
    }


    public static void saveWinValues(){
        Properties properties = new Properties();

        for (Map.Entry<UUID, Integer> entry : playerWinData.entrySet()) {
            properties.put(entry.getKey().toString(), entry.getValue().toString());
        }


        try {
            properties.store(Files.newOutputStream(Paths.get(PLUGIN.getDataFolder().getAbsolutePath()+ "/playerWinData.properties")), null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadWinValues(){
        if(!checkIfFileExists()) return;
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(PLUGIN.getDataFolder().getAbsolutePath()+ "/playerWinData.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String key : properties.stringPropertyNames()) {
            playerWinData.put(UUID.fromString(key), Integer.parseInt(properties.get(key).toString()));
        }
    }

    private static boolean checkIfFileExists(){
        File tempFile = new File(PLUGIN.getDataFolder().getAbsolutePath()+ "/playerWinData.properties");
        return tempFile.exists();
    }

}
