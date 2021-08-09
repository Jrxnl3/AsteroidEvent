package de.jinx.smpevents.items;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CustomEnchants {

    public static final Enchantment SMELTING = new EnchantmentWrapper("smelting","Smelting",1);

    public static void register(){
        boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(SMELTING);
        if(!registered){
            registerEnchantment(SMELTING);
        }
    }

    public static void registerEnchantment(Enchantment enchantment){
        boolean registered = true;
        try{
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null,true);
            Enchantment.registerEnchantment(enchantment);
        }catch (Exception e){
            registered = false;
            e.printStackTrace();
        }
        if(registered){
            System.out.println("Registered the enchantment: " + enchantment.getName());
        }
    }
}
