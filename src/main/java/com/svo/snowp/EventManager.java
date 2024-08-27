package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class EventManager {

    private final JavaPlugin plugin;
    private final Random random = new Random();
    private boolean eventActive = false;

    public EventManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void startEvent() {
        if (eventActive) return; // Проверяем, не запущен ли уже ивент

        eventActive = true;

        // Запуск ивента
        new BukkitRunnable() {
            @Override
            public void run() {
                // Завершение ивента
                eventActive = false;
                Bukkit.getServer().broadcastMessage("§cИвент закончился!");
                // Запуск следующего ивента через 5 минут
                startEvent();
            }
        }.runTaskLater(plugin, 5 * 60 * 20); // 5 минут
    }
}
