package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.svo.snowp.listeners.SphereListener;
import com.svo.snowp.utils.SphereUtils;
import com.svo.snowp.items.WaterTNT;

public class Snowp extends JavaPlugin {

    private SphereUtils sphereUtils;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        // Инициализация утилит и менеджера событий
        sphereUtils = new SphereUtils();
        eventManager = new EventManager(this);

        // Регистрация слушателей событий
        Bukkit.getPluginManager().registerEvents(new SphereListener(eventManager, sphereUtils), this);

        // Регистрация Water TNT и его рецепта
        WaterTNT waterTNT = new WaterTNT(this);
        Bukkit.getPluginManager().registerEvents(waterTNT, this);
        waterTNT.addRecipe();

        // Автоматический запуск ивента при старте сервера
        eventManager.startEvent();
    }

    @Override
    public void onDisable() {
        // Логика при отключении плагина
    }
}
