package de.jinx.smpevents.villager;
import de.jinx.smpevents.Config.ConfigManager;
import de.jinx.smpevents.SMPEvents;
import de.jinx.smpevents.gui.GUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class VillagerHandler implements Listener {

    @EventHandler
    public void villagerDamage(EntityDamageEvent e){
        if(e.getEntity().getCustomName() == null)
            return;

        if(e.getEntity().getCustomName().equals(Wizard.NAME)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void myTalkingWizard(PlayerInteractEntityEvent e) {
        if(e.getRightClicked().getCustomName() == null) return;

        if(e.getRightClicked().getCustomName().equals(Wizard.NAME)){

            e.setCancelled(true);

            if(e.getPlayer().isSneaking()){
                GUI.createGUI(e.getPlayer());
                return;
            }

            ConfigManager cfM = SMPEvents.getPlugin().cfM;
            cfM.reload();
            int stage = cfM.getProgressCfg().getInt("Event.stage");
            switch (stage){
                case 1:
                    for (String textLine: SMPEvents.getPlugin().phase0) {
                        e.getPlayer().sendMessage(textLine);
                    }
                    break;
                case 2:
                    for (String textLine: SMPEvents.getPlugin().phase1) {
                        e.getPlayer().sendMessage(textLine);
                    }
                    break;
                case 3:
                    for (String textLine: SMPEvents.getPlugin().phase2) {
                        e.getPlayer().sendMessage(textLine);
                    }
                    break;
                case 4:
                    for (String textLine: SMPEvents.getPlugin().phase3) {
                        e.getPlayer().sendMessage(textLine);
                    }
                    break;
                case 5:
                    for (String textLine: SMPEvents.getPlugin().phase4) {
                        e.getPlayer().sendMessage(textLine);
                    }
                    break;
            }
        }
    }

}
