package me.imaginefrags.main.HubItems.Fly;

import me.imaginefrags.main.utils.Arrays;
import me.imaginefrags.main.utils.Items;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

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
public class PlayerClick implements Listener {

    public ItemStack on;
    public ItemStack off;
    private final int cdtime = 5;
    private HashMap<String, Long> lastUsage = new HashMap<String, Long>();

    @EventHandler
    public void onPlayerToggle(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        this.on = Items.createItems(Material.MAGMA_CREAM, "§7§lFLY: §a§lON", "§8Poof. Magic.");
        this.off = Items.createItems(Material.SLIME_BALL, "§7§lFLY: §c§lOFF", "§8Poof. Magic.");
        if (!Arrays.playersLocked.contains(p.getName())) {
            if ((e.getAction() == Action.RIGHT_CLICK_AIR) && ((p.getItemInHand().getType().equals(Material.MAGMA_CREAM)) || (p.getItemInHand().getType().equals(Material.SLIME_BALL)))) {
                long lastUsed = 0L;
                if (this.lastUsage.containsKey(p.getName())) {
                    lastUsed = this.lastUsage.get(p.getName());
                }
                if (!p.hasPermission("rank.donor") && !p.hasPermission("rank.vip") && !p.hasPermission("rank.staff")) {
                    p.sendMessage("§7§l>> §cDonate at §4§lhttp://pvpwarfare.enjin.com/shop/ §cto use this item.");
                    return;
                }
                int cdmillis = 5000;
                if (System.currentTimeMillis() - lastUsed >= cdmillis) {
                    if (p.getItemInHand().getType().equals(Material.MAGMA_CREAM)) {
                        p.getInventory().setItemInHand(this.off);
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        Arrays.fly.add(p.getUniqueId().toString());
                        p.sendMessage("§7§l>> §7You have turned off §a§lfly§7.");
                        p.playSound(p.getLocation(), Sound.NOTE_BASS, 4.0F, 1.0F);

                    } else if (p.getItemInHand().getType().equals(Material.SLIME_BALL)) {
                        p.getInventory().setItemInHand(this.on);
                        p.setAllowFlight(true);
                        p.setFlying(true);
                        Arrays.fly.remove(p.getUniqueId().toString());
                        p.sendMessage("§7§l>> §7You have turned on §a§lfly§7.");
                        p.playSound(p.getLocation(), Sound.NOTE_PIANO, 4.0F, 1.0F);

                    }
                    this.lastUsage.put(p.getName(), System.currentTimeMillis());
                } else {
                    int timeLeft = (int) (5L - (System.currentTimeMillis() - lastUsed) / 1000L);
                    p.sendMessage("§7§l>> §cYou cannot use this item for another §l" + timeLeft + " §cseconds.");
                }
            }
        } else {
            p.sendMessage("§cPlayers Locked");
        }
    }
}
