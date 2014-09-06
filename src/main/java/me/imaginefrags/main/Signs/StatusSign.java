package me.imaginefrags.main.Signs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

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
public class StatusSign {

    private Location location;
    private Sign sign;
    private String name, ip;
    private int port;

    public StatusSign(Location location, String name, String ip, int port) {
        this.location = location;
        this.sign = (Sign) location.getBlock().getState();
        this.name = name;
        this.ip = ip;
        this.port = port;
    }
    public Location getLocation() {
        return location;
    }
    public String getName() {
        return name;
    }

    public String getIP() {
        return ip;
    }
    public Integer getPort() {
        return port;
    }

    public void update() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port), 1000);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.write(0xFE);

            StringBuilder str = new StringBuilder();

            int b;
            while ((b = in.read()) != -1) {
                if (b != 0 && b > 16 && b != 255 && b != 23 && b != 24) {
                    str.append((char) b);
                }
            }

            String[] data = str.toString().split("§");
            String motd = data[0];
            int onlinePlayers = Integer.valueOf(data[1]);
            int maxPlayers = Integer.valueOf(data[2]);

            sign.setLine(0, ChatColor.GREEN + name);
            sign.setLine(1, ChatColor.DARK_GRAY + "§l" + ip);
            sign.setLine(2, "§a" + onlinePlayers + "§7/§a" + maxPlayers);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();

            sign.setLine(0, name);
            sign.setLine(1, ip);
            sign.setLine(2, ChatColor.RED + "Error.");
        }

        sign.update();
    }
}
