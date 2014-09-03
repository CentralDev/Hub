package me.imaginefrags.main.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

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
public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player p = event.getPlayer();
        Location loc = p.getLocation();

        if (loc.getBlockY() <= 62.200 || loc.getBlockY() >= 147.534 || loc.getBlockX() >= 74){
            Bukkit.dispatchCommand(p, "spawn");
            p.sendMessage("§7§l>> §7You have been §ateleported §7back to §aspawn§7.");
        }
    }
}
