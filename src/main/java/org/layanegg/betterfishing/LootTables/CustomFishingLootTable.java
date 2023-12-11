package org.layanegg.betterfishing.LootTables;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CustomFishingLootTable{

    public ArrayList<ItemStack> getFishingDrop(HashMap<Material, Double> oddsMap){
        Random rand = new Random();
        double num = rand.nextDouble();
        double oddsSoFar = 0.0;
        for(Material material : oddsMap.keySet()){
            double curOdds = oddsMap.get(material);
            if (curOdds + oddsSoFar > num){
                ArrayList<ItemStack> toReturn = new ArrayList<>();
                toReturn.add(new ItemStack(material));
                return toReturn;
            }
            oddsSoFar += curOdds;
        }
        return null;
    }
}
