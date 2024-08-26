package com.svo.snowp.listeners;

import com.svo.snowp.Snowp; // Убедитесь, что класс Snowp существует
import com.svo.snowp.utils.SphereUtils; // Убедитесь, что класс SphereUtils существует
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {
    private final Snowp plugin;

    public BlockPlaceListener(Snowp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack item = new ItemStack(event.getBlockPlaced().getType());
        SphereUtils.openGiftBox(player, item, plugin);
    }
}
