package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
        ItemStack itemInHand = event.getItemInHand();
        Player player = event.getPlayer();

        // Проверяем, является ли предмет "Happy New Year Firework"
        if (itemInHand != null && itemInHand.getType() == Material.FIREWORK_ROCKET) {
            ItemMeta meta = itemInHand.getItemMeta();
            if (meta != null && ChatColor.stripColor(meta.getDisplayName()).equalsIgnoreCase("Happy New Year Firework")) {
                // Запускаем событие Нового года
                SphereUtils.startNewYearEvent(player, plugin);
                event.setCancelled(true); // Отменяем установку блока
            }
        }
    }
}
