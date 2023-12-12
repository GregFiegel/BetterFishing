package org.layanegg.betterfishing.LootTables;


import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum FishingTable{
    BASIC(new ArrayList<>(List.of(
            new ProbabilityInfo(Material.COD, 0, 0.2, 1, 1),
            new ProbabilityInfo(Material.DIRT, 0, 0.4, 4, 10),
            new ProbabilityInfo(Material.SAND, 0, 0.4, 3, 6)
    ))),

    TREASURE(new ArrayList<>()),

    SEA_CREATURES(new ArrayList<>(List.of(
            new ProbabilityInfo(Material.DIAMOND, 0, 0.5, 1, 3),
            new ProbabilityInfo(Material.NETHERITE_SCRAP, 0, 0.5, 1, 1)
    )));

    private final ArrayList<ProbabilityInfo> oddsList;

    FishingTable (ArrayList<ProbabilityInfo> oddsList){
        this.oddsList = oddsList;
    }


    public ArrayList<ProbabilityInfo> getOddsList() {
        return oddsList;
    }
}
