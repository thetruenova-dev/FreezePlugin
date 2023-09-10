package com.nova.freezeplugin.other;

import com.nova.freezeplugin.FreezePlugin;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (FreezePlugin.frozen.contains(player.getUniqueId())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (FreezePlugin.frozen.contains(player.getUniqueId())) {
            Bukkit.getServer().broadcast(player.getName() + " has logged out whilst frozen and was banned.", "freeze.use");
            Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "Logged out whilst frozen", null, "CONSOLE");
            FreezePlugin.frozen.remove(player.getUniqueId());
        }
    }

    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player)e.getEntity();
            if (FreezePlugin.frozen.contains(player.getUniqueId())) {
                e.setCancelled(true);
                e.getDamager().sendMessage(ChatColor.RED + "You cannot attack " + player.getName() + ChatColor.RED + " because they are frozen.");
            }
        }
        if (e.getDamager() instanceof Player) {
            Player attacker = (Player)e.getDamager();
            if (FreezePlugin.frozen.contains(attacker.getUniqueId())) {
                e.setCancelled(true);
                attacker.sendMessage(ChatColor.RED + "You cannot attack others whilst frozen.");
            }

        }
    }

}
