package com.svo.snowp.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereUtils {

    private static final List<Sphere> spheres = new ArrayList<>();

    public SphereUtils() {
        // Инициализация списка сфер
        spheres.add(new Sphere("Sphere Miner", "Miner Head", "100683", 0.0, 0.0, 1.0, 0.0, "Increased mining speed"));
        spheres.add(new Sphere("Sphere Diver", "Diver Head", "100683", 0.0, 0.0, 1.0, 0.0, "Increased underwater speed"));
        spheres.add(new Sphere("Sphere Builder", "Builder Head", "100683", 0.0, 0.0, 1.0, 0.0, "Increased building speed"));
        spheres.add(new Sphere("Sphere Explorer", "Explorer Head", "100683", 0.0, 0.0, 1.0, 0.0, "Increased exploration speed"));
        spheres.add(new Sphere("Sphere Warrior", "Warrior Head", "100683", 1.0, 0.0, 0.0, 1.0, "Increased combat ability"));
        spheres.add(new Sphere("Sphere Archer", "Archer Head", "100683", 0.0, 0.0, 1.0, 0.0, "Increased archery skills"));
        spheres.add(new Sphere("Sphere Mage", "Mage Head", "100683", 0.0, 0.0, 0.0, 1.0, "Increased magical abilities"));
        spheres.add(new Sphere("Sphere Knight", "Knight Head", "100683", 0.0, 0.0, 0.0, 1.0, "Balanced combat skills"));

        // Новая сфера с указанными эффектами
        spheres.add(new Sphere("Sphere of Agility", "Agility Head", "100684", 4.0, -3.0, 0.1, 2.0, "Increase damage by 4, decrease HP by 3, increase movement speed by 10%, increase defense by 2"));
    }

    // Метод для создания предмета сферы
    public ItemStack createSphereItem(Sphere sphere) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(sphere.getDisplayName());
        meta.setLore(List.of(sphere.getDescription()));
        item.setItemMeta(meta);
        return item;
    }

    // Открытие подарка
    public void openGiftBox(Player player, ItemStack item, Plugin plugin) {
        Sphere randomSphere = spheres.get(new Random().nextInt(spheres.size()));
        ItemStack sphereItem = createSphereItem(randomSphere);
        player.getInventory().addItem(sphereItem);
        player.sendMessage("Вы открыли подарок и получили: " + randomSphere.getDisplayName());
    }

    // 
