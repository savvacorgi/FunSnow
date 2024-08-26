package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SphereListener implements Listener {

    private final Snowp plugin;

    public SphereListener(Snowp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        if (itemInHand != null && itemInHand.getType() == Material.PLAYER_HEAD) {
            String caseId = itemInHand.getItemMeta().getDisplayName();

            if (plugin.isCaseActive(caseId)) {
                SphereUtils.giveRandomSphere(player);
                plugin.unregisterCase(caseId);
                player.getInventory().remove(itemInHand);
            }
        }
    }
}
