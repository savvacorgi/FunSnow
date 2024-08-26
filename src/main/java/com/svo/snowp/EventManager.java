package com.svo.snowp;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private static final long EVENT_DURATION = 5 * 60 * 1000; // 5 минут в миллисекундах
    private static final long COOLDOWN_DURATION = 10 * 60 * 1000; // 10 минут в миллисекундах

    private final Map<Player, Long> eventStartTimes = new HashMap<>();
    private final Map<Player, Long> cooldownEndTimes = new HashMap<>();

    // Проверка, можно ли запустить ивент для игрока
    public boolean canStartEvent(Player player) {
        long now = System.currentTimeMillis();
        if (cooldownEndTimes.containsKey(player)) {
            long cooldownEnd = cooldownEndTimes.get(player);
            if (now < cooldownEnd) {
                return false; // Находится в кулдауне
            }
        }
        return true;
    }

    // Начало ивента для игрока
    public void startEvent(Player player) {
        long now = System.currentTimeMillis();
        eventStartTimes.put(player, now);
        cooldownEndTimes.put(player, now + COOLDOWN_DURATION);

        // Запуск планировщика для завершения ивента
        scheduleEventEnd(player);
    }

    // Проверка, истекло ли время ивента
    public boolean isEventActive(Player player) {
        if (!eventStartTimes.containsKey(player)) {
            return false;
        }
        long now = System.currentTimeMillis();
        long eventStart = eventStartTimes.get(player);
        return now < eventStart + EVENT_DURATION;
    }

    // Завершение ивента для игрока
    public void endEvent(Player player) {
        eventStartTimes.remove(player);
        player.sendMessage("Event ended.");
    }

    // Планирование завершения ивента
    private void scheduleEventEnd(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isEventActive(player)) {
                    endEvent(player);
                }
            }
        }.runTaskLater(YourPlugin.getInstance(), EVENT_DURATION / 50); // Делим на 50, чтобы получить тики
    }
}
