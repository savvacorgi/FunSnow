package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    private final Snowp plugin;

    public BlockPlaceListener(Snowp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.SNOW_BLOCK) {
            // Удаляем снежный блок
            block.setType(Material.AIR);

            // Запускаем ивент
            startNewYearEvent();
        }
    }

    private void startNewYearEvent() {
        // Запуск ивента
        plugin.notifyEventStarted("New Year Event", "The New Year event has started! Enjoy the celebration.");
        
        // Ваша логика для начала ивента
    }
}
