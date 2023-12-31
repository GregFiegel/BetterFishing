package org.layanegg.betterfishing;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.layanegg.betterfishing.commands.FishXpCommand;
import org.layanegg.betterfishing.commands.FeedCommand;
import org.layanegg.betterfishing.commands.FishXpTabCompleter;
import org.layanegg.betterfishing.commands.RodCommand;
import org.layanegg.betterfishing.listeners.PlayerFishListener;
import org.layanegg.betterfishing.listeners.PlayerJoinListener;

import java.util.Objects;


public final class BetterFishing extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerFishListener(this), this);
        Objects.requireNonNull(getCommand("rod")).setExecutor(new RodCommand());
        Objects.requireNonNull(getCommand("feed")).setExecutor(new FeedCommand());
        Objects.requireNonNull(getCommand("fishXp")).setExecutor(new FishXpCommand(this));
        Objects.requireNonNull(getCommand("fishXp")).setTabCompleter(new FishXpTabCompleter());
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
