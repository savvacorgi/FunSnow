package com.svo.snowp.listeners;

import com.svo.snowp.utils.Sphere;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;

import java.util.Random;

public class SphereListener implements Listener {

    private final SphereUtils sphereUtils;

    public SphereListener(SphereUtils sphereUtils) {
        this.sphereUtils = sphereUtils;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType() == Material.PLAYER_HEAD) {
            ItemStack item = event.getItem();
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.hasDisplayName()) {
                String displayName = meta.getDisplayName();

                // Убедитесь, что это подарок
                if (displayName.equals("Подарок")) {
                    // Удаляем голову и добавляем случайную сферу
                    event.getPlayer().getInventory().removeItem(item);

                    Sphere randomSphere = sphereUtils.getRandomSphere(); // Получение случайной сферы
                    ItemStack sphereItem = sphereUtils.createSphereItem(randomSphere);
                    event.getPlayer().getInventory().addItem(sphereItem);
                    event.getPlayer().sendMessage("Вы нашли подарок и получили: " + randomSphere.getName() + "!");
                }
            }
        }
    }
}
