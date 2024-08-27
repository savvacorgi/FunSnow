package com.svo.snowp.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomWaterTNT implements Listener {

    private final JavaPlugin plugin;

    public CustomWaterTNT(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void addRecipe() {
        ItemStack customWaterTNT = new ItemStack(Material.TNT);
        customWaterTNT.getItemMeta().setDisplayName("§bWater TNT");
        ShapedRecipe recipe = new ShapedRecipe(customWaterTNT);
        recipe.shape("WWW", "WTW", "WWW");
        recipe.setIngredient('W', Material.WATER_BUCKET);
        recipe.setIngredient('T', Material.TNT);
        plugin.getServer().addRecipe(recipe);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.TNT && "§bWater TNT".equals(event.getItem().getCustomModelData())) {
            Block block = event.getClickedBlock();
            if (block != null && block.getType() == Material.TNT) {
                // Отслеживаем клик по TNT
                event.getPlayer().sendMessage("Water TNT активировано!");
                // Запускаем отсчет времени до взрыва
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        explode(block.getWorld(), block.getLocation());
                    }
                }.runTaskLater(plugin, 100L); // Взрыв через 5 секунд
            }
        }
    }

    @EventHandler
    public void onRedstonePower(BlockRedstoneEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.TNT) {
            // Проверяем, что это кастомное TNT
            if (block.getState().getData() instanceof org.bukkit.material.TNT) {
                // Запускаем отсчет времени до взрыва
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        explode(block.getWorld(), block.getLocation());
                    }
                }.runTaskLater(plugin, 100L); // Взрыв через 5 секунд
            }
        }
    }

    private void explode(World world, Location location) {
        world.createExplosion(location, 4F, false, false);
        for (Entity entity : world.getNearbyEntities(location, 4, 4, 4)) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.damage(10.0); // Наносим урон игрокам в радиусе взрыва
            }
        }
    }
}
