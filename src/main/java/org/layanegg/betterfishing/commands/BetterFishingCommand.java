package org.layanegg.betterfishing.commands;

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class BetterFishingCommand implements CommandExecutor {

    private final Plugin plugin;

    public BetterFishingCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player p){
            double num;
            if (args.length < 4){
                return false;
            }else{
                if(!(args[1].equals("xp") || args[1].equals("level"))){
                    return false;
                }
                if(!(args[2].equals("set") || args[2].equals("add"))){
                    return false;
                }
                try{
                    num = Double.parseDouble(args[3]);
                }catch (Exception e){
                    return false;
                }
            }

            PersistentDataContainer data = p.getPersistentDataContainer();
            if((args[1].equals("xp"))){
                NamespacedKey nsk = new NamespacedKey(plugin, "fishXP");
                if ((args[2].equals("set"))){
                    data.set(nsk, PersistentDataType.DOUBLE, num);
                }else if((args[2].equals("add"))){
                    Double old = data.get(nsk, PersistentDataType.DOUBLE);
                    assert old != null;

                }
            }
            else if(args[1].equals("level")){
                if ((args[2].equals("set"))){

                }else if((args[2].equals("add"))){

                }
            }
        }

        return true;
    }
}
