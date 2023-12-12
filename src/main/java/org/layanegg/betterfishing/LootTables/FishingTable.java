package org.layanegg.betterfishing.LootTables;


import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public enum FishingTable{
    BASIC(new ArrayList<>(List.of(
            new ProbabilityInfo(Material.COD, 0, 0.1, 1, 1),
            new ProbabilityInfo(Material.DIRT, 0, 1, 4, 10),
            new ProbabilityInfo(Material.SAND, 0, 1, 3, 6),
            new ProbabilityInfo(Material.CACTUS, 10, 100, 1, 1)
    ))),

    TREASURE(new ArrayList<>(List.of(
            new ProbabilityInfo(Material.DIAMOND, 0, 0.5, 1, 3),
            new ProbabilityInfo(Material.NETHERITE_SCRAP, 0, 0.5, 1, 1)
    ))),

    SEA_CREATURES(new ArrayList<>());

    private final ArrayList<ProbabilityInfo> oddsList;

    FishingTable (ArrayList<ProbabilityInfo> oddsList){
        this.oddsList = oddsList;
    }


    public ArrayList<ProbabilityInfo> getOddsList() {
        return oddsList;
    }
}
