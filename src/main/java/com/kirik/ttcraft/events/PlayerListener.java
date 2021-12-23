package com.kirik.ttcraft.events;

import com.kirik.ttcraft.main.TTCraft;
import com.kirik.ttcraft.player.TTPlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.io.IOException;
import java.util.UUID;

public class PlayerListener implements Listener {

    private final TTCraft plugin;

    public PlayerListener(TTCraft plug) {
        plugin = plug;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!player.hasPlayedBefore())
            player.teleport(plugin.getWorldSpawn());

        // Loads defaults for additional attributes
        TTPlayer ttplayer = new TTPlayer(player);

        if (playerUUID.equals(UUID.fromString("3f050fe1-a454-4a30-8c8d-4acc691f2b2d")) || // kirik__
                playerUUID.equals(UUID.fromString("dd871f27-e02d-46b2-b505-3b931aac6daa"))) { // circusfreak83
            try {
                plugin.getServer().getWorld("world").strikeLightningEffect(player.getLocation());
            } catch (NullPointerException ex) {
                plugin.sendConsoleError("Oopsies, can't strike lightning... " + ex);
            }
        }

        plugin.getOnlinePlayers().put(playerUUID, ttplayer);


        if (!ttplayer.getNickname().equals("")) {
            e.setJoinMessage("\u00a72[+] \u00a7r" + ttplayer.getNickname() + " \u00a7ejoined!");
        } else {
            e.setJoinMessage("\u00a72[+] \u00a77" + ttplayer.getUsername() + " \u00a7ejoined!");
        }
        player.setPlayerListName(ttplayer.getNickname());
        player.setDisplayName(ttplayer.getNickname());

        String[] motd = plugin.getMOTD().replace("$", "\u00a7").split("(nl)");
        for(String motdPiece : motd)
            plugin.sendPlayerMessage(player, motdPiece);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        TTPlayer ttplayer = plugin.getOnlinePlayers().get(playerUUID);
        try {
            ttplayer.playerConfig.save(ttplayer.getPlayerFile());
            plugin.sendConsoleMsg("Saved player file " + playerUUID);
        } catch (IOException | NullPointerException ex) {
            plugin.sendConsoleError("Failed to save player file " + playerUUID + ". Reason: " + ex);
        }

        plugin.getOnlinePlayers().remove(playerUUID);

        if (!ttplayer.getNickname().equals("")) {
            e.setQuitMessage("\u00a74[-] \u00a7r" + ttplayer.getNickname() + " \u00a7edisconnected!");
        } else {
            e.setQuitMessage("\u00a74[-] \u00a77" + e.getPlayer().getName() + " \u00a7edisconnected!");
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        TTPlayer ttplayer = plugin.getOnlinePlayers().get(playerUUID);
        try {
            ttplayer.playerConfig.save(ttplayer.getPlayerFile());
            plugin.sendConsoleMsg("Saved player file " + playerUUID);
        } catch (IOException | NullPointerException ex) {
            plugin.sendConsoleError("Failed to save player file " + playerUUID + ". Reason: " + ex);
        }

        plugin.getOnlinePlayers().remove(playerUUID);

        if (!ttplayer.getNickname().equals("")) {
            e.setLeaveMessage("\u00a74[-] \u00a7r" + ttplayer.getNickname() + " \u00a7ekicked! (" + e.getReason() + ")");
        } else {
            e.setLeaveMessage("\u00a74[-] \u00a77" + e.getPlayer().getName() + " \u00a7ekicked! (" + e.getReason() + ")");
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        TTPlayer ttplayer = plugin.getOnlinePlayers().get(playerUUID);

        String colorMessage = e.getMessage().replace("$", "\u00a7");

        if (!ttplayer.getNickname().equals("")) {
            e.setFormat(ttplayer.getNickname() + "\u00a7f: " + colorMessage);
        } else {
            e.setFormat("\u00a77" + player.getName() + "\u00a7f: " + colorMessage);
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (e.getPlayer().getBedSpawnLocation() == null) {
            Location location = plugin.getWorldSpawn();
            e.setRespawnLocation(location);
        }
    }
}
