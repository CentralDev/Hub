package me.imaginefrags.main.commands;

import me.confuser.barapi.BarAPI;
import me.imaginefrags.main.utils.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Random;

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
public class Donate implements CommandExecutor {

    public static void launchFirework(Player p) {
        Firework fw = p.getWorld().spawn(p.getLocation(), Firework.class);
        FireworkMeta fm = fw.getFireworkMeta();
        Random r = new Random();

        int fType = r.nextInt(5) + 1;
        FireworkEffect.Type type;
        switch (fType){
            default:
            case 1:
                type = FireworkEffect.Type.BALL;
                break;
            case 2:
                type = FireworkEffect.Type.BALL_LARGE;
                break;
            case 3:
                type = FireworkEffect.Type.BURST;
                break;
            case 4:
                type = FireworkEffect.Type.STAR;
                break;
            case 5:
                type = FireworkEffect.Type.CREEPER;

        }

        int c1i = r.nextInt(17) + 1;
        int c2i = r.nextInt(17) + 1;
        Color c1 = getColor(c1i);
        Color c2  = getColor(c2i);
        FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
        fm.addEffect(effect);
        int power = r.nextInt(2) + 1;

        fm.setPower(power);
        fw.setFireworkMeta(fm);

    }


    public static Color getColor(int c){
        switch (c){
            case 1:return Color.BLACK;
            case 2:return Color.AQUA;
            case 3:return Color.BLUE;
            case 4:return Color.FUCHSIA;
            case 5:return Color.GRAY;
            case 6:return Color.GREEN;
            case 7:return Color.LIME;
            case 8:return Color.MAROON;
            case 9:return Color.NAVY;
            case 10:return Color.OLIVE;
            case 11:return Color.ORANGE;
            case 12:return Color.PURPLE;
            case 13:return Color.RED;
            case 14:return Color.SILVER;
            case 15:return Color.TEAL;
            case 16:return Color.WHITE;
            case 17:return Color.YELLOW;

        }
        return null;

    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("donor")) {
            if (!(sender instanceof Player)) {
                if (args.length != 1) {
                    sender.sendMessage(ChatColor.RED + "Please use the correct format: " + ChatColor.DARK_RED + "/donor <player>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(ChatColor.GRAY + "§l>> §7That player is not §aONLINE §7right now. Their effects will happen when they §aJOIN");
                    Arrays.donor.add(args[0]);
                    return true;
                }
                for (Player online : Bukkit.getOnlinePlayers()) {
                    launchFirework(online);
                    BarAPI.setMessage(online, ChatColor.GREEN + "" + ChatColor.BOLD + target.getName() + ChatColor.RESET + "" + ChatColor.GREEN + " has donated!", 60);
                }
                Bukkit.broadcastMessage(ChatColor.GRAY + "§l>> " + ChatColor.GREEN + "" + ChatColor.BOLD + target.getName() + ChatColor.RESET + "" + ChatColor.GREEN + " has donated. You can donate at: " + ChatColor.GRAY +
                        "http://www.pvpwarfare.enjin.com/shop");
                target.sendMessage(ChatColor.GREEN + "Re-log §7to get your tab name changed.");
            }
            Player player = (Player) sender;
            if (!player.hasPermission("rank.owner")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission for this command.");
                return true;
            }
            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + "Please use the correct format: " + ChatColor.DARK_RED + "/donor <player>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.GRAY + "§l>> §7That player is not §aONLINE §7right now. Their effects will happen when they §aJOIN");
                Arrays.donor.add(args[0]);
                return true;
            }
            for (Player online : Bukkit.getOnlinePlayers()) {
                launchFirework(online);
                BarAPI.setMessage(online, ChatColor.GREEN + "" + ChatColor.BOLD + target.getName() + ChatColor.RESET + "" + ChatColor.GREEN + " has donated!", 60);
            }
            Bukkit.broadcastMessage(ChatColor.GRAY + "§l>> " + ChatColor.GREEN + "" + ChatColor.BOLD + target.getName() + ChatColor.RESET + "" + ChatColor.GREEN + " has donated. You can donate at: " + ChatColor.GRAY +
                    "http://www.pvpwarfare.enjin.com/shop");
            target.sendMessage(ChatColor.GREEN + "Re-log §7to get your tab name changed.");
        }
        return false;
    }
}
