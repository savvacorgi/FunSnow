package com.svo.snowp.commands;

import com.svo.snowp.Snowp;
import com.svo.snowp.utils.SphereUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugSnowCommand implements CommandExecutor {

    public DebugSnowCommand(Snowp snowp) {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Проверяем, что команду выполняет игрок
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Проверяем, является ли игрок администратором (опером)
            if (player.isOp() || player.hasPermission("op")) {
                // Открываем GUI для админа
                SphereUtils.openDebugSnowGUI(player);
                return true;
            } else {
                // Если игрок не админ, отправляем сообщение
                player.sendMessage(ChatColor.RED + "Чтобы ты маленький плаки плаки?");
                return true;
            }
        } else {
            sender.sendMessage("Эту команду может использовать только игрок.");
            return true;
        }
    }
}
