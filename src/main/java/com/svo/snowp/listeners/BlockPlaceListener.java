package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BlockPlaceListener implements Listener {
    private final Snowp plugin;

    public BlockPlaceListener(Snowp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = event.getItemInHand();
        Block block = event.getBlockPlaced();

        if (block.getType() == Material.FIREWORK_ROCKET) {
            ItemMeta meta = itemInHand.getItemMeta();
            if (meta != null && meta.getDisplayName().equals("§6Happy New Year")) {
                block.setType(Material.AIR);  // Убираем блок фейерверка после установки

                // Запускаем ивент
                SphereUtils.startNewYearEvent(player, plugin);
            }
        }
    }
}
