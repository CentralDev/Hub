package me.imaginefrags.main.events;

import org.bukkit.ChatColor;
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
public class PlayerChat implements Listener {

    @EventHandler
    public void Chat(AsyncPlayerChatEvent e) {

        if (e.getPlayer().hasPermission("rank.owner")) {
            e.setFormat(ChatColor.DARK_RED + "" + ChatColor.BOLD + "OWNER %s" + ChatColor.GRAY + ": §f%s");
            return;
        }
        if (e.getPlayer().hasPermission("rank.dev")) {
            e.setFormat(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "DEV %s" + ChatColor.GRAY + ": §f%s");
            return;
        }
        if (e.getPlayer().hasPermission("rank.admin")) {
            e.setFormat(ChatColor.DARK_RED + "§lADMIN §4%s" + ChatColor.GRAY + ":" + ChatColor.AQUA + " §f%s");
            return;
        }
        if (e.getPlayer().hasPermission("rank.srmod")) {
            e.setFormat(ChatColor.RED + "" + ChatColor.BOLD + "SRMOD %s" + ChatColor.GRAY + ": §f%s");
            return;
        }
        if (e.getPlayer().hasPermission("rank.mod")) {
            e.setFormat(ChatColor.RED + "§lMOD §c%s" + ChatColor.GRAY + ": §f%s");
            return;
        }
        if (e.getPlayer().hasPermission("rank.vip")) {
            e.setFormat(ChatColor.DARK_PURPLE + "§lVIP §5%s" + ChatColor.GRAY + ": §f%s");
            return;
        }
        if (e.getPlayer().hasPermission("rank.donor")) {
            e.setFormat(ChatColor.AQUA + "§lDONOR §3%s" + ChatColor.GRAY + ": §f%s");
            return;
        }
        if (e.getPlayer().hasPermission("rank.guest")) {
            e.setFormat(ChatColor.YELLOW + "§lPLAYER §e%s" + ChatColor.GRAY + ": §f%s");
        }
    }
}
