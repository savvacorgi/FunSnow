package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.svo.snowp.items.CustomWaterTNT;
import com.svo.snowp.listeners.SphereListener;
import com.svo.snowp.utils.SphereUtils;

public class Snowp extends JavaPlugin {

    private SphereUtils sphereUtils;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        // Инициализация утилит и менеджера событий
        sphereUtils = new SphereUtils();
        eventManager = new EventManager(this);

        // Регистрация слушателей событий
        Bukkit.getPluginManager().registerEvents(new SphereListener(sphereUtils), this);

        // Регистрация Water TNT и его рецепта
        CustomWaterTNT customWaterTNT = new CustomWaterTNT(this);
        Bukkit.getPluginManager().registerEvents(customWaterTNT, this);
        customWaterTNT.addRecipe();
    }

    @Override
    public void onDisable() {
        // Логика при отключении плагина
    }
}
