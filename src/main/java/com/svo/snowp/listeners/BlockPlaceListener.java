package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockPlaceListener implements Listener {

    private final JavaPlugin plugin;

    public BlockPlaceListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (block.getType() == Material.SNOW_BLOCK && event.getPlayer().hasPermission("snowp.startevent")) {
            // Удаляем блок
            block.setType(Material.AIR);

            // Запускаем ивент
            SphereUtils.startNewYearEvent(plugin, block.getWorld());
        }
    }
}
