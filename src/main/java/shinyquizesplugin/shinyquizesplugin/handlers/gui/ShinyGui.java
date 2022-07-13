package shinyquizesplugin.shinyquizesplugin.handlers.gui;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import shinyquizesplugin.Languages.LanguageManager;
import shinyquizesplugin.shinyquizesplugin.Mangers.Messengers.ServerCommunicator;
import shinyquizesplugin.shinyquizesplugin.Quiz.ActiveQuizInformation;

import java.util.Arrays;

public abstract class ShinyGui implements Listener {


    protected ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(name);

        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void initializeBorder(Inventory inv, int pageNumber, int totalPages){
        createLine(inv,0, 9, 1);
        createLine(inv,54-9, 54,1);
        createLine(inv,0, 46, 9);
        createLine(inv,8, 54,9);

        inv.setItem(4, createGuiItem(Material.NETHER_STAR, ChatColor.AQUA+"Random", ChatColor.LIGHT_PURPLE+"Choose a random index."));
        inv.setItem(46, createGuiItem(Material.FEATHER, ChatColor.GRAY+"Previous Page.", "Current page: "+(pageNumber+1), "Total pages: "+(totalPages)));
        inv.setItem(52, createGuiItem(Material.FEATHER, ChatColor.GRAY+"Next Page.", "Current page: "+(pageNumber+1), "Total pages: "+(totalPages)));
        inv.setItem(49, createGuiItem(Material.BARRIER, ChatColor.RED+"Exit", ChatColor.LIGHT_PURPLE+"Exit this menu."));
    }

    protected void createLine(Inventory inv, int start, int end, int step){
        for(int i = start; i  < end; i+= step){
            if((i % 2) == 0){
                inv.setItem(i, createGuiItem(Material.PURPLE_STAINED_GLASS_PANE, " ",  " "));
            } else {
                inv.setItem(i, createGuiItem(Material.MAGENTA_STAINED_GLASS_PANE, " ", " "));
            }
        }
    }

    protected boolean isQuizNotActive(Player sender){
        if (ActiveQuizInformation.isActive()) {
            ServerCommunicator.sendChatMessageToPlayer((Player) sender, LanguageManager.getLanguage().get("quizActive"));
        }
        return !ActiveQuizInformation.isActive();
    }




}
