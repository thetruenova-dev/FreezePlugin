package com.nova.freezeplugin.commands;

import com.nova.freezeplugin.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class FreezeCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("freeze.use")) {
                if (args.length == 1) {
                    final String playerName = args[0];
                    final Player target = Bukkit.getServer().getPlayerExact(playerName);
                    if (target != null) {
                        if (FreezePlugin.frozen.contains(target.getUniqueId())) {
                            player.sendMessage(ChatColor.RED + target.getDisplayName() + " is already frozen.");
                        } else {
                            FreezePlugin.frozen.add(target.getUniqueId());
                            FreezePlugin.frozenlist.add(player.getUniqueId());
                            FreezePlugin.frozenlist.add(target.getUniqueId());
                            player.sendMessage(ChatColor.GREEN + "You have frozen " + target.getName());
                            target.sendMessage(ChatColor.RED + "You have been frozen. Do not logout.");


                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Player not found.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid arguments! Usage: /freeze <player>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Insufficient permissions.");
            }
        }
        return false;

    }

}
