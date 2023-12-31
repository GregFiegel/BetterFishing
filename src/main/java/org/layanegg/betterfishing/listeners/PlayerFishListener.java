package org.layanegg.betterfishing.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
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
import org.layanegg.betterfishing.xpInfo.XpTable;
import org.layanegg.betterfishing.xpInfo.ItemXps;

import java.util.*;

public class PlayerFishListener implements Listener {
    private final BetterFishing plugin;

    public PlayerFishListener(BetterFishing plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onFishCaught(PlayerFishEvent e){

        if(e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){

            //get a loot table (ADD A PLAYER OBJECT MAYBE SO CHECKS CAN BE MADE)
            FishingTable table = getLootTable();

            //Get the drops from the custom loot table
            Item item = (Item) e.getCaught();
            ArrayList<ProbabilityInfo> oddsList = table.getOddsList();
            CustomFishingLootTable customFishingLootTable = new CustomFishingLootTable(oddsList, plugin);
            customFishingLootTable.removeLockedEntries(e.getPlayer());
            customFishingLootTable.normalizeOdds();
            ArrayList<ItemStack> items = customFishingLootTable.getFishingDrop();
            ItemStack item1  = items.get(0);
            assert item != null;
            item.setItemStack(item1);
            //Add the XP to the players fishing XP and display the corresponding info
            Player p = e.getPlayer();
            calculateXp(p, item);
        }

        if (e.getState().equals(PlayerFishEvent.State.FISHING) && !(e.getHook().getPersistentDataContainer().has(new NamespacedKey(plugin, "hookInfoUpdated"), PersistentDataType.BOOLEAN))){
            FishHook hook = e.getHook();
            hook.getPersistentDataContainer().set(new NamespacedKey(plugin, "hookInfoUpdated"), PersistentDataType.BOOLEAN, true);

            hook.setApplyLure(false);

            PersistentDataContainer playerData = e.getPlayer().getPersistentDataContainer();
            //DEBUG-LINE playerData.set(new NamespacedKey(plugin, "fishSPEED"), PersistentDataType.DOUBLE, 1000.0);

            if (playerData.has(new NamespacedKey(plugin, "fishSPEED"), PersistentDataType.DOUBLE)){
                Double fishSpeed = playerData.get(new NamespacedKey(plugin, "fishSPEED"), PersistentDataType.DOUBLE);
                assert fishSpeed != null;
                int minWait = (int) Math.max(Math.floor(100 / (fishSpeed  / 100)), 0) ;
                int maxWait = (int) Math.max(Math.floor(600 / (fishSpeed  / 100)), 1) ;
                hook.setWaitTime(minWait, maxWait);
                int minLure = (int) Math.max(Math.floor(20 / (fishSpeed / 100)), 15);
                int maxLure = (int) Math.max(Math.floor(80 / (fishSpeed / 100)), 20);
                hook.setLureTime(minLure, maxLure);
            }else{
                playerData.set(new NamespacedKey(plugin, "fishSPEED"), PersistentDataType.DOUBLE, 100.0);
            }
        }
    }

    private void calculateXp(Player player, Entity entity) {
        double toAdd = getToAdd(entity);
        toAdd = Math.ceil(toAdd);
        PersistentDataContainer playerData = player.getPersistentDataContainer();
        NamespacedKey fishXpNSK = new NamespacedKey(plugin, "fishXP");
        NamespacedKey fishLvlNSK = new NamespacedKey(plugin, "fishLVL");
        Double fishXP = playerData.get(fishXpNSK, PersistentDataType.DOUBLE);
        assert fishXP != null;
        Integer fishLVL = playerData.get(fishLvlNSK, PersistentDataType.INTEGER);
        assert fishLVL != null;
        fishXP += toAdd;
        fishXP = Math.ceil(fishXP);
        Integer Lvl = didLevelUp(fishXP, fishLVL);
        int fishXPint = fishXP.intValue();
        if (Lvl != -1){
            player.sendMessage(ChatColor.GREEN + "+" + (int) toAdd + " Fishing XP!");
            player.sendMessage(ChatColor.GREEN + "You now have " + fishXPint + " fishing XP!");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You leveled up!");
            player.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "You are now fishing level " + Lvl + "!");
            playerData.set(fishLvlNSK, PersistentDataType.INTEGER, Lvl);
            playerData.set(fishXpNSK, PersistentDataType.DOUBLE, fishXP);
        }else{
            playerData.set(fishXpNSK, PersistentDataType.DOUBLE, fishXP);
            player.sendMessage(ChatColor.GREEN + "+" + (int) toAdd + " Fishing XP!");
            player.sendMessage(ChatColor.GREEN + "You now have " + fishXPint + " fishing XP!");
        }
    }

    private Integer didLevelUp(Double fishXP, Integer fishLVL) {
        HashMap<Integer, Double> xpTable = new XpTable().getXpTable();
        int lvl = fishLVL;
        while (true){
            if (fishXP > xpTable.get(lvl)){
                lvl++;
            }else{
                break;
            }
        }
        if(fishLVL == lvl){
            return -1;
        }
        return lvl;
    }

    private static double getToAdd(Entity entity) {
        //get the material of the item fished up
        Material material = null;
        if (Objects.requireNonNull(entity).getType().equals(EntityType.DROPPED_ITEM)){
            Item item = (Item) entity;
            material = item.getItemStack().getType();
        }

        //get the number of XP to add based on the item caught
        assert material != null;
        HashMap<Material, Double> itemXps = new ItemXps().getItemXps();
        return itemXps.getOrDefault(material, 10.0);
    }

    private static FishingTable getLootTable(){
        Random rand = new Random();
        double dub = rand.nextDouble();
        if (dub < 0.25){
            return FishingTable.TREASURE;
        }
        return  FishingTable.BASIC;
    }
}
