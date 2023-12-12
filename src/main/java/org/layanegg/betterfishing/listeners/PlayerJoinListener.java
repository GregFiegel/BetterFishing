package org.layanegg.betterfishing.listeners;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {

    private final Plugin plugin;

    public PlayerJoinListener(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        PersistentDataContainer  dataContainer = p.getPersistentDataContainer();
        if(!hasJoinedBefore(p)){
            dataContainer.set(new NamespacedKey(plugin, "fishXP"), PersistentDataType.DOUBLE, 0.0);
            dataContainer.set(new NamespacedKey(plugin, "fishLVL"), PersistentDataType.INTEGER, 1);
        }
    }

    private boolean hasJoinedBefore(Player p){
        return p.getPersistentDataContainer().has(new NamespacedKey(plugin, "fishXP"), PersistentDataType.INTEGER);
    }
}
