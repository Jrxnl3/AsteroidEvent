package de.jinx.smpevents.gui;

import de.jinx.smpevents.SMPEvents;
import de.jinx.smpevents.items.All_Items;
import de.jinx.smpevents.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class GUI {

    public static final String WIZARDSHOP = "§bWIZARD-§5SHOP";

    public static void createGUI(Player player){
        Inventory inv = Bukkit.createInventory(null,9*3,WIZARDSHOP);

        for (int i = 0; i <inv.getSize(); i++) {

            inv.setItem(i,new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName("").build());
        }

        String[] asteroidLore= {"§aOnly Shift + Left Click = Gives Everything","§4Be aware of it!"};

        inv.setItem(11, new ItemBuilder(Material.NETHER_BRICK).setName("§eGive Fragments").setLore(asteroidLore).build());

        inv.setItem(15,new ItemBuilder(Material.BEDROCK).setName("§5§kLocked").build());

        String[] asteroidPickaxe = {"Cost: 5 Asteroid Fragment"};

        ItemStack pickaxe = All_Items.asteroidPickaxe;
        pickaxe.getItemMeta().setLore(Arrays.asList(asteroidLore));
        
        if(SMPEvents.getPlugin().cfM.getProgressCfg().getInt("Event.stage") >= 4)
            inv.setItem(15,All_Items.asteroidPickaxe);

        player.openInventory(inv);
    }

}
