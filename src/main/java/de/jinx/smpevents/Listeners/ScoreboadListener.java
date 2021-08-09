package de.jinx.smpevents.Listeners;

import de.jinx.smpevents.ScoreboardHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ScoreboadListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        ScoreboardHandler.createScoreboard(e.getPlayer());
    }

}
