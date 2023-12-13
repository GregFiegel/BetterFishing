package org.layanegg.betterfishing.LootTables;

import java.util.HashMap;

public class XpTable {
    public final HashMap<Integer, Double> xpTable;


    public XpTable() {
        HashMap<Integer, Double> xpTable = new HashMap<>();
        for (int x=1;  x<=100; x++){
            double y = 0.5*(Math.pow(x, 3)) + 50*(Math.pow(x, 2));
            xpTable.put(x, y);
        }
        this.xpTable = xpTable;
    }

    public HashMap<Integer, Double> getXpTable() {
        return xpTable;
    }
}
