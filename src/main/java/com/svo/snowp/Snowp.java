package com.svo.snowp;

import com.svo.snowp.listeners.BlockPlaceListener;
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
        // Регистрация слушателей событий
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(this), this);

        // Создание рецепта
        createCustomSnowBlockRecipe();

        // Инициализация и использование TelegramNotifier
        this.saveDefaultConfig(); // Убедитесь, что конфиг файл создан
        telegramNotifier = new TelegramNotifier(this);
        telegramNotifier.sendNotification("Server is now online!");

        getLogger().info("Snowp plugin enabled.");
    }

    @Override
    public void onDisable() {
        if (telegramNotifier != null) {
            telegramNotifier.sendNotification("Server is shutting down.");
        }
        getLogger().info("Snowp plugin disabled.");
    }

    private void createCustomSnowBlockRecipe() {
        // Создаем кастомный предмет
        ItemStack customSnowBlock = SphereUtils.createCustomSnowBlock();

        // Создаем рецепт крафта
        NamespacedKey key = new NamespacedKey(this, "custom_snow_block");
        ShapedRecipe recipe = new ShapedRecipe(key, customSnowBlock);
        recipe.shape("SSS", "SCS", "SSS");

        recipe.setIngredient('S', Material.SNOWBALL);
        recipe.setIngredient('C', Material.DIAMOND);

        // Регистрируем рецепт
        Bukkit.addRecipe(recipe);
    }
}
