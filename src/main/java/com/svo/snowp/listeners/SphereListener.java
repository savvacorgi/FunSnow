package com.svo.snowp.listeners;

import com.svo.snowp.utils.Sphere;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
                Sphere sphere = sphereUtils.getRandomSphere(); // Получение сферы для обработки эффекта

                // Проверка имени и применение эффекта
                if (displayName.equals(sphere.getDisplayName())) {
                    // Применение эффектов сферы
                    if (sphere.getAttackBoost() > 0) {
                        // Ваш код для увеличения урона
                    }
                    if (sphere.getHealthReduction() < 0) {
                        // Ваш код для уменьшения здоровья
                    }
                    if (sphere.getMovementSpeedBoost() > 0) {
                        // Ваш код для увеличения скорости
                    }
                    if (sphere.getDefenseBoost() > 0) {
                        // Ваш код для увеличения защиты
                    }

                    // Удаление сферы из инвентаря
                    event.getPlayer().getInventory().removeItem(item);
                    event.getPlayer().sendMessage("Эффект сферы " + displayName + " активирован!");
                }
            }
        }
    }
}
