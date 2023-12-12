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
        PersistentDataContainer  playerData = p.getPersistentDataContainer();
        if(!playerData.has(new NamespacedKey(plugin, "fishXP"), PersistentDataType.DOUBLE)){
            playerData.set(new NamespacedKey(plugin, "fishXP"), PersistentDataType.DOUBLE, 0.0);
        }
        if(!playerData.has(new NamespacedKey(plugin, "fishLVL"), PersistentDataType.INTEGER)){
            playerData.set(new NamespacedKey(plugin, "fishLVL"), PersistentDataType.INTEGER, 1);
        }
        if(!playerData.has(new NamespacedKey(plugin, "fishSPEED"), PersistentDataType.DOUBLE)){
            playerData.set(new NamespacedKey(plugin, "fishSPEED"), PersistentDataType.DOUBLE, 100.0);
        }
    }
}
