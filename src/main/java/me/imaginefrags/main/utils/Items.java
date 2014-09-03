package me.imaginefrags.main.utils;

import org.bukkit.DyeColor;
import org.bukkit.Material;
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
public class Items {

    public static ItemStack createItem(Material material, int amount, short shrt, String displayname, String lore) {
        ItemStack item = new ItemStack(material, amount, (short) shrt);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(lore);
        meta.setLore(Lore);

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack createItems(Material material, String displayname, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayname);
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.clear();
        meta.setLore(Lore);

        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack back = createItem(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData(), "§cBACK", "§7Go back to the previous window.");
}
