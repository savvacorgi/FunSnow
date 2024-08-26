package com.svo.snowp.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;

public class SphereUtils {

    // Создание кастомного предмета с NBT-тегом
    public static ItemStack createCustomSnowBlock() {
        ItemStack item = new ItemStack(Material.SNOW_BLOCK);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            NamespacedKey key = new NamespacedKey("snowp", "custom_tag");
            meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "special_snow_block");
            item.setItemMeta(meta);
        }

        return item;
    }

    // Проверка NBT-тега у предмета
    public static boolean hasCustomTag(ItemStack item) {
        if (item == null || !item.hasItemMeta()) return false;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        NamespacedKey key = new NamespacedKey("snowp", "custom_tag");
        String value = meta.getPersistentDataContainer().get(key, PersistentDataType.STRING);

        return "special_snow_block".equals(value);
    }
}
