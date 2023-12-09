package org.layanegg.betterfishing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.layanegg.betterfishing.listeners.XPBottleBreakListener;

public final class BetterFishing extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new XPBottleBreakListener(), this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.setJoinMessage("What's up! did this work?");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
