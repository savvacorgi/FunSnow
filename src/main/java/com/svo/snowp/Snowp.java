package com.svo.snowp;

import com.svo.snowp.listeners.BlockPlaceListener;
import com.svo.snowp.listeners.SphereListener;
import com.svo.snowp.utils.SphereUtils;
import com.svo.snowp.utils.TelegramNotifier;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Snowp extends JavaPlugin {

    private TelegramNotifier telegramNotifier;
    private SphereUtils sphereUtils;

    @Override
    public void onEnable() {
        // Регистрация слушателей событий
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SphereListener(this), this);

        // Инициализация утилит
        sphereUtils = new SphereUtils(this);

        // Создание рецепта
        createCustomSnowBlockRecipe();

        // Инициализация и использование TelegramNotifier
        this.saveDefaultConfig(); // Убедитесь, что конфиг файл создан
        telegramNotifier = new TelegramNotifier(this);
        telegramNotifier.sendNotification("Server is now online!");

        // Запуск ивента
        startEvent();

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
        ItemStack customSnowBlock = SphereUtils.createCustomSnowBlock();
        NamespacedKey key = new NamespacedKey(this, "custom_snow_block");
        ShapedRecipe recipe = new ShapedRecipe(key, customSnowBlock);
        recipe.shape("SSS", "SCS", "SSS");
        recipe.setIngredient('S', Material.SNOWBALL);
        recipe.setIngredient('C', Material.DIAMOND);
        Bukkit.addRecipe(recipe);
    }

    private void startEvent() {
        // Запускаем ивент сразу после включения плагина
        sphereUtils.spawnGiftBoxes(Bukkit.getWorlds().get(0)); // Замените на нужный мир

        // Запускаем повторяющийся ивент каждые 15 минут
        new BukkitRunnable() {
            @Override
            public void run() {
                sphereUtils.spawnGiftBoxes(Bukkit.getWorlds().get(0)); // Замените на нужный мир
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        sphereUtils.removeGiftBoxes(Bukkit.getWorlds().get(0)); // Замените на нужный мир
                    }
                }.runTaskLater(Snowp.this, 3600L); // 3 минуты позже
            }
        }.runTaskTimer(this, 0L, 18000L); // Каждые 15 минут (20 тик = 1 секунда)
    }
}
