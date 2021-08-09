package de.jinx.smpevents.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobHandler implements Listener {

    @EventHandler
    public void handleWitherEffect(EntityDamageByEntityEvent e){
        if(!(e.getDamager() instanceof Skeleton)) return;
        if(!(e.getEntity() instanceof Player)) return;

        Player victim = (Player) e.getEntity();
        victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER,20*10,1,true,false,true));

    }

}
