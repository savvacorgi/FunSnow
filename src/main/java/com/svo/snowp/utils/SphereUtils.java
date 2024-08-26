package com.svo.snowp.utils;

import com.svo.snowp.Snowp;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class SphereUtils {

    public static ItemStack createHappyNewYearBlock() {
        ItemStack item = new ItemStack(Material.SNOW_BLOCK);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.GREEN + "Happy New Year Block");
            item.setItemMeta(meta);
        }
        return item;
    }

    public static void startNewYearEvent(final JavaPlugin plugin, final World world) {
        // Сообщение в чат
        Bukkit.broadcastMessage(ChatColor.GOLD + "Новый год пришел! У вас есть 10 минут!");

        // Заменяем биомы на тайгу
        for (World w : Bukkit.getWorlds()) {
            changeBiomeToTaiga(w);
        }

        // Спавн случайных кейсов
        new BukkitRunnable() {
            @Override
            public void run() {
                for (World w : Bukkit.getWorlds()) {
                    spawnRandomCase(w);
                }
            }
        }.runTaskTimer(plugin, 0L, 20L * 60L);

        // Завершение ивента через 10 минут
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.RED + "Ивент Новый год завершен!");
                // Очищаем снежные блоки и случайные кейсы
            }
        }.runTaskLater(plugin, 20L * 60L * 10L);
    }

    private static void changeBiomeToTaiga(World world) {
        int radius = 500;
        int centerX = world.getSpawnLocation().getBlockX();
        int centerZ = world.getSpawnLocation().getBlockZ();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int blockX = centerX + x;
                int blockZ = centerZ + z;
                world.setBiome(blockX, blockZ, Biome.TAIGA);
            }
        }
    }

    public static void spawnRandomCase(World world) {
        Random random = new Random();
        if (random.nextInt(100) < 5) { // 5% шанс спавна
            int x = random.nextInt(1000) - 500;
            int z = random.nextInt(1000) - 500;
            int y = world.getHighestBlockYAt(x, z);
            Block block = world.getBlockAt(x, y, z);

            if (block.getType() == Material.GRASS_BLOCK || block.getType() == Material.SNOW_BLOCK) {
                block.setType(Material.PLAYER_HEAD);
                // Устанавливаем текстуру головы
                ItemStack head = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) head.getItemMeta();
                if (meta != null) {
                    meta.setOwningPlayer(Bukkit.getOfflinePlayer("MHF_Present")); // Используйте нужного игрока или текстуру
                    head.setItemMeta(meta);
                }
                block.getState().update(true);
            }
        }
    }
}
