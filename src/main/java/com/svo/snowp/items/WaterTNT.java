package com.svo.snowp.items;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class WaterTNT implements Listener {

    private final Plugin plugin;

    public WaterTNT(Plugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack createWaterTNT() {
        ItemStack item = new ItemStack(Material.TNT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§3Water TNT");
        meta.setLore(List.of("§4Этот TNT наносит урон, но не разрушает блоки"));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onPlayerUseTNT(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            ItemStack item = event.getItem();
            if (item != null && item.hasItemMeta() && "Water TNT".equals(item.getItemMeta().getDisplayName())) {
                event.setCancelled(true); // Отменяем обычное поведение TNT

                TNTPrimed tnt = (TNTPrimed) event.getPlayer().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.PRIMED_TNT);
                tnt.setYield(0); // Без разрушения блоков
                tnt.setIsIncendiary(false); // Не поджигает
                tnt.setFuseTicks(0); // Время до взрыва (0 секунды)

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getPlayer().getWorld().createExplosion(tnt.getLocation(), 4, false, false, event.getPlayer());
                    }
                }.runTaskLater(plugin, 0); // Задержка до взрыва, синхронизированная с TNT
            }
        }
    }
}
