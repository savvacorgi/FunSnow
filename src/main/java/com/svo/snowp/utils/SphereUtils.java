package com.svo.snowp.utils;

import com.svo.snowp.Snowp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SphereUtils {

    public static void startNewYearEvent(final JavaPlugin plugin, final World world) {
        // Логика запуска ивента Нового года
        plugin.getLogger().info("New Year event has started!");

        // Уведомление в телеграм
        ((Snowp) plugin).notifyEventStarted("New Year Event", "The New Year event has started! Enjoy the celebration.");

        // Логика завершения ивента через 3 минуты
        new BukkitRunnable() {
            @Override
            public void run() {
                plugin.getLogger().info("New Year event has ended!");
            }
        }.runTaskLater(plugin, 3 * 60 * 20L); // 3 минуты = 180 секунд = 180*20 тиков
    }

    public static ItemStack createHappyNewYearItem() {
        ItemStack item = new ItemStack(Material.SNOW_BLOCK);
        // Дополнительная настройка предмета (имя, описание, и т.д.)
        return item;
    }
}
