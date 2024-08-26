package com.svo.snowp;

import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Snowp extends JavaPlugin {
    private static Snowp instance;
    private EventManager eventManager;
    private SphereUtils sphereUtils;

    @Override
    public void onEnable() {
        instance = this;
        eventManager = new EventManager();
        sphereUtils = new SphereUtils();

        Bukkit.getPluginManager().registerEvents(new SphereListener(eventManager, sphereUtils), this);

        NamespacedKey key = new NamespacedKey(this, "custom_snow_block");
        ItemStack customSnowBlock = sphereUtils.createSphereItem(new Sphere("Custom Snow Block", "Custom Snow Block Head", "100683", 0, 0, 0, ""));
        ShapedRecipe recipe = new ShapedRecipe(key, customSnowBlock);
        recipe.shape("XXX", "XYX", "XXX");
        recipe.setIngredient('X', Material.SNOW_BLOCK);
        recipe.setIngredient('Y', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }

    @Override
    public void onDisable() {
        // Очистка ресурсов, если необходимо
    }

    public static Snowp getInstance() {
        return instance;
    }
}
