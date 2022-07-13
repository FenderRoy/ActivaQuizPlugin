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
import shinyquizesplugin.shinyquizesplugin.Quiz.QuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.MathQuestions.RandomMathQuestion;

public class mathQuestionGUI extends ShinyGui implements Listener {
    private static final Inventory inv = Bukkit.createInventory(null, 18, "Ask a math question");

    public mathQuestionGUI(){
        initializeItems();
    }

    public void initializeItems() {
        inv.setItem(1, createGuiItem(Material.REDSTONE, ChatColor.AQUA+"Plus Question", ChatColor.LIGHT_PURPLE+"Ask a plus question."));
        inv.setItem(3, createGuiItem(Material.REDSTONE_TORCH, ChatColor.AQUA+"Minus Question", ChatColor.LIGHT_PURPLE+"Ask a minus question."));
        inv.setItem(5, createGuiItem(Material.COMPARATOR, ChatColor.AQUA+"Multiply Question", ChatColor.LIGHT_PURPLE+"Ask a mulitply question."));
        inv.setItem(7, createGuiItem(Material.NETHER_STAR, ChatColor.AQUA+"Random Question", ChatColor.LIGHT_PURPLE+"Ask a random math question."));
        inv.setItem(13, createGuiItem(Material.BARRIER, ChatColor.RED+"Exit", ChatColor.LIGHT_PURPLE+"Exits this menu."));
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
        RandomMathQuestion question = new RandomMathQuestion();
        switch(e.getRawSlot()){
            case 1:
                question.generateRandomValues(1);
                break;
            case 3:
                question.generateRandomValues(2);
                break;
            case 5:
                question.generateRandomValues(3);
                break;
            case 7:
                question.generateRandomValues();
                break;
        }

        if(e.getRawSlot() != 13){

            if(isQuizNotActive(p)) QuestionManager.createQuestion(question);
            p.closeInventory();
        } else {
            p.closeInventory();
            new ShinyQuizesGUI().openInventory(p);
        }





    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }


}
