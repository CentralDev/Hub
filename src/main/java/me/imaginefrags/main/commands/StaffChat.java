package me.imaginefrags.main.commands;

import me.imaginefrags.main.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
public class StaffChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("staff")) {
            if (!(sender.hasPermission("rank.staff"))) {
                sender.sendMessage(Message.noPerms);
                return true;
            }
            if (args.length < 1) {
                sender.sendMessage("§cPlease use the correct format: " + ChatColor.DARK_RED + "/staff <message>");
                return true;
            }
            String msg = "";
            for (int i = 0; i < args.length; i++) {
                msg += args[i] + " ";
            }
            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (!(staff.hasPermission("rank.staff"))) {
                    return false;
                } else {
                    if (staff.hasPermission("rank.mod")) {
                        staff.sendMessage(ChatColor.GRAY + "§l<<" + ChatColor.GREEN + "§lSTAFF" + ChatColor.GRAY + "§l>> " + ChatColor.RED + staff.getName() + ChatColor.GRAY + ": §f" + msg);
                    }
                    if (staff.hasPermission("rank.srmod")) {
                        staff.sendMessage(ChatColor.GRAY + "§l<<" + ChatColor.GREEN + "§lSTAFF" + ChatColor.GRAY + "§l>> " + ChatColor.RED + "" + ChatColor.BOLD + staff.getName() + ChatColor.GRAY + ": §f" + msg);
                    }
                    if (staff.hasPermission("rank.admin")) {
                        staff.sendMessage(ChatColor.GRAY + "§l<<" + ChatColor.GREEN + "§lSTAFF" + ChatColor.GRAY + "§l>> " + ChatColor.DARK_RED + staff.getName() + ChatColor.GRAY + ": §f" + msg);
                    }
                    if (staff.hasPermission("rank.owner")) {
                        staff.sendMessage(ChatColor.GRAY + "§l<<" + ChatColor.GREEN + "§lSTAFF" + ChatColor.GRAY + "§l>> " + ChatColor.DARK_RED + "" + ChatColor.BOLD + staff.getName() + ChatColor.GRAY + ": §f" + msg);
                    }
                    if (staff.hasPermission("rank.dev")) {
                        staff.sendMessage(ChatColor.GRAY + "§l<<" + ChatColor.GREEN + "§lSTAFF" + ChatColor.GRAY + "§l>> " + ChatColor.DARK_AQUA + "" + ChatColor.BOLD + staff.getName() + ChatColor.GRAY + ": §f" + msg);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
