package com.svo.snowp;

import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventManager {

    private final JavaPlugin plugin;
    private final SphereUtils sphereUtils;
    private final List<Location> giftLocations = new ArrayList<>();
    private boolean eventActive = false;

    public EventManager(JavaPlugin plugin, SphereUtils sphereUtils) {
        this.plugin = plugin;
        this.sphereUtils = sphereUtils;
    }

    public void startEvent() {
        if (eventActive) {
            plugin.getServer().broadcastMessage("Ивент уже активен!");
            return;
        }

        eventActive = true;
        plugin.getServer().broadcastMessage("Ивент начался! Найдите подарки!");

        World world = Bukkit.getWorlds().get(0); // Основной мир
        spawnGiftsInWorld(world);
        announceGiftLocations();
    }

    private void spawnGiftsInWorld(World world) {
        Random random = new Random();
        giftLocations.clear();

        for (int i = 0; i < 6; i++) { // 6 подарков
            int x = random.nextInt(9901) - 10000; // Координаты от -10000 до 10000
            int z = random.nextInt(9901) - 10000; // Координаты от -10000 до 10000
            int y = world.getHighestBlockYAt(x, z); // Высота

            Location location = new Location(world, x, y, z);

            Block block = world.getBlockAt(location);
            block.setType(Material.PLAYER_HEAD);

            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            item.getItemMeta().setDisplayName("Подарок");

            giftLocations.add(location);
        }

        // Удаление подарков по истечении времени
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            for (Location loc : giftLocations) {
                Block block = loc.getBlock();
                if (block.getType() == Material.PLAYER_HEAD) {
                    block.setType(Material.AIR);
                }
            }
            plugin.getServer().broadcastMessage("Ивент завершен! Подарки исчезли.");
            eventActive = false;
        }, 20 * 60 * 5); // 5 минут
    }

    private void announceGiftLocations() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("Подарки раскиданы по миру. Найдите их!");
        }
    }

    public boolean isEventActive() {
        return eventActive;
    }
}
