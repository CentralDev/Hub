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
public class Report implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("report")) {
            if (args.length < 2) {
                sender.sendMessage("§cPlease use the correct format: " + ChatColor.DARK_RED + "/report <player> <reason>");
                return true;
            }
            final Player reported = Bukkit.getPlayer(args[0]);

            if (reported == null) {
                sender.sendMessage("§cCould not find player: " + ChatColor.DARK_RED + args[0]);
                return true;
            }
            String reason = "";
            for (int i = 1; i < args.length; i++) {
                reason += args[i] + " ";
            }
            sender.sendMessage("§7>> §aYou're §a§lreport §ahas been sent to an §a§lonline §astaff member.");
            for (Player staff : Bukkit.getOnlinePlayers()) {
                if (staff.hasPermission("rank.staff")) {
                    staff.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "<<" + ChatColor.DARK_RED + "REPORT" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + ">> " + ChatColor.DARK_RED + "Player: " + ChatColor.RED +
                            reported.getName() + ChatColor.WHITE + " -" + ChatColor.DARK_RED + " Reason: §c" + reason + ChatColor.WHITE + "- " + ChatColor.DARK_RED + "From: " + ChatColor.RED + sender.getName());
                }
            }
        }
        return false;
    }
}
