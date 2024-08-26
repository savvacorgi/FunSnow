package com.svo.snowp.listeners;

import com.svo.snowp.utils.SphereUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockPlaceListener implements Listener {

    private final JavaPlugin plugin;

    public BlockPlaceListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        ItemStack item = event.getItemInHand();

        if (SphereUtils.isGiftBox(item, plugin)) {
            SphereUtils.openGiftBox(event.getPlayer(), item, plugin);
            event.getBlockPlaced().setType(Material.AIR); // Удаляем блок после открытия
        }
    }
}
