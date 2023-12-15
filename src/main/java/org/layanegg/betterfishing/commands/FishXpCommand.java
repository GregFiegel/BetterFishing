package org.layanegg.betterfishing.commands;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class FishXpCommand implements CommandExecutor {

    private final Plugin plugin;

    public FishXpCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player argPlayer = Bukkit.getServer().getPlayerExact(args[0]);
        if(sender instanceof Player senderPlayer){
            double num;
            if (args.length == 4){
                if (argPlayer == null){
                    return false;
                }
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
            }else if (args.length == 3){
                if(!(args[0].equals("xp") || args[0].equals("level"))){
                    return false;
                }
                if(!(args[1].equals("set") || args[1].equals("add"))){
                    return false;
                }
                try{
                    num = Double.parseDouble(args[2]);
                }catch (Exception e){
                    return false;
                }
            }else{
                return false;
            }

            if (args.length == 3){
                PersistentDataContainer data = senderPlayer.getPersistentDataContainer();
                if((args[0].equals("xp"))){
                    NamespacedKey nsk = new NamespacedKey(plugin, "fishXP");
                    if ((args[1].equals("set"))){
                        data.set(nsk, PersistentDataType.DOUBLE, num);
                        senderPlayer.sendMessage("Fishing Xp is now: " + num);
                    }else if((args[1].equals("add"))){
                        Double old = data.get(nsk, PersistentDataType.DOUBLE);
                        assert old != null;
                        data.set(nsk, PersistentDataType.DOUBLE, old + num);
                        senderPlayer.sendMessage("Fishing Xp is now: " + (old + num));
                    }
                }
                else if(args[0].equals("level")){
                    int numInt = (int) num;
                    NamespacedKey nsk = new NamespacedKey(plugin, "fishLVL");
                    if ((args[1].equals("set"))){
                        data.set(nsk, PersistentDataType.INTEGER, numInt);
                        senderPlayer.sendMessage("Fishing level is now: " + numInt);
                    }else if((args[1].equals("add"))){
                        Integer old = data.get(nsk, PersistentDataType.INTEGER);
                        assert old != null;
                        data.set(nsk, PersistentDataType.INTEGER, old + numInt);
                        senderPlayer.sendMessage("Fishing level is now: " + (numInt + old));
                    }
                }
            }

            if (args.length == 4){
                PersistentDataContainer data = argPlayer.getPersistentDataContainer();
                if((args[1].equals("xp"))){
                    NamespacedKey nsk = new NamespacedKey(plugin, "fishXP");
                    if ((args[2].equals("set"))){
                        data.set(nsk, PersistentDataType.DOUBLE, num);
                        senderPlayer.sendMessage(argPlayer.getDisplayName() + "'s Fishing Xp is now: " + num);
                    }else if((args[2].equals("add"))){
                        Double old = data.get(nsk, PersistentDataType.DOUBLE);
                        assert old != null;
                        data.set(nsk, PersistentDataType.DOUBLE, old + num);
                        senderPlayer.sendMessage(argPlayer.getDisplayName() + "'s Fishing Xp is now: " + (old + num));
                    }
                }
                else if(args[1].equals("level")){
                    int numInt = (int) num;
                    NamespacedKey nsk = new NamespacedKey(plugin, "fishLVL");
                    if ((args[2].equals("set"))){
                        data.set(nsk, PersistentDataType.INTEGER, numInt);
                        senderPlayer.sendMessage(argPlayer.getDisplayName() + "'s Fishing Xp is now: " + numInt);
                    }else if((args[2].equals("add"))){
                        Integer old = data.get(nsk, PersistentDataType.INTEGER);
                        assert old != null;
                        data.set(nsk, PersistentDataType.INTEGER, old + numInt);
                        senderPlayer.sendMessage(argPlayer.getDisplayName() + "'s Fishing Xp is now: " + (old + numInt));
                    }
                }
            }
        }
        return true;
    }
}
