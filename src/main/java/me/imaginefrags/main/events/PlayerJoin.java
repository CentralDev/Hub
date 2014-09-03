package me.imaginefrags.main.events;

import me.confuser.barapi.BarAPI;
import me.imaginefrags.main.HubItems.Navigation.PlayerClick;
import me.imaginefrags.main.commands.Donate;
import me.imaginefrags.main.utils.Arrays;
import me.imaginefrags.main.utils.Image.ImageChar;
import me.imaginefrags.main.utils.Image.ImageMessage;
import me.imaginefrags.main.utils.Items;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

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
public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        event.setJoinMessage("");
        p.getPlayer().getInventory().setItem(0, Items.createItems(Material.SLIME_BALL, "§7§lFLY: §c§lOFF", "§8Poof. Magic."));
        p.getPlayer().getInventory().setItem(8, Items.createItems(Material.GLOWSTONE_DUST, "§7§lPLAYERS: §a§lON", "§8Poof. Magic."));
        if (p.getName().equalsIgnoreCase("GizmoTheCat1999")) {
            p.setPlayerListName(ChatColor.DARK_PURPLE + "GizmoTheCat");
        }
        if (p.getName().equalsIgnoreCase("ReeseHasNoPhone")) {
            p.setPlayerListName(ChatColor.DARK_GREEN + "ReeseHasPhone");
        }
        if (p.hasPermission("rank.guest")) {
            p.setPlayerListName(ChatColor.YELLOW + p.getName());
        }
        if (p.hasPermission("rank.donor")) {
            p.setPlayerListName(ChatColor.AQUA + p.getName());
        }
        if (p.hasPermission("rank.vip")) {
            p.setPlayerListName(ChatColor.DARK_PURPLE + p.getName());
        }
        if (p.hasPermission("rank.mod")) {
            p.setPlayerListName(ChatColor.RED + p.getName());
        }
        if (p.hasPermission("rank.srmod")) {
            p.setPlayerListName(ChatColor.RED + "" + ChatColor.BOLD + p.getName());
        }
        if (p.hasPermission("rank.admin")) {
            p.setPlayerListName(ChatColor.DARK_RED + p.getName());
        }
        if (p.hasPermission("rank.owner")) {
            p.setPlayerListName(ChatColor.DARK_RED + "" + ChatColor.BOLD + p.getName());
        }
        if (p.hasPermission("rank.dev")) {
            p.setPlayerListName(ChatColor.DARK_AQUA + "§l" + p.getName());
        }

        event.getPlayer().teleport(new Location(Bukkit.getWorld("world"), 0.48555, 81.000, 0.56577, 179.80573F, 2F));

        try {
            event.getPlayer().sendMessage("§7§l<<§a§m-------------------------------------------------§7§l>>");
            BufferedImage imageToSend = null;
            imageToSend = ImageIO.read(new URL("http://mineskin.ca/v2/avatar/?player=" + event.getPlayer().getName()));
            new ImageMessage(
                    imageToSend, // the bufferedimage to send
                    8, // the image height
                    ImageChar.BLOCK.getChar() // the character that the image is made of
            ).appendText(
                    "",
                    "§7§l>> §7The server is still in §a§lALPHA",
                    "§7§l>> §7Put text here",
                    "§7§l>> §7Put text here",
                    "§7§l>> §7Put text here",
                    "§7§l>> §7Put text here",
                    "§7§l>> §7Put text here"
            ).sendToPlayer(event.getPlayer());
            event.getPlayer().sendMessage("§7§l<<§a§m-------------------------------------------------§7§l>>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Arrays.donor.contains(p.getName())) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                Donate.launchFirework(online);
                BarAPI.setMessage(online, ChatColor.GREEN + "" + ChatColor.BOLD + p.getName() + ChatColor.RESET + "" + ChatColor.GREEN + " has donated!", 60);
            }
            Bukkit.broadcastMessage(ChatColor.GRAY + "§l>> " + ChatColor.GREEN + "" + ChatColor.BOLD + p.getName() + ChatColor.RESET + "" + ChatColor.GREEN + " has donated. You can donate at: " + ChatColor.GRAY +
                    "http://www.pvpwarfare.enjin.com/shop");
            Arrays.donor.remove(p.getName());
        }
    }
}
