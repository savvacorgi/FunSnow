package com.svo.snowp.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereUtils {
    private static final List<Sphere> spheres = new ArrayList<>();

    static {
        spheres.add(new Sphere("Sphere Miner", "Miner Head", "100683", 0.0, 0.0, 1.0, "Increased mining speed"));
        spheres.add(new Sphere("Sphere Diver", "Diver Head", "100683", 0.0, 0.0, 1.0, "Increased underwater speed"));
        spheres.add(new Sphere("Sphere Builder", "Builder Head", "100683", 0.0, 0.0, 1.0, "Increased building speed"));
        spheres.add(new Sphere("Sphere Explorer", "Explorer Head", "100683", 0.0, 0.0, 1.0, "Increased exploration speed"));
        spheres.add(new Sphere("Sphere Warrior", "Warrior Head", "100683", 1.0, 0.0, 1.0, "Increased combat ability"));
        spheres.add(new Sphere("Sphere Archer", "Archer Head", "100683", 0.0, 0.0, 1.0, "Increased archery skills"));
        spheres.add(new Sphere("Sphere Mage", "Mage Head", "100683", 0.0, 0.0, 1.0, "Increased magical abilities"));
        spheres.add(new Sphere("Sphere Knight", "Knight Head", "100683", 0.0, 0.0, 1.0, "Balanced combat skills"));
    }

    public static ItemStack createSphereItem(Sphere sphere) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD); // Предмет игрока как пример
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(sphere.getName());
        List<String> lore = new ArrayList<>();
        lore.add(sphere.getDescription());
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public Sphere getSphereFromItem(ItemStack item) {
        if (item == null || item.getItemMeta() == null || !item.getItemMeta().hasDisplayName()) {
            return null;
        }
        String name = item.getItemMeta().getDisplayName();
        for (Sphere sphere : spheres) {
            if (sphere.getName().equals(name)) {
                return sphere;
            }
        }
        return null;
    }
}
