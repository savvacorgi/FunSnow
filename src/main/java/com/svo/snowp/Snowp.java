import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.NamespacedKey;

public class Snowp extends JavaPlugin {
    private EventManager eventManager;
    private SphereUtils sphereUtils;

    @Override
    public void onEnable() {
        eventManager = new EventManager();
        sphereUtils = new SphereUtils();

        // Регистрация слушателя
        Bukkit.getPluginManager().registerEvents(new SphereListener(eventManager, sphereUtils), this);

        // Создание и регистрация рецептов, если необходимо
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
        // Очистка кода, если необходимо
    }
}
