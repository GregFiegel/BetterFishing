package org.layanegg.betterfishing.LootTables;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Random;

public class CustomFishingLootTable{

    private final ArrayList<ProbabilityInfo> oddsList;

    public CustomFishingLootTable(ArrayList<ProbabilityInfo> oddsList) {
        this.oddsList = oddsList;
    }

    public ArrayList<ItemStack> getFishingDrop(){
        Random rand = new Random();
        double num = rand.nextDouble();
        double oddsSoFar = 0.0;
        System.out.println(oddsList.toString());
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
}
