package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("spawn")
@Help("Sends player to spawn")
@Usage("/spawn")
@Level(0)
public class SpawnCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {

        Location loc = plugin.getWorldSpawn();

        player.teleport(loc);
        plugin.sendPlayerMessage(player,"Returned to spawn!");
        return true;
        /*

        if(playerHelper.getPlayerNicknameByUUID(player.getUniqueId()).equals(""))
            plugin.sendServerMessage("\u00a77" + player.getName() + " \u00a7fhas returned to spawn!");
        else
            plugin.sendServerMessage(playerHelper.getPlayerNicknameByUUID(player.getUniqueId()) + "\u00a7f has returned to spawn!");*/
    }
}
