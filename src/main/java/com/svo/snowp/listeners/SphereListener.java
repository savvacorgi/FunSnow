package com.svo.snowp.listeners;

import com.svo.snowp.EventManager;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.event.Listener;

public class SphereListener implements Listener {
    private final EventManager eventManager;
    private final SphereUtils sphereUtils;

    public SphereListener(EventManager eventManager, SphereUtils sphereUtils) {
        this.eventManager = eventManager;
        this.sphereUtils = sphereUtils;
    }

    // Обработчики событий
}
