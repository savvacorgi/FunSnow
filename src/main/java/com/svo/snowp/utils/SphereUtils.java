package com.svo.snowp.utils;

import com.svo.snowp.Snowp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Random;

public class SphereUtils {

    private static final Random random = new Random();

    public static void spawnCase(Location location) {
        World world = location.getWorld();
        if (world != null && random.nextInt(100) < 1) { // 1% chance
            ItemStack caseItem = createCaseItem();
            world.dropItemNaturally(location, caseItem);
            Snowp plugin = JavaPlugin.getPlugin(Snowp.class);
            plugin.registerCase(caseItem.getItemMeta().getDisplayName());
        }
    }

    public static void giveRandomSphere(Player player) {
        String[] spheres = {"EZ Sphere", "Turtle Sphere", "Miner Sphere", "Tank Sphere", "Dumb Sphere", "Tap Tap Monster Sphere"};
        String selectedSphere = spheres[random.nextInt(spheres.length)];

        ItemStack sphere = createSphereItem(selectedSphere);
        player.getInventory().addItem(sphere);
        player.sendMessage(ChatColor.GREEN + "You have received: " + selectedSphere);
    }

    public static void removeAllCases() {
        for (World world : Bukkit.getWorlds()) {
            world.getEntities().stream()
                .filter(entity -> entity instanceof Item)
                .filter(entity -> ((Item) entity).getItemStack().getType() == Material.PLAYER_HEAD)
                .forEach(entity -> entity.remove());
        }
    }

    private static ItemStack createCaseItem() {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName("Case-" + random.nextInt(10000));
        meta.setOwner("savvacorgi");
        item.setItemMeta(meta);
        return item;
    }

    private static ItemStack createSphereItem(String name) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + name);
        meta.setOwner("savvacorgi");
        item.setItemMeta(meta);
        return item;
    }
}
