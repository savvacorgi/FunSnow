package com.svo.snowp.utils;

import com.svo.snowp.Snowp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;

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

    public static void openDebugSnowGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.BLUE + "Debug Snow GUI");

        inventory.addItem(createSphereItem("EZ Sphere", ChatColor.AQUA + "+5 Урон, +10 Скорость"));
        inventory.addItem(createSphereItem("Turtle Sphere", ChatColor.DARK_GREEN + "-25% Скорость, +4 Урон"));
        inventory.addItem(createSphereItem("Miner Sphere", ChatColor.GRAY + "Спешка 2, Огнестойкость"));
        inventory.addItem(createSphereItem("Tank Sphere", ChatColor.GOLD + "+4 Броня, +4 HP"));
        inventory.addItem(createSphereItem("Dumb Sphere", ChatColor.RED + "+6 Урон, -3 Броня"));
        inventory.addItem(createSphereItem("Tap Tap Monster Sphere", ChatColor.YELLOW + "+15 Скорость Атаки"));

        player.openInventory(inventory);
    }

    private static ItemStack createSphereItem(String name, String lore) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
        meta.setLore(Collections.singletonList(ChatColor.GRAY + lore));

        // Устанавливаем владельца головы
        meta.setOwner("savvacorgi");

        item.setItemMeta(meta);
        return item;
    }
}
