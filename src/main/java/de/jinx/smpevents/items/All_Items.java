package de.jinx.smpevents.items;

import de.jinx.smpevents.util.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class All_Items {
    public static String[] asteroidLore = {"§6This is a Fragment of an Ancient Asteroid.","§6A §b§lWizard§6 may now what this is used for."};

    public static ItemStack asteroidFragments = new ItemBuilder(Material.NETHER_BRICK).setName("§eAsteroid Fragment").setAmount(1).setLore(asteroidLore).build();

    public static String[] pickaxeLore = {"§6This is a Pickaxe made out of Asteroid Fragments.","§6Forged by the §b§lWizard§6.", ChatColor.GRAY+"Smelting I"};

    public static ItemStack asteroidPickaxe = new ItemBuilder(Material.NETHERITE_PICKAXE).setName("§eAsteroid Pickaxe").setAmount(1).setLore(pickaxeLore).setEnchantment(CustomEnchants.SMELTING,1,true).build();

}

