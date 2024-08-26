package com.svo.snowp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Random;

public class SphereUtils {

    public static void startNewYearEvent(final JavaPlugin plugin, final World world) {
        // Запуск ивента
        plugin.getLogger().info("New Year Event Started!");

        // Отправка уведомления в Telegram
        String message = "🎉 Event Started: New Year Celebration\nDescription: The New Year event has started! Enjoy the celebration.";
        TelegramNotifier telegramNotifier = new TelegramNotifier(plugin);
        telegramNotifier.sendMessage(message);

        // Установка биома на TAIGA
        int radius = 500;
        int centerX = world.getSpawnLocation().getBlockX();
        int centerZ = world.getSpawnLocation().getBlockZ();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int blockX = centerX + x;
                int blockZ = centerZ + z;
                world.setBiome(blockX, blockZ, org.bukkit.block.Biome.TAIGA);
            }
        }
    }

    public static ItemStack createHappyNewYearItem() {
        ItemStack item = new ItemStack(Material.SNOW_BLOCK); // Используем снежный блок
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Happy New Year");
            item.setItemMeta(meta);
        }
        return item;
    }

    public static void giveRandomSphere(Player player) {
        // Пример рандомного выбора сферы и выдачи игроку
        String[] spheres = {"ez sphere", "turtle sphere", "miner sphere", "tank sphere", "dumb sphere", "tap tap monster sphere"};
        Random random = new Random();
        String chosenSphere = spheres[random.nextInt(spheres.length)];

        ItemStack sphere = new ItemStack(Material.DIAMOND); // Замените на нужный тип
        ItemMeta meta = sphere.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(chosenSphere);
            sphere.setItemMeta(meta);
        }

        player.getInventory().addItem(sphere);
    }
}
