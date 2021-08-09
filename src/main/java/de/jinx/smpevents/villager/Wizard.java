package de.jinx.smpevents.villager;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Wizard {

    public static final String NAME = "§bWizard";

    public static void createWizardNPC(Location loc){
        Villager wizard = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        wizard.setAI(true);
        wizard.setCustomName("§bWizard");
        wizard.setCustomNameVisible(true);
        wizard.setProfession(Villager.Profession.LIBRARIAN);
        wizard.addPotionEffect(new PotionEffect(PotionEffectType.SLOW,Integer.MAX_VALUE,255,false,false));
    }

}
