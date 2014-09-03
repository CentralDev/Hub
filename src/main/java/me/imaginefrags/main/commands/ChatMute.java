package me.imaginefrags.main.commands;

import me.imaginefrags.main.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

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
public class ChatMute implements CommandExecutor, Listener {

    public static boolean chatDisabled;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (chatDisabled) {
            if (!p.hasPermission("rank.staff")) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.RED + "The chat has been disabled.");
                return;
            }else {
                if (!chatDisabled) {
                    e.setCancelled(false);
                }
            }
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("chatmute")) {
            if (!(sender.isOp())) {
                sender.sendMessage(Message.noPerms);
                return true;
            }
            chatDisabled = true;
            Bukkit.getServer().broadcastMessage("§7§l>> " + ChatColor.RED + "The chat has been disabled! Only staff can chat now!");
        } else if (cmd.getName().equalsIgnoreCase("chatunmute")) {
            if (!(sender.isOp())) {
                sender.sendMessage(Message.noPerms);
                return true;
            }
            chatDisabled = false;
            Bukkit.getServer().broadcastMessage("§7§l>> " + ChatColor.GREEN + "The chat has been enabled for everyone!");
        }
        return false;
    }
}
