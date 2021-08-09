package de.jinx.smpevents.Listeners;

import de.jinx.smpevents.SMPEvents;
import de.jinx.smpevents.mob.Astroider;
import net.minecraft.server.level.WorldServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class MobSpawning implements Listener {

    @EventHandler
    public void handleMobJoin(EntitySpawnEvent e){
        if(e.getEntity() instanceof Skeleton) return;
        if(e.getEntity() instanceof Monster) {

            if (SMPEvents.isPrime(5)) {
                e.setCancelled(true);
                Astroider astroider = new Astroider(e.getLocation());

                WorldServer world = ((CraftWorld) e.getLocation().getWorld()).getHandle();
                world.addEntity(astroider);
            }
        }
    }
}
