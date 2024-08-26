package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {

    private final Snowp plugin;

    public BlockPlaceListener(Snowp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        Block placedBlock = event.getBlockPlaced();

        if (itemInHand.getType() == Material.DIAMOND_BLOCK && itemInHand.getItemMeta().hasEnchant(org.bukkit.enchantments.Enchantment.LUCK)) {
            plugin.startNewYearEvent();
            placedBlock.setType(Material.AIR); // Remove the block after placing
        }
    }
}
