package com.svo.snowp;

import com.svo.snowp.commands.DebugSnowCommand;
import com.svo.snowp.listeners.SphereListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Snowp extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new SphereListener(), this);
        this.getCommand("debugsnow").setExecutor(new DebugSnowCommand());
        getLogger().info("Snowp plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Snowp plugin disabled.");
    }
}
