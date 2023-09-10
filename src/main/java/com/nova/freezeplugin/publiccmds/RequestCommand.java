package com.nova.freezeplugin.publiccmds;

import com.nova.freezeplugin.FreezePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RequestCommand implements CommandExecutor {

    // save requests and eventually move to UI based.

    @Override
    public boolean onCommand(CommandSender sender, Command command, java.lang.String label, java.lang.String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // /request <request>

            if (!(args.length < 1)) {

                StringBuilder builder = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    builder.append(args[i]).append(" ");
                }

                Bukkit.getServer().broadcast(player.getName() + "has made a request: " + builder, "staff.requests");

            } else {
                player.sendMessage(ChatColor.RED + "Please state your issue.");
            }

        } else {
            return false;
        }

        return false;
    }
}
