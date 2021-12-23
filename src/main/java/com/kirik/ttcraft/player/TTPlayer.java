package com.kirik.ttcraft.player;

import com.kirik.ttcraft.main.TTCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class TTPlayer {

    private final TTCraft plugin = TTCraft.instance;

    private final File playerConfigFile;
    public FileConfiguration playerConfig;

    private final UUID uuid;
    private final String username;
    private String nickname;

    private final Player bukkitPlayer;
    //private Rank rank;
    private Home home = null;

    public TTPlayer(UUID uuid) {
        this.bukkitPlayer = Bukkit.getPlayer(uuid);
        this.uuid = uuid;
        this.username = bukkitPlayer.getName();
        this.playerConfigFile = new File(plugin.getDataFolder() + "/players/", uuid + ".yml");
        this.playerConfig = getPlayerConfig();

        this.nickname = (playerConfig.getString("nickname") == null) ? "" : playerConfig.getString("nickname");
        this.home = (playerConfig.getLocation("home") == null) ?
                new Home(plugin.getServer().getWorld("world").getSpawnLocation()) : new Home(playerConfig.getLocation("home"));
    }

    public TTPlayer(Player player) {
        this(player.getUniqueId());
    }

    public FileConfiguration getPlayerConfig(){
        if(playerConfigFile.exists()) {
            plugin.sendConsoleMsg("Player file for " + uuid + " loaded");
            return YamlConfiguration.loadConfiguration(playerConfigFile);
        }else {
            try {
                FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerConfigFile);
                //playerConfig.save(playerConfigFile);
                loadPlayerDefaults(playerConfig);
                plugin.sendConsoleMsg("Player file for " + uuid + " created");
                playerConfig.save(playerConfigFile);
                return playerConfig;
            } catch (Exception e) {
                e.printStackTrace();
                plugin.sendConsoleError("Player file for " + uuid + " did not load properly");
                return null;
            }
        }
    }

    public File getPlayerFile() {
        return playerConfigFile;
    }

    private void loadPlayerDefaults(FileConfiguration playerConfig) {
        playerConfig.set("nickname", nickname);
    }

    /*public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }*/

    public void saveConfig() {
        if(!playerConfigFile.exists()) {
            playerConfig = getPlayerConfig();
        }
        try {
            playerConfig.save(playerConfigFile);
        }catch(IOException e) {
            e.printStackTrace();
            plugin.sendConsoleError("Unable to save player file " + getUUID() + ". Reason:" + e);
        }
    }

    public UUID getUUID() {
        return uuid;
    }

    /*public void setUsername(String username) {
        this.username = username;
    }*/

    public String getUsername() {
        return username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        playerConfig.set("nickname", nickname);
        saveConfig();
    }

    public void setHome(Home home) {
        playerConfig.set("home", home.getFullLoc());
        this.home = home;
        saveConfig();
    }

    public Home getHome() {
        if(home == null)
            return new Home(plugin.getServer().getWorld("world").getSpawnLocation());
        Location loc = playerConfig.getLocation("home");
        this.home = new Home(loc);
        return home;
    }

    public String getNickname() {
        return nickname;
    }

}
