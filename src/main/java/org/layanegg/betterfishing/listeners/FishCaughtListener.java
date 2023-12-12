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
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.layanegg.betterfishing.BetterFishing;
import org.layanegg.betterfishing.LootTables.CustomFishingLootTable;
import org.layanegg.betterfishing.LootTables.FishingTable;
import org.layanegg.betterfishing.LootTables.ProbabilityInfo;

import java.util.*;

public class FishCaughtListener implements Listener {
    private final BetterFishing plugin;

    public FishCaughtListener(BetterFishing plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onFishCaught(PlayerFishEvent e){

        if(e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){

            FishingTable table = getLootTable();
            if (e.getCaught() instanceof Item item){
                ArrayList<ProbabilityInfo> oddsList = table.getOddsList();
                ArrayList<ItemStack> items = new CustomFishingLootTable(oddsList).getFishingDrop();
                ItemStack item1  = items.get(0);
                item.setItemStack(item1);
            }
            Player p = e.getPlayer();
            double toAdd = getToAdd(e);
            toAdd = Math.ceil(toAdd);

            //Add the XP to the players fishing XP and display the corresponding info
            PersistentDataContainer data = p.getPersistentDataContainer();
            NamespacedKey nsk = new NamespacedKey(plugin, "fishXP");
            Double fishXP = data.get(nsk, PersistentDataType.DOUBLE);
            assert fishXP != null;
            fishXP += toAdd;
            fishXP = Math.ceil(fishXP);
            data.set(nsk, PersistentDataType.DOUBLE, fishXP);
            p.sendMessage(ChatColor.GREEN + "+" + (int) toAdd + " Fishing XP!");
            p.sendMessage(ChatColor.GREEN + "You now have " + fishXP.intValue() + " fishing XP!");
        }
    }

    private static double getToAdd(PlayerFishEvent e) {
        //get the material of the item fished up
        Material material = null;
        if (Objects.requireNonNull(e.getCaught()).getType().equals(EntityType.DROPPED_ITEM)){
            Item item = (Item) e.getCaught();
            material = item.getItemStack().getType();
        }

        //get the number of XP to add based on the item caught
        double toAdd;
        assert material != null;
        if (material.equals(Material.COAL)){
            toAdd = 10.0;
        }else{
            toAdd = 15.0;
        }
        return toAdd;
    }

    private static FishingTable getLootTable(){
        Random rand = new Random();
        double dub = rand.nextDouble();
        if (dub < 0.1){
            return FishingTable.TREASURE;
        }
        return  FishingTable.BASIC;
    }
}
