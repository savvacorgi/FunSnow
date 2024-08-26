package com.svo.snowp;

import com.svo.snowp.listeners.BlockPlaceListener;
import com.svo.snowp.listeners.SphereListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Snowp extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SphereListener(this), this);
        getLogger().info("Snowp plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Snowp plugin disabled.");
    }

    public void startNewYearEvent() {
        getLogger().info("New Year event started!");

        // Сообщение в чат
        Bukkit.broadcastMessage("§bНовый год пришел, у вас есть 10 минут!");

        // Запуск ивента (например, спавн снежных сундуков и изменение биома)
        // Здесь добавляется ваш код для спавна сундуков и изменения мира
    }
}
