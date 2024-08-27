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
spheres.add(new Sphere("Sphere Miner", "Miner Head", 0.0, 0.0, 1.0, 0.0));
spheres.add(new Sphere("Sphere Diver", "Diver Head", 0.0, 0.0, 1.0, 0.0));
spheres.add(new Sphere("Sphere Builder", "Builder Head", 0.0, 0.0, 1.0, 0.0));
spheres.add(new Sphere("Sphere Explorer", "Explorer Head", 0.0, 0.0, 1.0, 0.0));
spheres.add(new Sphere("Sphere Warrior", "Warrior Head", 1.0, 0.0, 1.0, 0.0));
spheres.add(new Sphere("Sphere Archer", "Archer Head", 0.0, 0.0, 1.0, 0.0));
spheres.add(new Sphere("Sphere Mage", "Mage Head", 0.0, 0.0, 1.0, 0.0));
spheres.add(new Sphere("Sphere Knight", "Knight Head", 0.0, 0.0, 1.0, 0.0));
spheres.add(new Sphere("Sphere of Agility", "Agility Head", 4.0, -3.0, 2.0, 0.0));
        
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
