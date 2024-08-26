package com.svo.snowp;

import com.svo.snowp.listeners.SphereListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Snowp extends JavaPlugin {

    private Map<Location, Biome> originalBiomes = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new SphereListener(this), this);
        getLogger().info("Snowp plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Snowp plugin disabled.");
    }

    public void startEvent(Player player) {
        Location playerLocation = player.getLocation();
        int radius = 500;

        // Сохраняем оригинальные биомы и устанавливаем тайгу
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int x = -radius; x <= radius; x++) {
                    for (int z = -radius; z <= radius; z++) {
                        Location location = playerLocation.clone().add(x, 0, z);
                        Biome originalBiome = location.getBlock().getBiome();
                        originalBiomes.put(location, originalBiome);
                        location.getBlock().setBiome(Biome.TAIGA);
                    }
                }
            }
        }.runTask(this);

        // Сообщение в чат
        Bukkit.broadcastMessage(ChatColor.GREEN + "Новый год пришел, у вас есть 10 минут!");

        // Запуск таймера на 10 минут
        new BukkitRunnable() {
            @Override
            public void run() {
                // Возвращаем оригинальные биомы
                for (Map.Entry<Location, Biome> entry : originalBiomes.entrySet()) {
                    entry.getKey().getBlock().setBiome(entry.getValue());
                }

                // Сообщение о завершении ивента
                Bukkit.broadcastMessage(ChatColor.RED + "Новогодний ивент завершен!");
            }
        }.runTaskLater(this, 20L * 60 * 10); // 10 минут (600 секунд)
    }
}
