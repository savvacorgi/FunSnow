package com.svo.snowp.listeners;

import com.svo.snowp.utils.Sphere;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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

                // Найти сферу по имени
                for (Sphere s : sphereUtils.getSpheres()) {
                    if (displayName.equals(s.getDisplayName())) {
                        // Применение эффектов сферы
                        Player player = event.getPlayer();
                        
                        if (s.getAttackBoost() > 0) {
                            player.sendMessage("Увеличение урона на " + s.getAttackBoost());
                            // Ваш код для увеличения урона игрока
                        }
                        if (s.getHealthReduction() < 0) {
                            player.sendMessage("Уменьшение здоровья на " + Math.abs(s.getHealthReduction()));
                            // Ваш код для уменьшения здоровья игрока
                            player.setHealth(Math.max(player.getHealth() + s.getHealthReduction(), 0));
                        }
                        if (s.getMovementSpeedBoost() > 0) {
                            player.sendMessage("Увеличение скорости передвижения на " + s.getMovementSpeedBoost());
                            // Ваш код для увеличения скорости передвижения
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, (int) s.getMovementSpeedBoost() - 1));
                        }
                        if (s.getDefenseBoost() > 0) {
                            player.sendMessage("Увеличение защиты на " + s.getDefenseBoost());
                            // Ваш код для увеличения защиты игрока
                            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 600, (int) s.getDefenseBoost() - 1));
                        }

                        // Удаление сферы из инвентаря
                        player.getInventory().removeItem(item);
                        player.sendMessage("Эффект сферы " + displayName + " активирован!");
                        break; // Прекратить обработку после нахождения сферы
                    }
                }
            }
        }
    }
}
