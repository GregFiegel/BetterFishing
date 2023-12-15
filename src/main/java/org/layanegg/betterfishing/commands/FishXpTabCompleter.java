package org.layanegg.betterfishing.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FishXpTabCompleter implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (args.length == 1){
            List<String> playerNames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().toArray().length];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for(Player p : players){
                playerNames.add(p.getName());
            }
            return playerNames;
        }else if (args.length == 2){
            return List.of("xp", "level");
        }else if (args.length == 3){
            return List.of("set", "add");
        }else if (args.length == 4){
            return List.of("<number>");
        }


        return null;
    }
}
