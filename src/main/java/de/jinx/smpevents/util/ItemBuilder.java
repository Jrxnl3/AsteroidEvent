package de.jinx.smpevents.util;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {
    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        item = new ItemStack(material, 1);
        itemMeta = item.getItemMeta();
    }


    public ItemBuilder setName(String name) {
        itemMeta.setDisplayName(name);
        return this;
    }
    public ItemBuilder setUnbreakable(Boolean breakable) {
        itemMeta.setUnbreakable(breakable);
        return this;
    }

    public ItemBuilder setLore(String...lore) {
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }
    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }
    public ItemBuilder setEnchantment(Enchantment enchantment, int level, boolean limitLevel) {
        itemMeta.addEnchant(enchantment, level, limitLevel);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(itemMeta);
        return item;
    }
}