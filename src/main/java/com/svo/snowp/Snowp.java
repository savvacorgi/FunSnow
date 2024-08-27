package com.svo.snowp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import com.svo.snowp.listeners.SphereListener;
import com.svo.snowp.utils.SphereUtils;
import com.svo.snowp.items.WaterTNT;

public class Snowp extends JavaPlugin {

    private SphereUtils sphereUtils;
    private EventManager eventManager;

    @Override
    public void onEnable() {
        sphereUtils = new SphereUtils();
        eventManager = new EventManager(this);

        Bukkit.getPluginManager().registerEvents(new SphereListener(eventManager, sphereUtils), this);

        // Регистрация Water TNT
        WaterTNT waterTNT = new WaterTNT(this);
        Bukkit.getPluginManager().registerEvents(waterTNT, this);

        // Крафт для Water TNT
        ItemStack waterTNTItem = waterTNT.createWaterTNT();
        ShapedRecipe recipe = new ShapedRecipe(waterTNTItem);
        recipe.shape(" S ", "STT", " S ");
        recipe.setIngredient('S', Material.SAND);
        recipe.setIngredient('T', Material.TNT);
        Bukkit.addRecipe(recipe);

        // Автоматический запуск ивента при старте сервера
        eventManager.startEvent();
    }

    @Override
    public void onDisable() {
        // Логика при отключении плагина
    }
}
