/*package com.kirik.ttcraft.player;

import com.kirik.ttcraft.main.StateContainer;
import com.kirik.ttcraft.main.TTCraft;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class PlayerHelper extends StateContainer {

    private final TTCraft plugin;

    public PlayerHelper(TTCraft plugin) {
        this.plugin = plugin;
    }

    public Player getPlayerByUUID(UUID uuid){
        return Bukkit.getPlayer(uuid);
    }

    public Player getPlayerByName(String name) {
        return plugin.getServer().getPlayer(name);
    }

    public String getPlayerNicknameByUUID(UUID uuid){
        FileConfiguration playerConfig = getPlayerConfig(uuid);
        String nickname = (String) playerConfig.get("nickname");
        return nickname;
    }

    public int getPlayerLevel(CommandSender commandSender){
        if (commandSender instanceof Player) {
            FileConfiguration playerConfig = getPlayerConfig(((Player) commandSender).getUniqueId());
            int level = (int)playerConfig.get("level");
            return level;
        }
        return -1;
    }

    public FileConfiguration getPlayerConfig(UUID uuid){
        File playerConfigFile = new File(plugin.getDataFolder() + "/players/", uuid + ".yml");
        if(playerConfigFile.exists()) {
            plugin.sendConsoleMsg("Found file and loaded");
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

    private void loadPlayerDefaults(FileConfiguration playerConfig) {
        playerConfig.set("nickname", "");
        playerConfig.set("level", 1);
    }

    public File getPlayerConfigFile(UUID uuid){
        return new File(plugin.getDataFolder() + "/players/", uuid + ".yml");
    }


}*/
