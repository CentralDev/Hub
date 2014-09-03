package me.imaginefrags.main.events;

import me.imaginefrags.main.commands.PrivateMessage;
import me.imaginefrags.main.utils.Arrays;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

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
public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage("");
        e.getPlayer().getInventory().clear();
        e.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
        e.getPlayer().getInventory().setChestplate(new ItemStack(Material.AIR));
        e.getPlayer().getInventory().setLeggings(new ItemStack(Material.AIR));
        e.getPlayer().getInventory().setBoots(new ItemStack(Material.AIR));
        Arrays.pmoff.remove(e.getPlayer().getName());
        Arrays.playersLocked.remove(p.getUniqueId().toString());

        p.getInventory().clear();
    }
}
