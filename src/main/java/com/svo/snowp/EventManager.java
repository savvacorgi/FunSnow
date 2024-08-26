package com.svo.snowp;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.HashMap;
import java.util.Map;

public class EventManager {
    private static final long EVENT_DURATION = 5 * 60 * 20; // 5 минут в тиках
    private static final long COOLDOWN_DURATION = 10 * 60 * 20; // 10 минут в тиках

    private final Map<Player, Long> eventStartTimes = new HashMap<>();
    private final Map<Player, Long> cooldownEndTimes = new HashMap<>();

    public boolean canStartEvent(Player player) {
        long now = System.currentTimeMillis();
        if (cooldownEndTimes.containsKey(player)) {
            long cooldownEnd = cooldownEndTimes.get(player);
            return now >= cooldownEnd;
        }
        return true;
    }

    public void startEvent(Player player) {
        long now = System.currentTimeMillis();
        eventStartTimes.put(player, now);
        cooldownEndTimes.put(player, now + COOLDOWN_DURATION);

        new BukkitRunnable() {
            @Override
            public void run() {
                endEvent(player);
            }
        }.runTaskLater(Snowp.getInstance(), EVENT_DURATION);
    }

    public boolean isEventActive(Player player) {
        Long startTime = eventStartTimes.get(player);
        if (startTime == null) {
            return false;
        }
        return System.currentTimeMillis() - startTime < EVENT_DURATION;
    }

    public void endEvent(Player player) {
        eventStartTimes.remove(player);
        player.sendMessage("Event ended.");
    }
}
