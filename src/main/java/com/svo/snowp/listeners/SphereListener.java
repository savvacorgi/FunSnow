package com.svo.snowp.listeners;

import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class SphereListener implements Listener {

    @EventHandler
    public void onPlayerHoldItem(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItem(event.getNewSlot());

        if (itemInHand != null && itemInHand.getType() == Material.PLAYER_HEAD) {
            SphereUtils.applyCustomEffects(player, itemInHand);
        } else {
            SphereUtils.resetPlayerAttributes(player);
        }
    }
}
