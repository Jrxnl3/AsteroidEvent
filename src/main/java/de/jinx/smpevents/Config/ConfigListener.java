package de.jinx.smpevents.Config;

import de.jinx.smpevents.SMPEvents;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ConfigListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        System.out.println(SMPEvents.getPlugin().cfM.getProgressCfg().get("Event.stage"));
        System.out.println(SMPEvents.getPlugin().cfM.getProgressCfg().get("Event.progress"));
    }
}
