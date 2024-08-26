package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SphereListener implements Listener {
    private final Snowp plugin;

    public SphereListener(Snowp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if (item != null && item.getType() == Material.PLAYER_HEAD) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getDisplayName().equals("§dPresent Magenta")) {
                event.setCancelled(true);  // Отменяем стандартное действие

                // Убираем кейс
                item.setAmount(0);

                // Выдаем случайную сферу
                SphereUtils.giveRandomSphere(player);
            }
        }
    }
}
