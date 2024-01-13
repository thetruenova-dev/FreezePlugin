package com.nova.freezeplugin;

import com.nova.freezeplugin.commands.FreezeCommand;
import com.nova.freezeplugin.commands.UnfreezeCommand;
import com.nova.freezeplugin.other.EventListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public final class FreezePlugin extends JavaPlugin {

    public static ArrayList<UUID> frozen;
    public static ArrayList<UUID> frozenlist;

    public FreezePlugin() {
        frozen = new ArrayList<UUID>();
        frozenlist = new ArrayList<UUID>();
        requester = new ArrayList<Player>();
    }

    @Override
    public void onEnable() {
        this.getCommand("freeze").setExecutor(new FreezeCommand());
        this.getCommand("unfreeze").setExecutor(new UnfreezeCommand());
        
        this.getServer().getPluginManager().registerEvents(new EventListener(), this);

    }

}
