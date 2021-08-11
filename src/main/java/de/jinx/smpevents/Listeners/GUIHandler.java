package de.jinx.smpevents.Listeners;

import de.jinx.smpevents.Config.ConfigManager;
import de.jinx.smpevents.SMPEvents;
import de.jinx.smpevents.ScoreboardHandler;
import de.jinx.smpevents.gui.GUI;
import de.jinx.smpevents.items.All_Items;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUIHandler implements Listener {

    @EventHandler
    public void handleWizardShop(InventoryClickEvent e){
        if(!(e.getWhoClicked() instanceof Player))
            return;
        Player p = (Player) e.getWhoClicked();

        if (!e.getView().getTitle().equals(GUI.WIZARDSHOP))
            return;

        if(e.getCurrentItem() == null)
            return;

        if(e.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE)
            e.setCancelled(true);

        if(!e.getCurrentItem().hasItemMeta())
            return;

        if(SMPEvents.getPlugin().cfM.getProgressCfg().getInt("Event.stage") > 1 && SMPEvents.getPlugin().cfM.getProgressCfg().getInt("Event.stage") < 5) {

            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eGive Fragments")) {

                if (e.getClick().equals(ClickType.SHIFT_LEFT)) {

                    for (int i = 0; i < p.getInventory().getSize(); i++) {
                        ItemStack item = p.getInventory().getItem(i);

                        if(!SMPEvents.isCustomItem(item))
                            continue;


                        if (item.getItemMeta().getLore().equals(All_Items.asteroidFragments.getItemMeta().getLore())) {

                            ConfigManager cfM = SMPEvents.getPlugin().cfM;
                            cfM.reload();
                            FileConfiguration cfg = cfM.getProgressCfg();


                            int stage = cfg.getInt("Event.stage");

                            int maxProgress = cfg.getInt("Stage"+stage+".maxProgress");

                            int progress =cfg.getInt("Event.progress");

                            int amount = item.getAmount();

                            if(cfg.getInt("Event.progress") >= maxProgress){
                                p.sendMessage("§c The maximum has been achieved!");
                                e.setCancelled(true);
                                return;
                            }

                            if((amount + progress) > maxProgress){

                                int zumAbgegben = maxProgress - progress;

                                p.getInventory().getItem(i).setAmount(amount-zumAbgegben);
                                cfM.getProgressCfg().set("Event.progress", cfM.getProgressCfg().getInt("Event.progress") + zumAbgegben);
                                p.sendMessage("You added: " +zumAbgegben);

                            }else{
                                p.getInventory().remove(item);
                                cfM.getProgressCfg().set("Event.progress", cfM.getProgressCfg().getInt("Event.progress") + amount);
                                p.sendMessage("You added: " +amount);

                            }
                            cfM.save();
                            ScoreboardHandler.updateScoreboard();
                        }
                    }
                }
            }else if(e.getCurrentItem().getItemMeta().getDisplayName().equals(All_Items.asteroidPickaxe.getItemMeta().getDisplayName())){
                if(hasAvaliableSlot(p)){
                    for (ItemStack item: p.getInventory().getContents()) {
                        if(!SMPEvents.isCustomItem(item))
                            continue;

                        if(item.getItemMeta().getLore().equals(All_Items.asteroidFragments.getItemMeta().getLore())) {
                            if(item.getAmount() >= 5) {
                                item.setAmount(item.getAmount() - 5);
                                p.getInventory().addItem(All_Items.asteroidPickaxe);
                            }
                        }
                    }
                }
            }
        }
        e.setCancelled(true);
    }

    public boolean hasAvaliableSlot(Player player){
        Inventory inv = player.getInventory();
        for (ItemStack item: inv.getContents()) {
            if(item == null) {
                return true;
            }
        }
        return false;
    }


}
