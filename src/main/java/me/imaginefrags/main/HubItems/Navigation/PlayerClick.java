package me.imaginefrags.main.HubItems.Navigation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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

    @EventHandler
    @SuppressWarnings("deprecation")
    public static void onJoin(PlayerJoinEvent e) {

        ItemStack warp = new ItemStack(Material.NETHER_STAR);
        ItemMeta mwarp = warp.getItemMeta();
        mwarp.setDisplayName("§7§lFAST TRAVEL");
        List<String> loreList11 = new ArrayList<String>();
        loreList11.clear();
        warp.setItemMeta(mwarp);
        e.getPlayer().getInventory().setItem(4, warp);
    }

    @EventHandler
    public void Interact(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        try{
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (p.getItemInHand().getItemMeta().getDisplayName().equals("§7§lFAST TRAVEL")) {
                    p.openInventory(GUI.warp);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getInventory().getName().equals(GUI.warp.getName())) {
            e.setCancelled(true);

            if (e.getCurrentItem() == null) {
                return;
            }

            if (!(e.getCurrentItem().hasItemMeta())) {
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aOther  ")) {
                p.teleport(new Location(Bukkit.getWorld("world"), -46.46291, 76.000, 0.54412, 90.32257F, 1F));
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aOther ")) {
                p.teleport(new Location(e.getWhoClicked().getWorld(), 0.45947, 76.000, -46.35840, -179.6637F, 2F));
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAssualt Zone")) {
                p.teleport(new Location(e.getWhoClicked().getWorld(), 0.45947, 76.000, 47.40448, -0.6099243F, 0F));
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSpawn")) {
                Bukkit.dispatchCommand(p, "spawn");
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                return;
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aOther")) {
                p.teleport(new Location(Bukkit.getWorld("world"), 47.69850, 76.000, 0.48296, -89.55408F, 3F));
                p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1, 1);
                return;
            }
        }
    }
}
