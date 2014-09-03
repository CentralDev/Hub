package me.imaginefrags.main;

import me.imaginefrags.main.HubItems.Navigation.GUI;
import me.imaginefrags.main.HubItems.Navigation.PlayerClick;
import me.imaginefrags.main.commands.*;
import me.imaginefrags.main.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

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
public class Core extends JavaPlugin {

    PluginManager pm = Bukkit.getServer().getPluginManager();

    public void onEnable() {

        Bukkit.getServer().getWorld("world").setTime(0);

        registerEvents();
        registerCommands();
        registerHubItems();
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
    }

    private void registerCommands() {
        getCommand("msg").setExecutor(new PrivateMessage());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("report").setExecutor(new Report());
        getCommand("chatmute").setExecutor(new ChatMute());
        getCommand("chatunmute").setExecutor(new ChatMute());
        /*getCommand("fly").setExecutor(new Fly());*/
        getCommand("staff").setExecutor(new StaffChat());
        getCommand("r").setExecutor(new PrivateMessage());
        getCommand("pmon").setExecutor(new PrivateMessage());
        getCommand("pmoff").setExecutor(new PrivateMessage());
        getCommand("donor").setExecutor(new Donate());
    }

    private void registerHubItems() {
        pm.registerEvents(new GUI(), this);
        pm.registerEvents(new PlayerClick(), this);
        pm.registerEvents(new me.imaginefrags.main.HubItems.Fly.PlayerClick(), this);
        pm.registerEvents(new me.imaginefrags.main.HubItems.PlayerToggle.PlayerClick(), this);
    }
}
