package com.svo.snowp;

import com.svo.snowp.listeners.BlockPlaceListener;
import com.svo.snowp.listeners.SphereListener;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

public class Snowp extends JavaPlugin implements Listener {

    private final Set<String> activeCases = new HashSet<>();
    private boolean newYearEventActive = false;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new SphereListener(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("Snowp plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Snowp plugin disabled.");
    }

    @EventHandler
    public void onCraft(CraftItemEvent event) {
        if (isNewYearCraft(event)) {
            event.getInventory().setResult(createEnchantedDiamondBlock());
        }
    }

    private boolean isNewYearCraft(CraftItemEvent event) {
        return event.getRecipe().getResult().getType() == Material.DIAMOND_BLOCK &&
                event.getInventory().contains(Material.SNOW_BLOCK, 8);
    }

    private ItemStack createEnchantedDiamondBlock() {
        ItemStack enchantedBlock = new ItemStack(Material.DIAMOND_BLOCK);
        ItemMeta meta = enchantedBlock.getItemMeta();
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.setDisplayName("New Year Block");
        enchantedBlock.setItemMeta(meta);
        return enchantedBlock;
    }

    public void startNewYearEvent() {
        if (newYearEventActive) return;

        newYearEventActive = true;
        Bukkit.broadcastMessage("Новый год пришел, у вас есть 10 минут!");

        // Schedule the end of the event after 10 minutes (12000 ticks)
        new BukkitRunnable() {
            @Override
            public void run() {
                endNewYearEvent();
            }
        }.runTaskLater(this, 12000L);
    }

    public void endNewYearEvent() {
        Bukkit.broadcastMessage("Новогодний ивент закончился!");
        SphereUtils.removeAllCases();
        newYearEventActive = false;
    }

    public void registerCase(String caseId) {
        activeCases.add(caseId);
    }

    public boolean isCaseActive(String caseId) {
        return activeCases.contains(caseId);
    }

    public void unregisterCase(String caseId) {
        activeCases.remove(caseId);
    }
}
