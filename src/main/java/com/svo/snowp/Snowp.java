package com.svo.snowp;

import com.svo.snowp.commands.DebugSnowCommand;
import com.svo.snowp.listeners.SphereListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Snowp extends JavaPlugin {

    @Override
public void onEnable() {
    Bukkit.getPluginManager().registerEvents(new SphereListener(), this);
    if (this.getCommand("debugsnow") != null) {
        this.getCommand("debugsnow").setExecutor(new DebugSnowCommand());
    } else {
        getLogger().severe("Command 'debugsnow' not found in plugin.yml!");
    }
    getLogger().info("Snowp plugin enabled.");
}

    @Override
    public void onDisable() {
        getLogger().info("Snowp plugin disabled.");
    }
}
