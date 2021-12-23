package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.io.File;

@Name("setspawn")
@Help("Sets spawn of world")
@Usage("/setspawn")
@Level(2)
public class SetSpawnCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

        Location newSpawn = player.getLocation();

        /*plugin.getServer().getWorld("world").setSpawnLocation(newSpawn);*/

        plugin.getConfig().set("spawn", newSpawn);
        plugin.saveConfig();
        plugin.sendPlayerMessage(player, "Set world spawn");
        return true;
        /*

        if(playerHelper.getPlayerNicknameByUUID(player.getUniqueId()).equals(""))
            plugin.sendServerMessage("\u00a77" + player.getName() + " \u00a7fhas returned to spawn!");
        else
            plugin.sendServerMessage(playerHelper.getPlayerNicknameByUUID(player.getUniqueId()) + "\u00a7f has returned to spawn!");*/
    }
}
