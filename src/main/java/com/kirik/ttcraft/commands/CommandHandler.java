/*
package com.kirik.TTCraft.commands;

import com.kirik.TTCraft.main.TTCraft;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class CommandHandler implements CommandExecutor {

    public TTCraft plugin;

    public CommandHandler(TTCraft plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player) {
            Player player = (Player)commandSender;
            String nickname = getNickname(player.getUniqueId());

            if(command.getName().equalsIgnoreCase("ping")) {
                plugin.sendPlayerMessage(player, "pong!");
            }

            if(command.getName().equalsIgnoreCase("spawn")) {
                //Make sure overworld name is set to "world"
                World overworld = plugin.getServer().getWorld("world");
                double spawnX = (double)plugin.getConfig().get("spawn.x");
                double spawnY = (double)plugin.getConfig().get("spawn.y");
                double spawnZ = (double)plugin.getConfig().get("spawn.z");
                float spawnYaw = (float)plugin.getConfig().get("spawn.yaw");
                float spawnPitch = (float)plugin.getConfig().get("spawn.pitch");

                Location spawnLocation = new Location(overworld, spawnX, spawnY, spawnZ, spawnYaw, spawnPitch);
                */
/*commandSender.getServer().getWorld("world").getSpawnLocation();*//*

                player.teleport(spawnLocation);

                if(nickname.equals(""))
                    plugin.sendServerMessage("\u00a77" + player.getName() + " \u00a7fhas returned to spawn!");
                else
                    plugin.sendServerMessage(nickname + "\u00a7f has returned to spawn!");
            }

            if(command.getName().equalsIgnoreCase("setnick")) {
                String newNickname = args[0];
                if(newNickname.equalsIgnoreCase("reset")) {
                    newNickname = "";
                }
                newNickname = newNickname.replace("$", "\u00a7");
                File playerConfigFile = plugin.getPlayerConfigFile(player.getUniqueId());
                FileConfiguration playerConfig = plugin.getPlayerConfig(player.getUniqueId());
                playerConfig.set("nickname", newNickname);
                try {
                    playerConfig.save(playerConfigFile);
                }catch(Exception e){
                    e.printStackTrace();
                }
                plugin.sendPlayerMessage(player, "Set nickname to " + newNickname);
            }

            if(command.getName().equalsIgnoreCase("setspawn")) {
                Location newSpawn = player.getLocation();
                plugin.getConfig().set("spawn.x", newSpawn.getX());
                plugin.getConfig().set("spawn.y", newSpawn.getY());
                plugin.getConfig().set("spawn.z", newSpawn.getZ());
                plugin.getConfig().set("spawn.yaw", newSpawn.getYaw());
                plugin.getConfig().set("spawn.pitch", newSpawn.getPitch());
                File configFile = new File(plugin.getDataFolder(), "config.yml");
                try {
                    plugin.getConfig().save(configFile);
                }catch(Exception e){
                    e.printStackTrace();
                }
                plugin.sendPlayerMessage(player, "Set world spawn");
            }
        }else{ //console
            commandSender.sendMessage("Console cannot currently use commands!");
        }

        return true;
    }

    public String getNickname(UUID uuid){
        FileConfiguration playerConfig = plugin.getPlayerConfig(uuid);
        return (String)playerConfig.get("nickname");
    }
}
*/
