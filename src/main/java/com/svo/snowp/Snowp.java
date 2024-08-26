package com.svo.snowp;

import com.svo.snowp.listeners.BlockPlaceListener;
import com.svo.snowp.listeners.SphereListener;
import com.svo.snowp.utils.SphereUtils;
import com.svo.snowp.utils.TelegramNotifier;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Snowp extends JavaPlugin {

    private TelegramNotifier telegramNotifier;

    @Override
    public void onEnable() {
        saveDefaultConfig(); // Создание файла конфигурации с дефолтными значениями

        telegramNotifier = new TelegramNotifier(this);

        // Регистрация слушателей событий
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SphereListener(this), this);

        // Создание рецепта
        createHappyNewYearRecipe();

        getLogger().info("Snowp plugin enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Snowp plugin disabled.");
    }

    private void createHappyNewYearRecipe() {
        ItemStack happyNewYearItem = SphereUtils.createHappyNewYearItem();

        NamespacedKey key = new NamespacedKey(this, "happy_new_year");
        ShapedRecipe recipe = new ShapedRecipe(key, happyNewYearItem);
        recipe.shape("DSD", "SCS", "DSD");

        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('S', Material.SNOWBALL);
        recipe.setIngredient('C', Material.NETHERITE_INGOT);

        Bukkit.addRecipe(recipe);
    }

    public void notifyEventStarted(String eventName, String description) {
        String message = String.format("🎉 Event Started: %s\nDescription: %s", eventName, description);
        telegramNotifier.sendMessage(message);
    }
}
