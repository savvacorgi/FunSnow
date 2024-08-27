package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {

    private final JavaPlugin plugin;
    private final List<Location> giftLocations = new ArrayList<>();
    private boolean eventActive = false;
    private final long eventInterval = 20 * 60 * 5; // 5 минут в тиках
    private final long eventDuration = 20 * 60 * 5; // 5 минут ивента
    private final SphereUtils sphereUtils;

    public EventManager(JavaPlugin plugin, SphereUtils sphereUtils) {
        this.plugin = plugin;
        this.sphereUtils = sphereUtils;
        startEventCycle();
    }

    private void startEventCycle() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            if (eventActive) {
                endEvent();
            } else {
                startEvent();
            }
        }, 0L, eventInterval); // Запуск ивента каждые 5 минут
    }

    public void startEvent() {
        if (eventActive) {
            return;
        }

        eventActive = true;
        plugin.getServer().broadcastMessage("Ивент начался! Найдите подарки!");

        World world = Bukkit.getWorlds().get(0); // Основной мир
        spawnGiftsInWorld(world);
    }

    private void spawnGiftsInWorld(World world) {
        Random random = new Random();
        giftLocations.clear();

        for (int i = 0; i < 6; i++) { // 6 подарков
            int x = random.nextInt(20001) - 10000; // Координаты от -10000 до 10000
            int z = random.nextInt(20001) - 10000; // Координаты от -10000 до 10000
            int y = world.getHighestBlockYAt(x, z) + 1; // Высота на поверхности + 1 для размещения

            Location location = new Location(world, x, y, z);

            Block block = world.getBlockAt(location);
            block.setType(Material.PLAYER_HEAD);

            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName("Подарок");
            meta.setLore(List.of("Координаты: X:" + x + " Y:" + y + " Z:" + z));
            item.setItemMeta(meta);

            block.setType(Material.PLAYER_HEAD);
            block.getState().update(); // Обновляем состояние блока

            giftLocations.add(location);
        }

        // Удаление подарков по истечении времени
        Bukkit.getScheduler().runTaskLater(plugin, this::endEvent, eventDuration); // 5 минут
    }

    private void endEvent() {
        for (Location loc : giftLocations) {
            Block block = loc.getBlock();
            if (block.getType() == Material.PLAYER_HEAD) {
                block.setType(Material.AIR);
            }
        }
        plugin.getServer().broadcastMessage("Ивент завершен! Подарки исчезли.");
        eventActive = false;
    }

    public boolean isEventActive() {
        return eventActive;
    }

    public SphereUtils getSphereUtils() {
        return sphereUtils;
    }
    }
