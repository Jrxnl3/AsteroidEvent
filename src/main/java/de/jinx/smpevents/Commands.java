package de.jinx.smpevents;

import de.jinx.smpevents.items.All_Items;
import de.jinx.smpevents.mob.Astroider;
import de.jinx.smpevents.villager.Wizard;
import net.minecraft.server.level.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("mob")){
            Astroider astroider = new Astroider(player.getLocation());

            WorldServer world = ((CraftWorld)player.getLocation().getWorld()).getHandle();
            world.addEntity(astroider);

            player.getInventory().addItem(All_Items.asteroidPickaxe);
        }else if (label.equalsIgnoreCase("movein")){

            int x = player.getTargetBlock(null,10).getLocation().getBlockX();
            int y = player.getTargetBlock(null,10).getLocation().getBlockY() + 1;
            int z = player.getTargetBlock(null,10).getLocation().getBlockZ();

            Location loc = new Location(player.getWorld(),x,y,z);

            Wizard.createWizardNPC(loc);

        }else if (label.equalsIgnoreCase("phase")){

            int phase = Integer.parseInt(args[0]);

            SMPEvents.getPlugin().cfM.reload();
            SMPEvents.getPlugin().cfM.getProgressCfg().set("Event.stage",phase);
            SMPEvents.getPlugin().cfM.getProgressCfg().set("Event.progress",0);
            SMPEvents.getPlugin().cfM.save();

            ScoreboardHandler.updateScoreboard();

        }

        return false;
    }
}
