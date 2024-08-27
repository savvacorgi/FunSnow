package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class GiftManager {

    private final Plugin plugin;
    private final Random random = new Random();

    public GiftManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void spawnGifts(int amount) {
        World world = Bukkit.getWorld("world"); // Имя мира, в котором будут спавниться подарки
        if (world == null) return;

        for (int i = 0; i < amount; i++) {
            int x = random.nextInt(2000) - 1000; // Координаты от -1000 до 1000
            int z = random.nextInt(2000) - 1000;
            int y = getHighestY(world, x, z); // Находим высоту для спавна подарка

            Location location = new Location(world, x, y, z);
            dropGift(world, location);
            announceGiftLocation(location);
        }
    }

    private int getHighestY(World world, int x, int z) {
        return world.getHighestBlockYAt(x, z) + 1; // Высота над самым высоким блоком в координатах
    }

    private void dropGift(World world, Location location) {
        ItemStack gift = new ItemStack(Material.CHEST); // Подарок представлен сундуком
        world.dropItemNaturally(location, gift);
    }

    private void announceGiftLocation(Location location) {
        String message = String.format("Подарок появился вблизи координат: X: %d, Y: %d, Z: %d", 
                                        location.getBlockX(), 
                                        location.getBlockY(), 
                                        location.getBlockZ());
        Bukkit.broadcastMessage(message);
    }
}
