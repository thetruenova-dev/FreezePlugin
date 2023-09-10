package com.nova.freezeplugin.commands;

import com.nova.freezeplugin.FreezePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnfreezeCommand implements CommandExecutor {

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
                            player.sendMessage(ChatColor.GREEN + target.getName() + " has been unfrozen.");
                            target.sendMessage(ChatColor.GREEN + "You have been unfrozen.");
                            FreezePlugin.frozen.remove(target.getUniqueId());
                            FreezePlugin.frozenlist.remove(target.getUniqueId());
                            FreezePlugin.frozenlist.remove(player.getUniqueId());
                        } else {
                            player.sendMessage(ChatColor.RED + target.getName() + " is not frozen.");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Player not found.");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid arguments. Use /unfreeze <player>");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Insufficient permissions.");
            }
        }

        return false;
    }
}
