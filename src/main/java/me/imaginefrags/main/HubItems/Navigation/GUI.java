package me.imaginefrags.main.HubItems.Navigation;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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
public class GUI implements Listener {

    public ItemStack createItem(Material material, int amount, short shrt, String displayname, String lore) {
        ItemStack item = new ItemStack(material, amount, (short) shrt);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(lore);
        meta.setLore(Lore);

        item.setItemMeta(meta);
        return item;
    }
    public ItemStack looks(Material material, int amount, short shrt, String displayname, String lore) {
        ItemStack item = new ItemStack(material, amount, (short) shrt);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.clear();

        item.setItemMeta(meta);
        return item;
    }
    short id = 1;
    @SuppressWarnings("deprecation")
    ItemStack gapple = looks(Material.getMaterial(322), 1, (short) 1, "§aPVPWarfare", "§7The coolest server around town.");


    public static Inventory warp;
    {
        warp = Bukkit.createInventory(null, 27, "§lFast Travel");

        warp.setItem(11, createItem(Material.ENDER_PEARL, 1, (short) 0, "§aOther  ", "§7Click to Teleport!"));
        warp.setItem(12, createItem(Material.FIREWORK, 1, (short) 0, "§aOther", "§7Click to Teleport!"));
        warp.setItem(14, createItem(Material.TNT, 1, (short) 0, "§aAssualt Zone", "§7Click to Teleport!"));
        warp.setItem(13, createItem(Material.COMPASS, 1, (short) 0, "§aSpawn", "§7Click to Teleport!"));
        warp.setItem(15, createItem(Material.EMERALD, 1, (short) 0, "§aOther ", "§7Click to Teleport!"));

        warp.setItem(0, gapple);
        warp.setItem(1, gapple);
        warp.setItem(2, gapple);
        warp.setItem(3, gapple);
        warp.setItem(4, gapple);
        warp.setItem(5, gapple);
        warp.setItem(6, gapple);
        warp.setItem(7, gapple);
        warp.setItem(8, gapple);

        warp.setItem(9, gapple);
        warp.setItem(10, gapple);
        warp.setItem(16, gapple);
        warp.setItem(17, gapple);

        warp.setItem(18, gapple);
        warp.setItem(19, gapple);
        warp.setItem(20, gapple);
        warp.setItem(21, gapple);
        warp.setItem(22, gapple);
        warp.setItem(23, gapple);
        warp.setItem(24, gapple);
        warp.setItem(25, gapple);
        warp.setItem(26, gapple);

    }
}
