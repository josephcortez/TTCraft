package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.main.util.TTCraftCommandException;
import com.kirik.ttcraft.player.TTPlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@ICommand.Name("Home")
@ICommand.Help("Teleports you to your home. If no home is set, teleports to spawn")
@ICommand.Usage("/home")
@ICommand.Level(0)
public class HomeCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        TTPlayer player_ = plugin.getOnlinePlayers().get(player.getUniqueId());
        player.teleport(player_.getHome().getFullLoc());
        plugin.sendPlayerMessage(player,"Teleported home!");
        return true;
    }
}
