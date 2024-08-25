package com.svo.snowp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugSnowCommand implements CommandExecutor {

    private final Snowp plugin;

    public DebugSnowCommand(Snowp plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Debug Snow GUI открыт!");
            // Логика открытия GUI или другая логика команды
        } else {
            sender.sendMessage("Эту команду может использовать только игрок!");
        }
        return true;
    }
}
