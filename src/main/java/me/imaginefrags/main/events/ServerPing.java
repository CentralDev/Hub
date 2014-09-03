package me.imaginefrags.main.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

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
public class ServerPing implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        if (Bukkit.getServer().hasWhitelist()) {
            e.setMotd(ChatColor.GRAY + "§a§lPVP§aWarfare §7has whitelist enabled!\n§aDonate §7to gain access!");
            return;
        }
        e.setMotd(ChatColor.GRAY + "Welcome to §aPVPWarfare§7! Please have fun!\n§aDonate §7to get donor rank! §a$10");
        e.setMaxPlayers(1000);
    }
}
