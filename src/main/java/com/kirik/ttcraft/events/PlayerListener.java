package com.kirik.ttcraft.events;

import com.kirik.ttcraft.main.TTCraft;
import com.kirik.ttcraft.main.util.TimeUtil;
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
        TTPlayer player_ = new TTPlayer(player);

        if (playerUUID.equals(UUID.fromString("3f050fe1-a454-4a30-8c8d-4acc691f2b2d")) || // kirik__
                playerUUID.equals(UUID.fromString("dd871f27-e02d-46b2-b505-3b931aac6daa"))) { // circusfreak83
            try {
                plugin.getServer().getWorld("world").strikeLightningEffect(player.getLocation());
            } catch (NullPointerException ex) {
                plugin.sendConsoleError("Oopsies, can't strike lightning... " + ex);
            }
        }

        // TODO Make sure players spawn in at actual spawn
        // Should be done still need to test

        plugin.onlinePlayers.put(playerUUID, player_);

        /*Scoreboard mainScoreboard = plugin.getServer().getScoreboardManager().getMainScoreboard();
        Team playerTeam = mainScoreboard.registerNewTeam("SLOT_" + plugin.getServer().getScoreboardManager().getMainScoreboard().getTeams().size());
        playerTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        playerTeam.setDisplayName(player_.getNickname());
        playerTeam.addEntry(player.getName());*/


        //String nickname = playerHelper.getPlayerNicknameByUUID(playerUUID);
        if (!player_.getNickname().equals("")) {
            e.setJoinMessage("\u00a72[+] \u00a7r" + player_.getNickname() + " \u00a7ejoined!");
        } else {
            e.setJoinMessage("\u00a72[+] \u00a77" + player_.getUsername() + " \u00a7ejoined!");
        }
        player.setPlayerListName(player_.getNickname());
        player.setDisplayName(player_.getNickname());

        String[] motd = plugin.getMOTD().split("\\r?\\n");
        for(String motdPiece : motd)
            plugin.sendPlayerMessage(player, motdPiece);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        TTPlayer player_ = plugin.onlinePlayers.get(playerUUID);
        try {
            player_.playerConfig.save(player_.getPlayerFile());
            plugin.sendConsoleMsg("Saved player file " + playerUUID);
        } catch (IOException | NullPointerException ex) {
            plugin.sendConsoleError("Failed to save player file " + playerUUID + ". Reason: " + ex);
        }

        plugin.onlinePlayers.remove(playerUUID);

        /*Scoreboard mainScoreboard = plugin.getServer().getScoreboardManager().getMainScoreboard();
        Team playerTeam = mainScoreboard.getTeam(playerUUID.toString());
        playerTeam.unregister();*/


        if (!player_.getNickname().equals("")) {
            e.setQuitMessage("\u00a74[-] \u00a7r" + player_.getNickname() + " \u00a7edisconnected!");
        } else {
            e.setQuitMessage("\u00a74[-] \u00a77" + e.getPlayer().getName() + " \u00a7edisconnected!");
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        TTPlayer player_ = plugin.onlinePlayers.get(playerUUID);
        try {
            player_.playerConfig.save(player_.getPlayerFile());
            plugin.sendConsoleMsg("Saved player file " + playerUUID);
        } catch (IOException | NullPointerException ex) {
            plugin.sendConsoleError("Failed to save player file " + playerUUID + ". Reason: " + ex);
        }

        plugin.onlinePlayers.remove(playerUUID);

        if (!player_.getNickname().equals("")) {
            e.setLeaveMessage("\u00a74[-] \u00a7r" + player_.getNickname() + " \u00a7ekicked! (" + e.getReason() + ")");
        } else {
            e.setLeaveMessage("\u00a74[-] \u00a77" + e.getPlayer().getName() + " \u00a7ekicked! (" + e.getReason() + ")");
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        TTPlayer player_ = plugin.onlinePlayers.get(playerUUID);

        if (!player_.getNickname().equals("")) {
            e.setFormat(player_.getNickname() + "\u00a7f: " + e.getMessage());
        } else {
            e.setFormat("\u00a77" + player.getName() + "\u00a7f: " + e.getMessage());
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (e.getPlayer().getBedSpawnLocation() == null) {
            Location location = plugin.getWorldSpawn();
            e.setRespawnLocation(location);
        }
    }

    /*@EventHandler
    public void onPlayerSleep(PlayerBedEnterEvent e) {
        TTPlayer player_ = plugin.onlinePlayers.get(e.getPlayer().getUniqueId());
        int numPlayers = plugin.getServer().getOnlinePlayers().size();
        int sleeping = 0;
        for(Player player : plugin.getServer().getOnlinePlayers()) {
            if(player.isSleeping())
                sleeping += 1;
        }
        plugin.sendServerMessage(player_.getNickname() + " went to sleep (" + sleeping + "/" + numPlayers/2 + ")");
        plugin.getServer().getWorld("world").setTime((long)TimeUtil.TIME_NIGHT_END);
    }*/
}
