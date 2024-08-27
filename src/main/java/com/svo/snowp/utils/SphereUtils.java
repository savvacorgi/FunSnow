package com.svo.snowp.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SphereUtils {

    private static final List<Sphere> spheres = new ArrayList<>();

    public SphereUtils() {
        // Инициализация списка сфер
        spheres.add(new Sphere("Sphere Miner", "Miner Head", "100683", 0.0, 0.0, 1.0, "Increased mining speed"));
        spheres.add(new Sphere("Sphere Diver", "Diver Head", "100683", 0.0, 0.0, 1.0, "Increased underwater speed"));
        spheres.add(new Sphere("Sphere Builder", "Builder Head", "100683", 0.0, 0.0, 1.0, "Increased building speed"));
        spheres.add(new Sphere("Sphere Explorer", "Explorer Head", "100683", 0.0, 0.0, 1.0, "Increased exploration speed"));
        spheres.add(new Sphere("Sphere Warrior", "Warrior Head", "100683", 1.0, 0.0, 1.0, "Increased combat ability"));
        spheres.add(new Sphere("Sphere Archer", "Archer Head", "100683", 0.0, 0.0, 1.0, "Increased archery skills"));
        spheres.add(new Sphere("Sphere Mage", "Mage Head", "100683", 0.0, 0.0, 1.0, "Increased magical abilities"));
        spheres.add(new Sphere("Sphere Knight", "Knight Head", "100683", 0.0, 0.0, 1.0, "Balanced combat skills"));
        
        // Новые сферы с указанными эффектами
        spheres.add(new Sphere("Sphere of Agility", "Agility Head", "100684", 4.0, -3.0, 10.0, 2.0, "Increase damage by 4, decrease HP by 3, increase movement speed by 10, increase defense by 2"));
        spheres.add(new Sphere("Sphere of Strength", "Strength Head", "100685", 6.0, -2.0, 0.0, 1.0, "Increase damage by 6, decrease HP by 2, increase defense by 1"));
        spheres.add(new Sphere("Sphere of Swiftness", "Swiftness Head", "100686", 0.0, 0.0, 20.0, 0.0, "Increase movement speed by 20"));
        spheres.add(new Sphere("Sphere of Protection", "Protection Head", "100687", 0.0, 0.0, 0.0, 5.0, "Increase defense by 5"));
        spheres.add(new Sphere("Sphere of Vitality", "Vitality Head", "100688", 0.0, -5.0, 0.0, 3.0, "Decrease HP by 5, increase defense by 3"));
        spheres.add(new Sphere("Sphere of Precision", "Precision Head", "100689", 3.0, -1.0, 5.0, 1.0, "Increase damage by 3, decrease HP by 1, increase movement speed by 5, increase defense by 1"));
        spheres.add(new Sphere("Sphere of Fortitude", "Fortitude Head", "100690", 2.0, -4.0, 0.0, 4.0, "Increase damage by 2, decrease HP by 4, increase defense by 4"));
        spheres.add(new Sphere("Sphere of Agility Plus", "Agility Plus Head", "100691", 5.0, -2.0, 15.0, 3.0, "Increase damage by 5, decrease HP by 2, increase movement speed by 15, increase defense by 3"));
    }

    // Метод для создания предмета сферы
    public ItemStack createSphereItem(Sphere sphere) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(sphere.getName());
        meta.setLore(List.of(sphere.getDescription()));
        item.setItemMeta(meta);
        return item;
    }

    // Открытие подарка
    public void openGiftBox(Player player, ItemStack item, Plugin plugin) {
        // Логика открытия подарка
        Sphere randomSphere = spheres.get(new Random().nextInt(spheres.size()));
        ItemStack sphereItem = createSphereItem(randomSphere);
        player.getInventory().addItem(sphereItem);
        player.sendMessage("Вы открыли подарок и получили: " + randomSphere.getName());
    }

    // Спавн подарков в чанках
    public void spawnGiftsInChunks(World world) {
        Random random = new Random();

        for (int x = 0; x < world.getMaxHeight(); x += 16) {
            for (int z = 0; z < world.getMaxHeight(); z += 16) {
                if (random.nextInt(100) < 5) { // 5% вероятность
                    int y = random.nextInt(181) + 60; // Высота от 60 до 240

                    Block block = world.getHighestBlockAt(x, z).getRelative(0, y - world.getHighestBlockYAt(x, z), 0);

                    if (block.getType() == Material.AIR) {
                        block = world.getBlockAt(x, y, z);
                        block.setType(Material.PLAYER_HEAD);
                        Bukkit.getLogger().info("Подарок заспавнен в чанкe (" + x + ", " + z + ") на высоте " + y);
                    }
                }
            }
        }
    }
                                                                            }
