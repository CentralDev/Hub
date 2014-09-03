package me.imaginefrags.main.commands;

import me.imaginefrags.main.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

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
public class PrivateMessage implements CommandExecutor {

    public static List<String> pmoff = new ArrayList<String>();
    private HashMap<UUID, UUID> recentMsg = new HashMap<UUID, UUID>();

    @Override
    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("msg")) {
            if (args.length < 2) {
                sender.sendMessage("§cPlease use the correct format: " + ChatColor.DARK_RED + "/msg <player> <message>");
                return true;
            }
            final Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage("§cCould not find player: " + ChatColor.DARK_RED + args[0]);
                return true;
            }
            String msg = "";
            for (int i = 1; i < args.length; i++) {
                msg += args[i] + " ";
            }
            if (pmoff.contains(target.getName()) && !sender.hasPermission("rank.staff")) {
                sender.sendMessage(ChatColor.RED + "That player has disabled private messages.");
                return false;
            }
            if (sender.getName().equals(target.getName())) {
                Player p = (Player) sender;
                if (!p.isOp()) {
                    sender.sendMessage("§cYou cannot message yourself.");
                    return true;
                }
            }
            Player p = (Player) sender;

            if (!pmoff.contains(target.getName()) || !sender.hasPermission("rank.staff")) {
                recentMsg.put(p.getUniqueId(), target.getUniqueId());
                recentMsg.put(target.getUniqueId(), p.getUniqueId());
                p.sendMessage(ChatColor.GREEN + "§lTo " + target.getName() + ChatColor.GRAY + " §l>> " + ChatColor.GREEN + msg);
                target.sendMessage(ChatColor.GREEN + "§lFrom " + sender.getName() + ChatColor.GRAY + " §l>> " + ChatColor.GREEN + msg);
            }
        }
        if(command.getName().equalsIgnoreCase("reply") || command.getName().equalsIgnoreCase("r")){
            Player p = (Player)sender;
            if(args.length < 0){
                sender.sendMessage("§cPlease use the correct format: " + ChatColor.DARK_RED + "/r <message>");
                return true;
            }
            if(recentMsg.containsKey(p.getUniqueId())){
                final Player target = Bukkit.getPlayer(String.valueOf(recentMsg.get(p.getUniqueId())));
                if(target == null){
                    p.sendMessage(""+ChatColor.RED + args[0] + "is no longer online!");
                    return true;
                }
                String msg = "";
                for(int i = 1;i< args.length;i++){
                    msg = msg + args[i] + " ";
                }

                recentMsg.put(p.getUniqueId(), target.getUniqueId());
                recentMsg.put(target.getUniqueId(), p.getUniqueId());
                target.sendMessage(ChatColor.GREEN + "§lFrom " + sender.getName() + ChatColor.GRAY + " §l>> " + ChatColor.GREEN + msg);
                p.sendMessage(ChatColor.GREEN + "§lTo " + target.getName() + ChatColor.GRAY + " §l>> " + ChatColor.GREEN + msg);
                return true;
            }else{

                p.sendMessage("§cYou have no one to reply to!");
                return true;
            }
        }

        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("pmoff")) {
            if (pmoff.contains(p.getName())) {
                p.sendMessage(ChatColor.RED + "You have already used this command. To turn it off, use " + ChatColor.DARK_RED + "/pmon");
                return true;
            }
            pmoff.add(p.getName());
            p.sendMessage(ChatColor.GRAY + "§l>>" + ChatColor.GREEN + " You have disabled private messages from non-staff members.");
            return true;
        }
        if (command.getName().equalsIgnoreCase("pmon")) {
            if (!pmoff.contains(p.getName())) {
                p.sendMessage(ChatColor.RED + "Your private messages are already on. To turn them off, use " + ChatColor.DARK_RED + "/pmoff");
                return true;
            }
            if (pmoff.contains(p.getName())) {
                pmoff.remove(p.getName());
                p.sendMessage(ChatColor.GRAY + "§l>>" + ChatColor.GREEN + " You have enabled private messages.");
                return true;
            }
        }
        return false;
    }
}
