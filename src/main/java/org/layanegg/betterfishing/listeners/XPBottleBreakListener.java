package org.layanegg.betterfishing.listeners;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;

public class XPBottleBreakListener implements Listener {

    @EventHandler
    public void onXPBottleBreak(ExpBottleEvent e){
        Block block = e.getHitBlock();
        BlockFace blockFace = e.getHitBlockFace();
        assert block != null;
        assert blockFace != null;
        try {
            block.applyBoneMeal(blockFace);

        }catch (NullPointerException exception){
            System.out.println("NULL POINTER EXCEPTION");
        }
    }
}
