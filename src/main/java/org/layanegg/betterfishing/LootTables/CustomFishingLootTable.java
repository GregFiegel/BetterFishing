package org.layanegg.betterfishing.LootTables;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.Random;

public class CustomFishingLootTable{

    private final ArrayList<ProbabilityInfo> oddsList;
    private final Plugin plugin;

    public CustomFishingLootTable(ArrayList<ProbabilityInfo> oddsList, Plugin plugin) {
        this.oddsList = oddsList;
        this.plugin = plugin;
    }

    public ArrayList<ItemStack> getFishingDrop(){
        Random rand = new Random();
        double num = rand.nextDouble();
        double oddsSoFar = 0.0;
        //System.out.println(oddsList.toString());
        for(ProbabilityInfo probabilityInfo : oddsList){
            double curOdds = probabilityInfo.getOdds();
            if (curOdds + oddsSoFar > num){
                ArrayList<ItemStack> toReturn = new ArrayList<>();
                int max = probabilityInfo.getMax();
                int min = probabilityInfo.getMin();
                Material material = probabilityInfo.getMaterial();
                toReturn.add(new ItemStack(material, (rand.nextInt(max+1 - min)) + min));
                return toReturn;
            }
            oddsSoFar += curOdds;
        }
        return null;
    }

    public void removeLockedEntries(Player p){

        PersistentDataContainer data = p.getPersistentDataContainer();
        int lvl = 0;
        if (data.has(new NamespacedKey(plugin, "fishLVL"), PersistentDataType.INTEGER)){
            Integer level = data.get(new NamespacedKey(plugin, "fishLVL"), PersistentDataType.INTEGER);
            assert level != null;
            lvl = level;
        }
        ArrayList<ProbabilityInfo> toRemove = new ArrayList<>();
        for (ProbabilityInfo singleEntry : oddsList){
            if (singleEntry.getLevelReq() > lvl){
                toRemove.add(singleEntry);
            }
        }
        for (ProbabilityInfo itemToRemove : toRemove){
            oddsList.remove(itemToRemove);
        }
    }

    public void normalizeOdds(){
        double totalOdds = 0.0;
        for (ProbabilityInfo item : oddsList){
            totalOdds += item.getOdds();
        }

        if(totalOdds == 1.0){
            return;
        }

        for (ProbabilityInfo item : oddsList){
            item.setOdds( item.getOdds() / totalOdds );
        }
    }
}
