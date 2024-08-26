package com.svo.snowp.listeners;

import com.svo.snowp.Snowp;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
        Block block = event.getBlockPlaced();
        Block below = block.getRelative(BlockFace.DOWN);

        // Проверяем, что игрок поставил алмазный блок и окружен ли он снегом
        if (block.getType() == Material.DIAMOND_BLOCK && isSurroundedBySnow(below)) {
            block.setType(Material.AIR); // Удаляем алмазный блок
            plugin.startNewYearEvent(); // Запускаем ивент
        }
    }

    private boolean isSurroundedBySnow(Block block) {
        // Проверяем, окружен ли алмазный блок снежными блоками
        return block.getRelative(BlockFace.NORTH).getType() == Material.SNOW_BLOCK &&
               block.getRelative(BlockFace.SOUTH).getType() == Material.SNOW_BLOCK &&
               block.getRelative(BlockFace.EAST).getType() == Material.SNOW_BLOCK &&
               block.getRelative(BlockFace.WEST).getType() == Material.SNOW_BLOCK;
    }
}
