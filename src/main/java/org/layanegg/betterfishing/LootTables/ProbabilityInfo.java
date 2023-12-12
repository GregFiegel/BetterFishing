package org.layanegg.betterfishing.LootTables;

import org.bukkit.Material;

public class ProbabilityInfo {
    private final Material material;
    private final int levelReq;
    private final double odds;
    private final int min;
    private final int max;

    public ProbabilityInfo(Material material, int levelReq, double odds, int min, int max) {
        this.material = material;
        this.levelReq = levelReq;
        this.odds = odds;
        this.min = min;
        this.max = max;
    }

    public Material getMaterial() {
        return material;

    }

    public double getOdds() {
        return odds;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    public int getLevelReq() {
        return levelReq;
    }
}
