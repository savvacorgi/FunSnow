package com.svo.snowp.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;

public class WaterTNT implements Listener {

    private final Plugin plugin;

    public WaterTNT(Plugin plugin) {
        this.plugin = plugin;
        initRecipe();
    }

    private void initRecipe() {
        ItemStack item = new ItemStack(Material.TNT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bВодяная TNT");
        meta.setLore(Collections.singletonList("§4Этот TNT наносит урон, но не разрушает блоки"));
        meta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
        item.setItemMeta(meta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(plugin, "water_tnt"), item);
        recipe.shape("SSS", "STC", "SSS");
        recipe.setIngredient('S', Material.SAND);
        recipe.setIngredient('T', Material.TNT);
        recipe.setIngredient('C', Material.WATER_BUCKET);

        plugin.getServer().addRecipe(recipe);
    }

    @EventHandler
    public void onPlayerUseTNT(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType() != Material.TNT) {
            return;
        }

        ItemStack item = event.getItem();
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals("§bВодяная TNT")) {
            event.setCancelled(true); // Предотвращаем стандартное поведение

            // Спавним TNT, которое взрывается мгновенно и не разрушает блоки
            TNTPrimed tnt = (TNTPrimed) event.getPlayer().getWorld().spawn(event.getPlayer().getLocation(), TNTPrimed.class);
            tnt.setFuseTicks(0); // Мгновенный взрыв
            tnt.setYield(4F); // Мощность взрыва
            tnt.setIsIncendiary(false); // Без разрушения блоков
        }
    }
}
