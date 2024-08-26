package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.Material;

public class SphereListener implements Listener {

    private final Snowp plugin;

    public SphereListener(Snowp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        Player player = event.getPlayer();

        if (block != null && block.getType() == Material.PLAYER_HEAD) {
            SphereUtils.giveRandomSphere(player);
            block.setType(Material.AIR); // Удаляем кейс после открытия
        }
    }
}
