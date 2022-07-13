package shinyquizesplugin.shinyquizesplugin.handlers.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import shinyquizesplugin.shinyquizesplugin.Commands.ShinyCommands.ShinyQuizAskQuestionCommand;
import shinyquizesplugin.shinyquizesplugin.Mangers.QuestionAskerManager;

public class ShinyQuizesGUI extends ShinyGui implements Listener {
    private static final Inventory inv = Bukkit.createInventory(null, 45, "Shiny Quizes");;

    public ShinyQuizesGUI() {
        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(4, createGuiItem(Material.PHANTOM_MEMBRANE, ChatColor.AQUA+"Acronym Question", ChatColor.LIGHT_PURPLE+"Ask an Acronym question."));
        inv.setItem(11, createGuiItem(Material.BOOK, ChatColor.AQUA+"Custom Question", ChatColor.LIGHT_PURPLE+"Ask a Custom question."));
        inv.setItem(15, createGuiItem(Material.REDSTONE, ChatColor.AQUA+"Math Question", ChatColor.LIGHT_PURPLE+"Ask a Math question."));
        inv.setItem(22, createGuiItem(Material.BARRIER, ChatColor.RED+"Exit", ChatColor.LIGHT_PURPLE+"Exits this menu."));
        inv.setItem(29, createGuiItem(Material.WRITABLE_BOOK, ChatColor.AQUA+"Type Question", ChatColor.LIGHT_PURPLE+"Ask a Type question."));
        inv.setItem(33, createGuiItem(Material.QUARTZ, ChatColor.AQUA+"Shuffled Question", ChatColor.LIGHT_PURPLE+"Ask a Shuffled question."));
        inv.setItem(40, createGuiItem(Material.NETHER_STAR, ChatColor.AQUA+"Random Question", ChatColor.LIGHT_PURPLE+"Asks a random question."));
    }

    public void openInventory(final HumanEntity ent) {
        if(ent.hasPermission("ShinyQuizes.askQuestions")) ent.openInventory(inv);
    }

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getInventory().equals(inv)) return;

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        if (clickedItem == null || clickedItem.getType().isAir()) return;

        final Player p = (Player) e.getWhoClicked();

        // Using slots click is a best option for your inventory click's

        switch(e.getRawSlot()){
            case 4:
                p.closeInventory();
                new AcronymWordGui().openInventory(p);
                return;
            case 11:
                p.closeInventory();
                new CustomWordGui().openInventory(p);
                return;
            case 15:
                p.closeInventory();
                new mathQuestionGUI().openInventory(p);
                return;
            case 29:
                p.closeInventory();
                new TypeWordGui().openInventory(p);
                return;
            case 33:
                p.closeInventory();
                new ShuffledWordGui().openInventory(p);
                return;
            case 40:
                QuestionAskerManager.askRandomQuestion();
                break;
        }

        p.closeInventory();

    }

    private void askQuestion(Player player, String question){
        ShinyQuizAskQuestionCommand q = new ShinyQuizAskQuestionCommand();
        String[] str = new String[]{"", question};
        q.executeCommand(player, null,str);
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }
}
