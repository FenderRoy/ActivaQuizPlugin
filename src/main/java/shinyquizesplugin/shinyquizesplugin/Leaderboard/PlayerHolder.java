package shinyquizesplugin.shinyquizesplugin.Leaderboard;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerHolder implements Comparable<PlayerHolder> {

    private final OfflinePlayer player;
    private final int wins;

    public PlayerHolder(OfflinePlayer player, int wins){
        this.player = player;
        this.wins = wins;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public int getWins() {
        return wins;
    }

    @Override
    public int compareTo(PlayerHolder o) {
        return Integer.compare(o.wins, wins);
    }
}
