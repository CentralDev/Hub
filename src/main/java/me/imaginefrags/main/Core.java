package me.imaginefrags.main;

import me.imaginefrags.main.HubItems.Navigation.GUI;
import me.imaginefrags.main.HubItems.Navigation.PlayerClick;
import me.imaginefrags.main.Signs.StatusSign;
import me.imaginefrags.main.commands.*;
import me.imaginefrags.main.events.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
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
public class Core extends JavaPlugin implements Listener {

    PluginManager pm = Bukkit.getServer().getPluginManager();
    private ArrayList<StatusSign> signs;

    public void onEnable() {

        this.signs = new ArrayList<StatusSign>();

        saveDefaultConfig();
        for (String str : getConfig().getKeys(false)) {
            ConfigurationSection s = getConfig().getConfigurationSection(str);
            ConfigurationSection l = s.getConfigurationSection("loc");
            World w = Bukkit.getServer().getWorld("world");
            double x = l.getDouble("x"), y = l.getDouble("y"), z = l.getDouble("z");
            Location loc = new Location(w, x, y, z);

            if (loc.getBlock() == null) {
                getConfig().set(str, null);
            } else {
                signs.add(new StatusSign(loc, s.getString("name"), s.getString("ip"), s.getInt("port")));
            }
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (StatusSign s :  signs) {
                    s.update();
                }
            }
        }, 0, 20);

        Bukkit.getServer().getWorld("world").setTime(0);

        registerEvents();
        registerCommands();
        registerHubItems();

        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    public void onDisable() {

    }

    private void registerEvents() {
        pm.registerEvents(new Blocks(), this);
        pm.registerEvents(new PlayerChat(), this);
        pm.registerEvents(new PlayerDamage(), this);
        pm.registerEvents(new PlayerDrop(), this);
        pm.registerEvents(new PlayerFood(), this);
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new ServerPing(), this);
        pm.registerEvents(new WeatherChange(), this);
        pm.registerEvents(this, this);
    }

    private void registerCommands() {
        getCommand("msg").setExecutor(new PrivateMessage());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("report").setExecutor(new Report());
        getCommand("chatmute").setExecutor(new ChatMute());
        getCommand("chatunmute").setExecutor(new ChatMute());
        getCommand("staff").setExecutor(new StaffChat());
        getCommand("r").setExecutor(new PrivateMessage());
        getCommand("pmon").setExecutor(new PrivateMessage());
        getCommand("pmoff").setExecutor(new PrivateMessage());
        getCommand("donor").setExecutor(new Donate());
        getCommand("sign").setExecutor(this);
    }

    private void registerHubItems() {
        pm.registerEvents(new GUI(), this);
        pm.registerEvents(new PlayerClick(), this);
        pm.registerEvents(new me.imaginefrags.main.HubItems.Fly.PlayerClick(), this);
        pm.registerEvents(new me.imaginefrags.main.HubItems.PlayerToggle.PlayerClick(), this);
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = e.getClickedBlock();
        if (block.getType() != Material.SIGN && block.getType() != Material.SIGN_POST && block.getType() != Material.WALL_SIGN) return;

        for (StatusSign s : signs) {
            if (s.getLocation().equals(block.getLocation())) {
                try {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);

                    out.writeUTF("Connect");
                    out.writeUTF(s.getName());

                    e.getPlayer().sendPluginMessage(this, "BungeeCord", b.toByteArray());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("sign")) {
            if (args.length <3) {
                p.sendMessage(ChatColor.RED + "Please use the correct format: " + ChatColor.DARK_RED + "/sign <ip> <port> <name>");
                return true;
            }
            String ip = args[0];
            int port;
            String name = args[2];

            try {
                port = Integer.valueOf(args[1]);
            }catch (Exception e) {
                p.sendMessage(ChatColor.RED + "Port is not a valid number.");
                return true;
            }

            Block block = p.getTargetBlock(null, 10);
            if (block == null) {
                p.sendMessage(ChatColor.RED + "You are not looking at a sign.");
                return true;
            }
            if (block.getType() != Material.SIGN && block.getType() != Material.SIGN_POST && block.getType() != Material.WALL_SIGN) {
                p.sendMessage(ChatColor.RED + "You are not looking at a sign.");
                return true;
            }

            Sign s = (Sign) block.getState();
            StatusSign statusSign = new StatusSign(block.getLocation(), name, ip, port);
            signs.add(statusSign);
            save(statusSign);
        }
        return false;
    }
    private void save(StatusSign sign) {
        int size = getConfig().getKeys(false).size() + 1;

        getConfig().set(size + ".loc.world", sign.getLocation().getWorld().getName());
        getConfig().set(size + ".loc.x", sign.getLocation().getX());
        getConfig().set(size + ".loc.y", sign.getLocation().getY());
        getConfig().set(size + ".loc.z", sign.getLocation().getZ());

        getConfig().set(size + ".name", sign.getName());
        getConfig().set(size + ".ip", sign.getIP());
        getConfig().set(size + ".port", sign.getPort());

        saveConfig();
    }
}
