package com.svo.snowp.utils;

import com.svo.snowp.Snowp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.block.Block;

import java.util.Collections;
import java.util.Random;

public class SphereUtils {

    public static void applyCustomEffects(Player player, ItemStack item) {
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        String displayName = ChatColor.stripColor(meta.getDisplayName());

        resetPlayerAttributes(player);

        switch (displayName.toLowerCase()) {
            case "ez sphere":
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE, 5.0);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH, -3.5);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MOVEMENT_SPEED, 0.1);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ARMOR, 2.0);
                break;
            case "turtle sphere":
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MOVEMENT_SPEED, -0.25);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE, 4.0);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (player.isOnline() && player.getInventory().getItemInMainHand().equals(item)) {
                            player.setHealth(Math.min(player.getHealth() + 1, player.getMaxHealth()));
                        } else {
                            cancel();
                        }
                    }
                }.runTaskTimer(JavaPlugin.getPlugin(Snowp.class), 0L, 40L);
                break;
            case "miner sphere":
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_SPEED, 2.0);
                player.setFireTicks(0);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH, -2.0);
                break;
            case "tank sphere":
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ARMOR, 4.0);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH, 4.0);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE, -4.0);
                break;
            case "dumb sphere":
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE, 6.0);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH, -2.0);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ARMOR, -3.0);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MOVEMENT_SPEED, -0.1);
                break;
            case "tap tap monster sphere":
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_SPEED, 3.0);
                modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE, -5.5);
                break;
            default:
                break;
        }
    }

    public static void resetPlayerAttributes(Player player) {
        modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_DAMAGE, 0.0);
        modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MAX_HEALTH, 0.0);
        modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_MOVEMENT_SPEED, 0.0);
        modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ARMOR, 0.0);
        modifyAttribute(player, org.bukkit.attribute.Attribute.GENERIC_ATTACK_SPEED, 0.0);
    }

    private static void modifyAttribute(Player player, org.bukkit.attribute.Attribute attribute, double value) {
        org.bukkit.attribute.AttributeInstance attributeInstance = player.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(attributeInstance.getBaseValue() + value);
        }
    }

    public static void changeBiomeToTaiga(Player player) {
        World world = player.getWorld();
        int radius = 500;
        int centerX = player.getLocation().getBlockX();
        int centerZ = player.getLocation().getBlockZ();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int blockX = centerX + x;
                int blockZ = centerZ + z;
                world.setBiome(blockX, blockZ, org.bukkit.block.Biome.TAIGA);
            }
        }
    }

    public static void spawnRandomCase(World world) {
        Random random = new Random();
        if (random.nextInt(100) < 1) { // 1% шанс спавна
            int x = random.nextInt(1000) - 500;
            int z = random.nextInt(1000) - 500;
            int y = world.getHighestBlockYAt(x, z);
            Block block = world.getBlockAt(x, y, z);

            if (block.getType() == Material.GRASS_BLOCK || block.getType() == Material.SNOW_BLOCK) {
                block.setType(Material.PLAYER_HEAD);
            }
        }
    }
}
