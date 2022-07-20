package shinyquizesplugin.shinyquizesplugin.handlers.gui;

import org.bukkit.Bukkit;
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
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions.ShuffledWordQuestionManager;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.ShuffledQuestions.shuffledWord;
import shinyquizesplugin.shinyquizesplugin.Quiz.Questions.TypeQuestions.TypeWordQuestionManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShuffledWordGui extends ShinyGui implements Listener {

    private static final Inventory inv = Bukkit.createInventory(null, 54, "Ask a Shuffled question.");
    private static final Map<Integer, shuffledWord> indexToWordList = new HashMap<>();
    private static int totalPages;
    private static int pageNumber;

    public ShuffledWordGui(){
        pageNumber = 0;
        totalPages = (int) Math.ceil(ShuffledWordQuestionManager.shuffledWordListOriginal.size()/27f);
        drawPage(0);
    }
    public void initializeItems() {
        List<shuffledWord> words = ShuffledWordQuestionManager.shuffledWordListOriginal;

        int index = 27 * pageNumber;
        for(int i = 27*pageNumber; i < words.size(); i++){
            int xx = inv.firstEmpty();
            inv.addItem(createGuiItem(Material.MAP, words.get(i).toString(), ""));
            indexToWordList.put(xx, words.get(i));
            index++;
            if(index > 27*(pageNumber+1)) return;
        }

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

        switch(e.getRawSlot()){
            case 49:
                p.closeInventory();
                new ShinyQuizesGUI().openInventory(p);
                break;
            case 46:
                if(pageNumber > 0) {
                    drawPage(--pageNumber);
                }
                break;
            case 52:
                if(pageNumber < totalPages-1) {
                    drawPage(++pageNumber);
                }
                break;
            case 4:
                if(isQuizNotActive(p)) QuestionManager.createQuestion(TypeWordQuestionManager.getRandomQuestion());
                p.closeInventory();
                break;
        }


        if(indexToWordList.get(e.getRawSlot()) != null){
            if(isQuizNotActive(p)) QuestionManager.createQuestion(indexToWordList.get(e.getRawSlot()));
            p.closeInventory();
        }


    }

    private void drawPage(int page){
        this.pageNumber=page;
        inv.clear();
        indexToWordList.clear();
        initializeBorder(inv, pageNumber, totalPages);
        initializeItems();
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            e.setCancelled(true);
        }
    }

}
