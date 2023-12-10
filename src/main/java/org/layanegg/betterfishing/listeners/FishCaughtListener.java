package org.layanegg.betterfishing.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.layanegg.betterfishing.BetterFishing;
import java.util.Objects;

public class FishCaughtListener implements Listener {
    private final BetterFishing plugin;

    public FishCaughtListener(BetterFishing plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onFishCaught(PlayerFishEvent e){

        if(e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){
            Player p = e.getPlayer();

            //get the material of the item fished up
            Material material = null;
            if (Objects.requireNonNull(e.getCaught()).getType().equals(EntityType.DROPPED_ITEM)){
                Item item = (Item) e.getCaught();
                material = item.getItemStack().getType();
            }

            //get the number of XP to add based on the item caught
            int toAdd;
            assert material != null;
            if (material.equals(Material.COD)){
                toAdd = 10;
            }else{
                toAdd = 15;
            }

            //Add the XP to the players fishing XP and display the corresponding info
            PersistentDataContainer data = p.getPersistentDataContainer();
            NamespacedKey nsk = new NamespacedKey(plugin, "fishXP");
            if(data.has(nsk, PersistentDataType.INTEGER)){
                Integer fishXP = data.get(nsk, PersistentDataType.INTEGER);
                assert fishXP != null;
                fishXP += toAdd;
                data.set(nsk, PersistentDataType.INTEGER, fishXP);
                p.sendMessage(ChatColor.GREEN + "+" + toAdd + " Fishing XP!");
                p.sendMessage(ChatColor.GREEN + "You now have " + fishXP + " fishing XP!");
            }
            else{
                data.set(nsk, PersistentDataType.INTEGER, toAdd);
                p.sendMessage(ChatColor.GREEN + "+" + toAdd + " Fishing XP!");
                p.sendMessage(ChatColor.GREEN + "You now have "+ toAdd +" fishing XP!");
            }
        }
    }
}
