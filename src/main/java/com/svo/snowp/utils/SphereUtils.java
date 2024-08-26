import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

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
        spheres.add(new Sphere("Sphere Miner", "Miner Head", "100683", 0.0, 0.0, 1.0, "Increased mining speed"));
        spheres.add(new Sphere("Sphere Diver", "Diver Head", "100683", 0.0, 0.0, 1.0, "Increased underwater speed"));
        spheres.add(new Sphere("Sphere Builder", "Builder Head", "100683", 0.0, 0.0, 1.0, "Increased building speed"));
        spheres.add(new Sphere("Sphere Explorer", "Explorer Head", "100683", 0.0, 0.0, 1.0, "Increased exploration speed"));
        spheres.add(new Sphere("Sphere Warrior", "Warrior Head", "100683", 1.0, 0.0, 1.0, "Increased combat ability"));
        spheres.add(new Sphere("Sphere Archer", "Archer Head", "100683", 0.0, 0.0, 1.0, "Increased archery skills"));
        spheres.add(new Sphere("Sphere Mage", "Mage Head", "100683", 0.0, 0.0, 1.0, "Increased magical abilities"));
        spheres.add(new Sphere("Sphere Knight", "Knight Head", "100683", 0.0, 0.0, 1.0, "Balanced combat skills"));
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
}
