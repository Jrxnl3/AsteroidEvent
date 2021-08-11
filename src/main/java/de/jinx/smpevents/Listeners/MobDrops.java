package de.jinx.smpevents.Listeners;

import de.jinx.smpevents.Config.ConfigManager;
import de.jinx.smpevents.SMPEvents;
import de.jinx.smpevents.ScoreboardHandler;
import de.jinx.smpevents.items.All_Items;
import de.jinx.smpevents.mob.Astroider;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;


public class MobDrops implements Listener {



    @EventHandler
    public void handleMobDrop(EntityDeathEvent e){
        if(!(e.getEntity() instanceof Skeleton)) return;

        if(e.getEntity().getCustomName() == null || e.getEntity().getCustomName().equals("")) return;

        if(e.getEntity().getCustomName().equals(Astroider.name)) {
            e.getDrops().clear();

            if(e.getEntity().getKiller() == null) return;

            if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {

                int stage = SMPEvents.getPlugin().cfM.getProgressCfg().getInt("Event.stage");

                if(stage == 1){
                    ConfigManager cfM = SMPEvents.getPlugin().cfM;
                    cfM.reload();
                    FileConfiguration cfg = cfM.getProgressCfg();

                    int maxProgress = cfg.getInt("Stage"+stage+".maxProgress");

                    int progress =cfg.getInt("Event.progress");

                    if(progress < maxProgress){
                        cfM.getProgressCfg().set("Event.progress", cfM.getProgressCfg().getInt("Event.progress") + 1);

                    }
                    cfM.save();
                    ScoreboardHandler.updateScoreboard();
                }

                Player killer = e.getEntity().getKiller();

                if(killer.getInventory().getItemInMainHand().getType() == Material.GOLDEN_PICKAXE) {
                    if (SMPEvents.isPrime(10))
                        e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), All_Items.asteroidFragments);
                }else if (SMPEvents.isPrime(5))
                    e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), All_Items.asteroidFragments);
            }
        }
    }
}
