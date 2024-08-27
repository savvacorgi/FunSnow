package com.svo.snowp.utils;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereUtils {

    private static final List<Sphere> spheres = new ArrayList<>();

    public SphereUtils() {
        // Инициализация списка сфер
        spheres.add(new Sphere("Sphere Miner", "Miner Head", 1.0, 0.0, 0.0, 0.0));
        spheres.add(new Sphere("Sphere Diver", "Diver Head", 0.0, 1.0, 0.0, 0.0));
        spheres.add(new Sphere("Sphere Builder", "Builder Head", 0.0, 0.0, 1.0, 0.0));
        spheres.add(new Sphere("Sphere Explorer", "Explorer Head", 0.0, 0.0, 1.0, 0.0));
        spheres.add(new Sphere("Sphere Warrior", "Warrior Head", 2.0, 0.0, 0.0, 1.0));
        spheres.add(new Sphere("Sphere Archer", "Archer Head", 0.0, 0.0, 1.0, 0.0));
        spheres.add(new Sphere("Sphere Mage", "Mage Head", 0.0, 0.0, 0.0, 1.0));
        spheres.add(new Sphere("Sphere Knight", "Knight Head", 0.0, 0.0, 0.0, 1.0));
        spheres.add(new Sphere("Sphere of Agility", "Agility Head", 4.0, -3.0, 10.0, 2.0));
    }

    public ItemStack createSphereItem(Sphere sphere) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(sphere.getDisplayName());
            meta.setLore(List.of("§4Attack Boost: " + sphere.getAttackBoost(),
                    "§4Health Reduction: " + sphere.getHealthReduction(),
                    "§4Movement Speed Boost: " + sphere.getMovementSpeedBoost(),
                    "§4Defense Boost: " + sphere.getDefenseBoost()));
            item.setItemMeta(meta);
        }
        return item;
    }

    public void openGiftBox(Player player, ItemStack item, Plugin plugin) {
        Sphere randomSphere = spheres.get(new Random().nextInt(spheres.size()));
        ItemStack sphereItem = createSphereItem(randomSphere);
        player.getInventory().addItem(sphereItem);
        player.sendMessage("Вы открыли подарок и получили: " + randomSphere.getDisplayName());
    }

    public void spawnGiftsInWorld(World world) {
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int x = random.nextInt(9900) + 100;
            int z = random.nextInt(9900) + 100;
            int y = world.getHighestBlockYAt(x, z);
            Block block = world.getBlockAt(x, y, z);
            block.setType(Material.PLAYER_HEAD);
            ItemStack gift = createSphereItem(spheres.get(random.nextInt(spheres.size())));
            block.getWorld().dropItemNaturally(block.getLocation(), gift);
            world.getPlayers().forEach(player -> player.sendMessage("Подарок спавнится на координатах: " + x + ", " + y + ", " + z));
        }
    }

    public Sphere getRandomSphere() {
        return spheres.get(new Random().nextInt(spheres.size()));
    }
}
