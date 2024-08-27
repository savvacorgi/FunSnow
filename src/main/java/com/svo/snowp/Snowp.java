package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.svo.snowp.listeners.SphereListener;
import com.svo.snowp.utils.SphereUtils;

public class Snowp extends JavaPlugin {

    private SphereUtils sphereUtils;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        sphereUtils = new SphereUtils();
        eventManager = new EventManager(this);

        Bukkit.getPluginManager().registerEvents(new SphereListener(eventManager, sphereUtils), this);

        // Автоматический запуск ивента при старте сервера
        eventManager.startEvent();
    }

    @Override
    public void onDisable() {
        // Логика при отключении плагина
    }
}
