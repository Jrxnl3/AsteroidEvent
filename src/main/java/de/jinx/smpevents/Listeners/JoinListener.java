package de.jinx.smpevents.Listeners;

import de.jinx.smpevents.SMPEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void playerJoinMessage(PlayerJoinEvent e){
        if(SMPEvents.getPlugin().cfM.getProgressCfg().getInt("Event.stage") == 1){
            e.getPlayer().sendMessage("§2SM§aPain§6 >> §6Hey, The Wizard NPC moved in check him out!");
        }
    }

}
