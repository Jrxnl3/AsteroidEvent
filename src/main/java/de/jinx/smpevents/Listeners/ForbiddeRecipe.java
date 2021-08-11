package de.jinx.smpevents.Listeners;

import de.jinx.smpevents.items.All_Items;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ForbiddeRecipe implements Listener {

    @EventHandler
    public void onPlayerCraft(CraftItemEvent event) {
        for (HumanEntity entity : event.getViewers()) {
            if (entity instanceof Player) {
                List<ItemStack> items = Arrays.asList(event.getInventory().getMatrix());

                if(items.contains(All_Items.asteroidFragments)) {
                    event.setCancelled(true);
                    event.getView().getPlayer().sendMessage("§cYou cant craft this Item with a Asteroid Fragments!");
                }
            }
        }
    }
}
