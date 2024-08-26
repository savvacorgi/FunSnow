package com.svo.snowp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SphereUtils {

    public static class Sphere {
        private final String name;
        private final String headName;
        private final String ownerUUID;
        private final double speed;
        private final double jumpBoost;
        private final double efficiency;
        private final String description;

        public Sphere(String name, String headName, String ownerUUID, double speed, double jumpBoost, double efficiency, String description) {
            this.name = name;
            this.headName = headName;
            this.ownerUUID = ownerUUID;
            this.speed = speed;
            this.jumpBoost = jumpBoost;
            this.efficiency = efficiency;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getHeadName() {
            return headName;
        }

        public String getOwnerUUID() {
            return ownerUUID;
        }

        public double getSpeed() {
            return speed;
        }

        public double getJumpBoost() {
            return jumpBoost;
        }

        public double getEfficiency() {
            return efficiency;
        }

        public String getDescription() {
            return description;
        }
    }

    private static final List<Sphere> spheres = new ArrayList<>();

    static {
        spheres.add(new Sphere("Сфера Воина", "Голова Воина", "some-uuid-1", 2.0, 0.0, 5.0, "Увеличение урона и здоровья"));
        spheres.add(new Sphere("Сфера Защитника", "Голова Защитника", "some-uuid-2", 5.0, 0.0, -1.0, "Увеличение брони, но уменьшение здоровья"));
        spheres.add(new Sphere("Сфера Ассасина", "Голова Ассасина", "some-uuid-3", -1.0, 5.0, 0.0, "Увеличение урона, но уменьшение брони"));
        spheres.add(new Sphere("Сфера Мага", "Голова Мага", "some-uuid-4", 0.0, 2.0, 5.0, "Увеличение здоровья и магического урона"));
        spheres.add(new Sphere("Сфера Танка", "Голова Танка", "some-uuid-5", 8.0, -1.0, -1.0, "Максимальная броня, но уменьшение урона и здоровья"));
        spheres.add(new Sphere("Сфера Лучника", "Голова Лучника", "some-uuid-6", 0.0, 3.0, 3.0, "Увеличение дальнего урона и здоровья"));
        spheres.add(new Sphere("Сфера Целителя", "Голова Целителя", "some-uuid-7", 0.0, 0.0, 10.0, "Максимальное здоровье"));
        spheres.add(new Sphere("Сфера Вора", "Голова Вора", "some-uuid-8", -2.0, 4.0, 0.0, "Увеличение скорости и урона, но уменьшение брони"));
        spheres.add(new Sphere("Сфера Берсеркера", "Голова Берсеркера", "some-uuid-9", 4.0, -2.0, 2.0, "Высокий урон, но сниженная броня"));
        spheres.add(new Sphere("Сфера Паладина", "Голова Паладина", "some-uuid-10", 3.0, 1.0, 3.0, "Сбалансированное увеличение брони, урона и здоровья"));
        spheres.add(new Sphere("Сфера Run Vasa", "Голова Run Vasa", "some-uuid-11", 0.0, 2.0, 1.0, "Увеличение скорости и прыгучести"));
        spheres.add(new Sphere("Сфера Шахтёра", "Голова Шахтёра", "some-uuid-12", 0.0, 1.0, 0.0, "Эффективность +3, скорость +1"));
        spheres.add(new Sphere("Сфера Ez", "Голова Ez", "some-uuid-13", -1.0, -1.0, -1.0, "Урон +2, ХП -1, Броня -1"));
        spheres.add(new Sphere("Сфера Макака", "Голова Макака", "some-uuid-14", 4.0, 0.0, -3.0, "Скорость атаки и дамаг +4, броня и хп -3"));
    }

    public static void openGiftBox(Player player, ItemStack item, JavaPlugin plugin) {
        if (item.getType() == Material.PLAYER_HEAD) {
            ItemMeta meta = item.getItemMeta();
            if (meta instanceof SkullMeta) {
                SkullMeta skullMeta = (SkullMeta) meta;
                String headName = skullMeta.getDisplayName();
                for (Sphere sphere : spheres) {
                    if (sphere.getHeadName().equals(headName)) {
                        ItemStack sphereItem = createSphereItem(sphere);
                        player.getInventory().addItem(sphereItem);
                        player.sendMessage("Вы получили сферу: " + sphere.getName());
                        item.setAmount(0); // Удаляем подарок из инвентаря
                        return;
                    }
                }
                player.sendMessage("Этот подарок не содержит сферу.");
            }
        }
    }

    private static ItemStack createSphereItem(Sphere sphere) {
        ItemStack item = new ItemStack(Material.DIAMOND); // Замените на подходящий предмет
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(sphere.getName());
        meta.setLore(List.of(sphere.getDescription()));
        item.setItemMeta(meta);
        return item;
    }

    public void spawnGiftBoxes(World world) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) { // Количество подарков
            int x = random.nextInt(100) - 50;
            int y = 64; // Высота
            int z = random.nextInt(100) - 50;
            Location location = new Location(world, x, y, z);
            if (world.getBlockAt(location).getType() == Material.AIR) {
                world.getBlockAt(location).setType(Material.PLAYER_HEAD);
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                meta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("some-uuid"))); // Поставьте ваш UUID
                meta.setDisplayName("Подарок");
                head.setItemMeta(meta);
                world.dropItem(location, head);
            }
        }
    }

    public void removeGiftBoxes(World world) {
        for (int x = -50; x < 50; x++) {
            for (int z = -50; z < 50; z++) {
                Location location = new Location(world, x, 64, z);
                if (world.getBlockAt(location).getType() == Material.PLAYER_HEAD) {
                    world.getBlockAt(location).setType(Material.AIR);
                }
            }
        }
    }
    }
