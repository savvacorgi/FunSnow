package com.svo.snowp.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereUtils {

    private static final List<Sphere> spheres = new ArrayList<>();

    public SphereUtils() {
        // Инициализация списка сфер
        spheres.add(new Sphere("Sphere Miner", "Miner Head", "100683", 0.0, 0.0, 1.0, "Increased mining speed"));
        spheres.add(new Sphere("Sphere Diver", "Diver Head", "100683", 0.0, 0.0, 1.0, "Increased underwater speed"));
        spheres.add(new Sphere("Sphere Builder", "Builder Head", "100683", 0.0, 0.0, 1.0, "Increased building speed"));
        spheres.add(new Sphere("Sphere Explorer", "Explorer Head", "100683", 0.0, 0.0, 1.0, "Increased exploration speed"));
        spheres.add(new Sphere("Sphere Warrior", "Warrior Head", "100683", 1.0, 0.0, 1.0, "Increased combat ability"));
        spheres.add(new Sphere("Sphere Archer", "Archer Head", "100683", 0.0, 0.0, 1.0, "Increased archery skills"));
        spheres.add(new Sphere("Sphere Mage", "Mage Head", "100683", 0.0, 0.0, 1.0, "Increased magical abilities"));
        spheres.add(new Sphere("Sphere Knight", "Knight Head", "100683", 0.0, 0.0, 1.0, "Balanced combat skills"));
        spheres.add(new Sphere("Sphere of Agility", "Agility Head", "100684", 4.0, -3.0, 2.0, "Increase damage by 4, decrease HP by 3, increase movement speed by 10, increase defense by 2"));
        // Добавьте больше сфер по необходимости
    }

    // Метод для создания предмета сферы
    public ItemStack createSphereItem(Sphere sphere) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(sphere.getName());
        meta.setLore(List.of(sphere.getDescription()));
        item.setItemMeta(meta);
        return item;
    }

    // Получение случайной сферы
    public Sphere getRandomSphere() {
        Random random = new Random();
        return spheres.get(random.nextInt(spheres.size()));
    }
}
