import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SphereUtils {
    private static final List<Sphere> spheres = new ArrayList<>();

    static {
        initializeSpheres();
    }

    private static void initializeSpheres() {
        // Сферы
        spheres.add(new Sphere("Сфера Run Vasa", "Голова Run Vasa", "100683", 0.0, 0.0, 2.0, "Увеличение скорости и прыгучести"));
        spheres.add(new Sphere("Сфера Шахтёра", "Голова Шахтёра", "100683", 0.0, 0.0, 1.0, "Эффективность +3, скорость +1"));
        spheres.add(new Sphere("Сфера Ez", "Голова Ez", "100683", 4.0, -3.0, 10.0, "Урон +4, ХП -3, Передвижение +10, Защита +2"));
        spheres.add(new Sphere("Сфера Скоростного Копания", "Голова Скоростного Копания", "100683", 0.0, -1.0, 3.0, "Увеличение скорости копания"));

        // Пример дополнительных сфер
        spheres.add(new Sphere("Сфера Стрелка", "Голова Стрелка", "100683", 1.0, 0.0, 2.0, "Увеличение дальнего урона"));
        spheres.add(new Sphere("Сфера Защитника", "Голова Защитника", "100683", 0.0, 5.0, -1.0, "Увеличение брони, но снижение здоровья"));
        spheres.add(new Sphere("Сфера Мага", "Голова Мага", "100683", 0.0, 2.0, 3.0, "Увеличение магического урона и здоровья"));
        spheres.add(new Sphere("Сфера Рыцаря", "Голова Рыцаря", "100683", 3.0, 2.0, 1.0, "Сбалансированное увеличение урона и здоровья"));
        spheres.add(new Sphere("Сфера Воителя", "Голова Воителя", "100683", 5.0, -2.0, 0.0, "Увеличение урона и скорости"));
    }

    public static void openGiftBox(Player player, ItemStack item) {
        if (item.getType() != Material.PLAYER_HEAD) return;

        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if (meta == null || !meta.hasOwner() || !meta.getOwningPlayer().getUniqueId().toString().equals("100683")) return;

        Random random = new Random();
        Sphere sphere = spheres.get(random.nextInt(spheres.size()));
        player.getInventory().addItem(createSphereItem(sphere));

        // Remove the gift item
        player.getInventory().remove(item);
    }

    public static ItemStack createSphereItem(Sphere sphere) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(sphere.getName());
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString(sphere.getOwnerUUID())));
        item.setItemMeta(meta);
        return item;
    }

    public static void spawnGiftBoxes(World world) {
        Random random = new Random();
        int numberOfGifts = 10; // Number of gifts to spawn

        for (int i = 0; i < numberOfGifts; i++) {
            int x = random.nextInt(1000) - 500;
            int z = random.nextInt(1000) - 500;
            int y = random.nextInt(66) + 55; // Height between 55 and 120

            Location location = new Location(world, x, y, z);
            Block block = world.getBlockAt(location);

            if (block.getType() == Material.AIR && block.getRelative(0, -1, 0).getType() != Material.AIR) {
                block.setType(Material.PLAYER_HEAD);
                SkullMeta meta = (SkullMeta) block.getState().getData();
                meta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("100683")));
                block.getState().update();
            }
        }
    }

    public static void removeGiftBoxes(World world) {
        for (Location location : getAllGiftLocations(world)) {
            Block block = world.getBlockAt(location);
            if (block.getType() == Material.PLAYER_HEAD) {
                block.setType(Material.AIR);
            }
        }
    }

    private static List<Location> getAllGiftLocations(World world) {
        // Implement this method to return all locations where gifts are spawned.
        return new ArrayList<>();
    }
}
