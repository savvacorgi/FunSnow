package com.svo.snowp.listeners;

import com.svo.snowp.EventManager;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SphereListener implements Listener {
    private final EventManager eventManager;
    private final SphereUtils sphereUtils;

    public SphereListener(EventManager eventManager, SphereUtils sphereUtils) {
        this.eventManager = eventManager;
        this.sphereUtils = sphereUtils;
    }

    // Спавн подарков (голов)
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItemInHand();

        if (item.getType() == Material.PLAYER_HEAD) {
            Block block = event.getBlock();
            BlockState state = block.getState();

            if (state instanceof Skull) {
                Skull skull = (Skull) state;
                // Дополнительная логика для спавна подарков
                Bukkit.getScheduler().runTaskLater(Bukkit.getPluginManager().getPlugin("FunSnow"), () -> {
                    sphereUtils.openGiftBox(player, item, Bukkit.getPluginManager().getPlugin("FunSnow"));
                }, 20L); // 20L = 1 секунда задержки
            }
        }
    }

    // Открытие подарков
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (block != null && block.getType() == Material.PLAYER_HEAD) {
            Skull skull = (Skull) block.getState();
            // Логика открытия подарка
            sphereUtils.openGiftBox(player, new ItemStack(Material.PLAYER_HEAD), Bukkit.getPluginManager().getPlugin("FunSnow"));
        }
    }
}
