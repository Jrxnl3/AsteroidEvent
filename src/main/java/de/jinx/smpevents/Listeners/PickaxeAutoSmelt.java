package de.jinx.smpevents.Listeners;

import de.jinx.smpevents.SMPEvents;
import de.jinx.smpevents.items.All_Items;
import de.jinx.smpevents.items.CustomEnchants;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class PickaxeAutoSmelt implements Listener {

    @EventHandler
    public void handleAutoSmelt(BlockBreakEvent e){
        Player player = e.getPlayer();
        Block oreBlock = e.getBlock();
        if(player.getInventory().getItemInMainHand() == null) return;

        if(!player.getInventory().getItemInMainHand().hasItemMeta()) return;

        if(!player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchants.SMELTING)) return;

        if(player.getInventory().getItemInMainHand().getItemMeta().getEnchants().containsKey(CustomEnchants.SMELTING)) {

            LinkedHashMap<Material, Material> oreAutoSmelt = SMPEvents.getPlugin().autoSmeltOre;

            for (int i = 0; i < oreAutoSmelt.size(); i++) {

                Object[] mapOres = oreAutoSmelt.keySet().stream().toArray();

                if(oreBlock.getBlockData().getMaterial() == mapOres[i]){
                    System.out.println("Found: " +mapOres[i]);

                    ItemStack item = new ItemStack(oreAutoSmelt.get(mapOres[i]));

                    int ingots = (int) (Math.random() * 3 + 1);
                    item.setAmount(ingots);

                    oreBlock.getLocation().getWorld().dropItemNaturally(oreBlock.getLocation(),item);
                    e.setCancelled(true);
                    oreBlock.setType(Material.AIR);
                    return;
                }


            }

        }

    }

}
/*
for (int i = 0; i < SMPEvents.getPlugin().AutoSmeltOre.size(); i++) {

            Object[] keys = SMPEvents.getPlugin().AutoSmeltOre.keySet().toArray();

            System.out.println(keys.length);

            if(oreBlock.getBlockData().getMaterial().toString().equals(keys[i].toString())){

                System.out.println("i:"+i+":"+SMPEvents.getPlugin().AutoSmeltOre.get(i) + SMPEvents.getPlugin().AutoSmeltOre.size());
                ItemStack item = new ItemStack(Material.valueOf(SMPEvents.getPlugin().AutoSmeltOre.get(i).toString()));

                int ingots = (int) (Math.random() * 3 + 1);
                item.setAmount(ingots);

                oreBlock.getLocation().getWorld().dropItemNaturally(oreBlock.getLocation(),item);
                e.setCancelled(true);
                oreBlock.setType(Material.AIR);
                return;
            }
        }
 */