package com.svo.snowp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SphereUtils {

    private final JavaPlugin plugin;
    private final Random random = new Random();

    public SphereUtils(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private static final List<Sphere> spheres = new ArrayList<>();

    static {
        // Инициализируем сферы с их свойствами (на русском языке)
        spheres.add(new Sphere("Сфера Воина", "Голова Воина", "savvacorgi", 2.0, 0.0, 5.0, "Увеличение урона и здоровья"));
        spheres.add(new Sphere("Сфера Защитника", "Голова Защитника", "savvacorgi", 5.0, 0.0, -1.0, "Увеличение брони, но уменьшение здоровья"));
        spheres.add(new Sphere("Сфера Ассасина", "Голова Ассасина", "savvacorgi", -1.0, 5.0, 0.0, "Увеличение урона, но уменьшение брони"));
        spheres.add(new Sphere("Сфера Мага", "Голова Мага", "savvacorgi", 0.0, 2.0, 5.0, "Увеличение здоровья и магического урона"));
        spheres.add(new Sphere("Сфера Танка", "Голова Танка", "savvacorgi", 8.0, -1.0, -1.0, "Максимальная броня, но уменьшение урона и здоровья"));
        spheres.add(new Sphere("Сфера Лучника", "Голова Лучника", "savvacorgi", 0.0, 3.0, 3.0, "Увеличение дальнего урона и здоровья"));
        spheres.add(new Sphere("Сфера Целителя", "Голова Целителя", "savvacorgi", 0.0, 0.0, 10.0, "Максимальное здоровье"));
        spheres.add(new Sphere("Сфера Вора", "Голова Вора", "savvacorgi", -2.0, 4.0, 0.0, "Увеличение скорости и урона, но уменьшение брони"));
        spheres.add(new Sphere("Сфера Берсеркера", "Голова Берсеркера", "savvacorgi", 4.0, -2.0, 2.0, "Высокий урон, но сниженная броня"));
        spheres.add(new Sphere("Сфера Паладина", "Голова Паладина", "savvacorgi", 3.0, 1.0, 3.0, "Сбалансированное увеличение брони, урона и здоровья"));
    }

    public static ItemStack createGiftBox(JavaPlugin plugin) {
        ItemStack item = new ItemStack(Material.CHEST); // Можно заменить на другой материал, например на подарочный блок.
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName("Подарочная Коробка");
        List<String> lore = new ArrayList<>();
        lore.add("Содержит случайную сферу!");
        meta.setLore(lore);

        NamespacedKey key = new NamespacedKey(plugin, "gift_box");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, "true");

        item.setItemMeta(meta);
        return item;
    }

    public static void openGiftBox(Player player, ItemStack item, JavaPlugin plugin) {
        if (isGiftBox(item, plugin)) {
            ItemStack sphereItem = getRandomSphereItem(plugin);
            player.getInventory().addItem(sphereItem);
            player.sendMessage("Вы открыли подарочную коробку и получили " + sphereItem.getItemMeta().getDisplayName() + "!");
        }
    }

    public static boolean isGiftBox(ItemStack item, JavaPlugin plugin) {
        if (item == null || item.getType() != Material.CHEST) {
            return false;
        }

        ItemMeta meta = item.getItemMeta();
        NamespacedKey key = new NamespacedKey(plugin, "gift_box");
        return "true".equals(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
    }

    private static ItemStack getRandomSphereItem(JavaPlugin plugin) {
        Random random = new Random();
        Sphere sphere = spheres.get(random.nextInt(spheres.size()));

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(sphere.getName());

        List<String> lore = new ArrayList<>();
        lore.add("Особенности:");
        lore.add(" - Броня: " + sphere.getArmor());
        lore.add(" - Урон: " + sphere.getDamage());
        lore.add(" - Здоровье: " + sphere.getHealth());
        lore.add(sphere.getDescription());
        meta.setLore(lore);

        NamespacedKey key = new NamespacedKey(plugin, "sphere_type");
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, sphere.getName());

        meta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString(sphere.getOwnerUUID())));

        item.setItemMeta(meta);
        return item;
    }

    // Спавн кейсов на поверхности с шансом 10% в каждом чанке
    public void spawnGiftBoxes(World world) {
        world.getLoadedChunks().forEach(chunk -> {
            if (random.nextDouble() <= 0.1) {
                int x = chunk.getX() * 16 + random.nextInt(16);
                int z = chunk.getZ() * 16 + random.nextInt(16);
                int y = world.getHighestBlockYAt(x, z) + 1;
                Location location = new Location(world, x, y, z);

                world.getBlockAt(location).setType(Material.CHEST);
            }
        });
    }

    // Удаление всех кейсов после завершения ивента
    public void removeGiftBoxes(World world) {
        world.getLoadedChunks().forEach(chunk -> {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    int realX = chunk.getX() * 16 + x;
                    int realZ = chunk.getZ() * 16 + z;
                    Location location = new Location(world, realX, world.getHighestBlockYAt(realX, realZ), realZ);

                    if (world.getBlockAt(location).getType() == Material.CHEST) {
                        world.getBlockAt(location).setType(Material.AIR);
                    }
                }
            }
        });
    }
                                              }
                        
