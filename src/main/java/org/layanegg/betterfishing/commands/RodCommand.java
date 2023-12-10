package org.layanegg.betterfishing.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RodCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // /rod - gives the player a fishing rod

        if (sender instanceof Player p){
            Inventory i = p.getInventory();
            i.addItem(new ItemStack(Material.FISHING_ROD));
        }
        //System.out.println("RUNNING ROD COMMAND");
        return true;
    }
}
