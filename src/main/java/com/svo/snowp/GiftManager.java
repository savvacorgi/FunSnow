package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {

    private final Snowp plugin;
    private boolean eventActive = false;
    private final SphereUtils sphereUtils;
    private final List<Location> spawnedGiftLocations = new ArrayList<>();

    public EventManager(Snowp plugin) {
        this.plugin = plugin;
        this.sphereUtils = plugin.getSphereUtils();
    }

    public void startEvent() {
        if (eventActive) {
            return;
        }
        eventActive = true;

        Bukkit.getScheduler().runTask(plugin, () -> {
            // Запуск ивента и спавн голов
            spawnGiftHeads();
            
            // Планирование окончания ивента через 5 минут
            new BukkitRunnable() {
                @Override
                public void run() {
                    endEvent();
                }
            }.runTaskLater(plugin, 6000L); // 6000 тиков = 5 минут
        });
    }

    private void spawnGiftHeads() {
        World world = Bukkit.getWorlds().get(0); // Получаем первый мир
        Random random = new Random();

        while (spawnedGiftLocations.size() < 6) { // Спавн 6 голов
            int x = random.nextInt(9900) + 100; // Случайные координаты от 100 до 10000
            int z = random.nextInt(9900) + 100;
            int y = world.getHighestBlockYAt(x, z) + 1; // Поставить голову прямо над поверхностью

            Location location = new Location(world, x, y, z);
            Block block = location.getBlock();

            if (block.getType() == Material.AIR) {
                block.setType(Material.PLAYER_HEAD);
                org.bukkit.inventory.meta.PlayerHeadMeta meta = (org.bukkit.inventory.meta.PlayerHeadMeta) block.getState().getData();
                meta.setCustomModelData(97519); // ID текстуры головы
                block.getState().update();

                // Добавляем локацию в список и инфо в чат
                spawnedGiftLocations.add(location);
                Bukkit.getLogger().info("Подарок заспавнен в (" + x + ", " + z + ") на высоте " + y);
                Bukkit.broadcastMessage("Новый подарок заспавнен в локации (" + x + ", " + z + ")!");
            }
        }
    }

    private void endEvent() {
        if (!eventActive) {
            return;
        }
        eventActive = false;

        // Удаление голов
        for (Location loc : spawnedGiftLocations) {
            Block block = loc.getBlock();
            if (block.getType() == Material.PLAYER_HEAD) {
                block.setType(Material.AIR);
            }
        }
        spawnedGiftLocations.clear();

        Bukkit.broadcastMessage("Ивент закончился. Подарки были удалены. Спасибо за участие!");
    }
}
