package com.svo.snowp.utils;

import com.svo.snowp.Snowp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class SphereUtils {

    public static void applyCustomEffects(Player player, ItemStack item) {
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        String displayName = ChatColor.stripColor(meta.getDisplayName());

        resetPlayerAttributes(player);

        switch (displayName.toLowerCase()) {
            case "ez sphere":
                modifyAttribute(player, Attribute.GENERIC_ATTACK_DAMAGE, 5.0);
                modifyAttribute(player, Attribute.GENERIC_MAX_HEALTH, -3.5);
                modifyAttribute(player, Attribute.GENERIC_MOVEMENT_SPEED, 0.1);
                modifyAttribute(player, Attribute.GENERIC_ARMOR, 2.0);
                break;
            case "turtle sphere":
                modifyAttribute(player, Attribute.GENERIC_MOVEMENT_SPEED, -0.25);
                modifyAttribute(player, Attribute.GENERIC_ATTACK_DAMAGE, 4.0);
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
                modifyAttribute(player, Attribute.GENERIC_ATTACK_SPEED, 2.0);
                player.setFireTicks(0);
                modifyAttribute(player, Attribute.GENERIC_MAX_HEALTH, -2.0);
                break;
            case "tank sphere":
                modifyAttribute(player, Attribute.GENERIC_ARMOR, 4.0);
                modifyAttribute(player, Attribute.GENERIC_MAX_HEALTH, 4.0);
                modifyAttribute(player, Attribute.GENERIC_ATTACK_DAMAGE, -4.0);
                break;
            case "dumb sphere":
                modifyAttribute(player, Attribute.GENERIC_ATTACK_DAMAGE, 6.0);
                modifyAttribute(player, Attribute.GENERIC_MAX_HEALTH, -2.0);
                modifyAttribute(player, Attribute.GENERIC_ARMOR, -3.0);
                modifyAttribute(player, Attribute.GENERIC_MOVEMENT_SPEED, -0.1);
                break;
            case "tap tap monster sphere":
                modifyAttribute(player, Attribute.GENERIC_ATTACK_SPEED, 3.0);
                modifyAttribute(player, Attribute.GENERIC_ATTACK_DAMAGE, -5.5);
                break;
            default:
                break;
        }
    }

    public static void resetPlayerAttributes(Player player) {
        modifyAttribute(player, Attribute.GENERIC_ATTACK_DAMAGE, 0.0);
        modifyAttribute(player, Attribute.GENERIC_MAX_HEALTH, 0.0);
        modifyAttribute(player, Attribute.GENERIC_MOVEMENT_SPEED, 0.0);
        modifyAttribute(player, Attribute.GENERIC_ARMOR, 0.0);
        modifyAttribute(player, Attribute.GENERIC_ATTACK_SPEED, 0.0);
    }

    private static void modifyAttribute(Player player, Attribute attribute, double value) {
        var attributeInstance = player.getAttribute(attribute);
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
        if (random.nextInt(100) < 8) { // 8% шанс спавна в чанке
            int x = random.nextInt(16);
            int z = random.nextInt(16);
            int y = world.getHighestBlockYAt(x, z);
            Block block = world.getBlockAt(x, y, z);

            if (block.getType() == Material.GRASS_BLOCK || block.getType() == Material.SNOW_BLOCK) {
                block.setType(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) block.getState().getBlock().getState();
                meta.setOwningPlayer(Bukkit.getOfflinePlayer("savvacorgi"));
                block.getState().update();
            }
        }
    }

    public static ItemStack createSphereItem(String name) {
        ItemStack sphere = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) sphere.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + name);
            meta.setOwningPlayer(Bukkit.getOfflinePlayer("savvacorgi"));
            sphere.setItemMeta(meta);
        }

        return sphere;
    }

    public static void giveRandomSphere(Player player) {
        Random random = new Random();
        int roll = random.nextInt(100);

        ItemStack sphere;

        if (roll < 50) { // 50% шанс
            sphere = createSphereItem("EZ Sphere");
        } else if (roll < 80) { // 30% шанс
            sphere = createSphereItem("Turtle Sphere");
        } else if (roll < 95) { // 15% шанс
            sphere = createSphereItem("Miner Sphere");
        } else { // 5% шанс
            sphere = createSphereItem("Tank Sphere");
        }

        player.getInventory().addItem(sphere);
        player.sendMessage(ChatColor.GREEN + "You received: " + sphere.getItemMeta().getDisplayName());
    }
}
