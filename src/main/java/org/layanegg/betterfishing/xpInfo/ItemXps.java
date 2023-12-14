package org.layanegg.betterfishing.xpInfo;

import org.bukkit.Material;

import java.util.HashMap;

public class ItemXps {
    private final HashMap<Material, Double> itemXps;

    public ItemXps() {
        HashMap<Material, Double> table = new HashMap<>();
        table.put(Material.COD, 10.0);

        this.itemXps = table;
    }

    public HashMap<Material, Double> getItemXps() {
        return itemXps;
    }
}
