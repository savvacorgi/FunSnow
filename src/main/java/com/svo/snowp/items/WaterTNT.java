package com.svo.snowp.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class WaterTNT implements Listener {

    private final JavaPlugin plugin;

    public WaterTNT(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void registerEvents() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public ItemStack createWaterTNT() {
        ItemStack tnt = new ItemStack(Material.TNT);
        ItemMeta meta = tnt.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§bWater TNT");
            meta.setLore(List.of("§4Этот TNT наносит урон, но не разрушает блоки"));
            tnt.setItemMeta(meta);
        }
        return tnt;
    }

    public void addRecipe() {
        ItemStack waterTNT = createWaterTNT();
        ShapedRecipe recipe = new ShapedRecipe(waterTNT);

        // Определяем рецепт: 2 песка и 2 TNT
        recipe.shape("TT ", "T S", "T S");
        recipe.setIngredient('T', Material.TNT);
        recipe.setIngredient('S', Material.SAND);

        Bukkit.addRecipe(recipe);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getItem() != null && event.getItem().isSimilar(createWaterTNT())) {
            ItemStack item = event.getItem();
            if (item != null && item.getAmount() > 0) {
                // Устанавливаем TNT на месте клика
                TNTPrimed tnt = (TNTPrimed) event.getClickedBlock().getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0.5, 0.5, 0.5), EntityType.PRIMED_TNT);
                
                // Настраиваем TNT
                tnt.setIsIncendiary(false);
                tnt.setYield(0);
                
                // Устанавливаем обработчик события активации TNT
                tnt.setFuseTicks(80); // Устанавливаем задержку перед взрывом (в тиках)

                // Уменьшаем количество использованных предметов
                item.setAmount(item.getAmount() - 1);
                player.sendMessage("§aTNT установлено. Используйте зажигалку, чтобы активировать его.");
            }
        }
    }

    @EventHandler
    public void onTNTExplosion(org.bukkit.event.entity.EntityExplodeEvent event) {
        if (event.getEntityType() == EntityType.PRIMED_TNT) {
            TNTPrimed tnt = (TNTPrimed) event.getEntity();
            // Наносим урон всем игрокам в радиусе взрыва
            tnt.getWorld().getNearbyEntities(tnt.getLocation(), 5, 5, 5).forEach(entity -> {
                if (entity instanceof Player) {
                    Player player = (Player) entity;
                    player.damage(10); // Наносим 10 единиц урона
                }
            });
            
            // Останавливаем разрушение блоков
            event.blockList().clear();
        }
    }
}
