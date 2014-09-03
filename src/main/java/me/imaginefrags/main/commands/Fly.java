package me.imaginefrags.main.commands;

import me.imaginefrags.main.utils.Arrays;
import me.imaginefrags.main.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * ***********************************************************************
 * Copyright CentralDev (c) 2014. All Rights Reserved.
 * Any code contained within this document, and any associated APIs with similar branding
 * are the sole property of ImagineFrags. Distribution, reproduction, taking snippets, or
 * claiming any contents as your own will break the terms of the license, and void any
 * agreements with you, the third party.
 * Thanks.
 * ************************************************************************
 */
public class Fly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("fly")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cYou must be a player to use this command!");
                return true;
            }
            Player p = (Player) sender;
            if (!p.isOp() && !p.hasPermission("rank.staff") && !p.hasPermission("rank.vip")) {
                p.sendMessage(Message.noPerms);
                return true;
            }
            if (Arrays.fly.contains(p.getName())) {
                p.setAllowFlight(false);
                p.setFlying(false);
                Arrays.fly.remove(p.getUniqueId().toString());
                p.sendMessage("§7§l>> §7You have turned off §a§lfly");
                return true;
            }
            p.setAllowFlight(true);
            p.setFlying(true);
            Arrays.fly.add(p.getUniqueId().toString());
            p.sendMessage("§7§l>> §7You have turned on §a§lfly");
            return true;
        }
        return false;
    }
}
