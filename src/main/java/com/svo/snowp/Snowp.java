package com.svo.snowp;

import com.svo.snowp.listeners.BlockPlaceListener;
import com.svo.snowp.listeners.SphereListener;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Snowp extends JavaPlugin {

    @Override
    public void onEnable() {
        // Регистрация слушателей событий
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SphereListener(this), this);

        // Создание рецепта
        createSnowBlockRecipe();

        getLogger().info("Snowp plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Snowp plugin disabled.");
    }

    private void createSnowBlockRecipe() {
        // Создаем предмет "Happy New Year Snow Block"
        ItemStack happyNewYearBlock = SphereUtils.createHappyNewYearBlock();

        // Создаем рецепт крафта
        NamespacedKey key = new NamespacedKey(this, "happy_new_year_snow_block");
        ShapedRecipe recipe = new ShapedRecipe(key, happyNewYearBlock);
        recipe.shape("DND", "NSN", "DND");

        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', Material.SNOW_BLOCK);

        // Регистрируем рецепт
        Bukkit.addRecipe(recipe);
    }
}
