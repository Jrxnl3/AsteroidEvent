package de.jinx.smpevents.mob;

import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EnumItemSlot;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.monster.EntitySkeleton;
import net.minecraft.world.entity.player.EntityHuman;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;


public class Astroider extends EntitySkeleton {
    public static String name = ChatColor.GOLD+"ASTROIDER";

    public Astroider(Location loc){
        super(EntityTypes.aB, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(),loc.getY(),loc.getZ());

        this.setCustomName(new ChatComponentText(name));
        this.setCustomNameVisible(true);
        this.setHealth(20);

        this.setItem(EnumItemSlot.a, new ItemStack(Material.WOODEN_AXE));
        this.setItem(EnumItemSlot.e, new ItemStack(Material.CHAINMAIL_CHESTPLATE));
    }

    public void initPathfinder(){
        super.initPathfinder();
        this.bP.a(0,new PathfinderGoalNearestAttackableTarget<EntityHuman>(this,EntityHuman.class,false));

    }
    public void setItem(EnumItemSlot slot, ItemStack item){
        this.setSlot(slot, CraftItemStack.asNMSCopy(item));
    }
}
