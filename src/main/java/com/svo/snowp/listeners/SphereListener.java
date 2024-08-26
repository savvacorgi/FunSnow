package com.svo.snowp.listeners;

import com.svo.snowp.EventManager;
import com.svo.snowp.utils.Sphere;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SphereListener implements Listener {
    private final EventManager eventManager;
    private final SphereUtils sphereUtils;

    public SphereListener(EventManager eventManager, SphereUtils sphereUtils) {
        this.eventManager = eventManager;
        this.sphereUtils = sphereUtils;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == org.bukkit.Material.PLAYER_HEAD) {
            // Проверяем, является ли предмет Sphere
            Sphere sphere = sphereUtils.getSphereFromItem(item);
            if (sphere != null) {
                // Реализуйте логику взаимодействия с Sphere
                if (eventManager.canStartEvent(player)) {
                    eventManager.startEvent(player);
                    player.sendMessage("Event started!");
                } else {
                    player.sendMessage("You must wait before starting another event.");
                }
            }
        }
    }
}
