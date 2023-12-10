package org.layanegg.betterfishing.listeners;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.projectiles.ProjectileSource;

public class XPBottleBreakListener implements Listener {

    @EventHandler
    public void onXPBottleBreak(ExpBottleEvent e){
        Block block = e.getHitBlock();
        BlockFace blockFace = e.getHitBlockFace();
        assert block != null;
        assert blockFace != null;
        try {
            block.applyBoneMeal(blockFace);
            ProjectileSource proj = e.getEntity().getShooter();
            if (proj instanceof Player p){
                p.sendMessage("bone meal!!");
            }
        }catch (NullPointerException exception){
            //System.out.println("NULL POINTER EXCEPTION");
            if (e instanceof Player){
                ProjectileSource proj = e.getEntity().getShooter();
                if (proj instanceof Player p){
                    p.sendMessage("NULL POINTER?!?!?!?");
                }
            }
        }
    }
}
