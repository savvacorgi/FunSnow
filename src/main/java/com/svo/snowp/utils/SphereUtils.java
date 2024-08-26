package com.svo.snowp.utils;

import com.svo.snowp.Snowp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereUtils {

    // Запуск ивента Нового года
    public static void startNewYearEvent(final JavaPlugin plugin, final World world) {
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

    // Создание предмета для ивента Нового года
    public static ItemStack createHappyNewYearItem() {
        ItemStack item = new ItemStack(Material.SNOW_BLOCK);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Happy New Year Block");
            List<String> lore = new ArrayList<>();
            lore.add("Place this block to start the New Year event!");
            meta.setLore(lore);
            item.setItemMeta(meta);
        }
        return item;
    }

    // Применение эффектов от сфер
    public static void applyCustomEffects(Player player, ItemStack item) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null || !meta.hasDisplayName()) return;

        String displayName = meta.getDisplayName();

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

    // Сброс атрибутов игрока
    public static void resetPlayerAttributes(Player player) {
        modifyAttribute(player, Attribute.GENERIC_ATTACK_DAMAGE, 0.0);
        modifyAttribute(player, Attribute.GENERIC_MAX_HEALTH, 0.0);
        modifyAttribute(player, Attribute.GENERIC_MOVEMENT_SPEED, 0.0);
        modifyAttribute(player, Attribute.GENERIC_ARMOR, 0.0);
        modifyAttribute(player, Attribute.GENERIC_ATTACK_SPEED, 0.0);
    }

    // Изменение атрибута игрока
    private static void modifyAttribute(Player player, Attribute attribute, double value) {
        var attributeInstance = player.getAttribute(attribute);
        if (attributeInstance != null) {
            attributeInstance.setBaseValue(attributeInstance.getBaseValue() + value);
        }
    }

    // Раздача случайной сферы
    public static void giveRandomSphere(Player player) {
        String[] sphereNames = {"EZ Sphere", "Turtle Sphere", "Miner Sphere", "Tank Sphere", "Dumb Sphere", "Tap Tap Monster Sphere"};
        Random random = new Random();
        String chosenSphere = sphereNames[random.nextInt(sphereNames.length)];

        ItemStack sphere = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = sphere.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(chosenSphere);
            sphere.setItemMeta(meta);
        }

        player.getInventory().addItem(sphere);
    }
                               }
                    
