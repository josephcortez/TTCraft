package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.main.TTCraft;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import com.kirik.ttcraft.player.Home;
import com.kirik.ttcraft.player.TTPlayer;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@ICommand.Name("sethome")
@ICommand.Help("Sets your home")
@ICommand.Usage("/sethome")
@ICommand.Level(0)
public class SetHomeCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        TTPlayer player_ = plugin.onlinePlayers.get(player.getUniqueId());
        Location newHome = player.getLocation();
        player_.setHome(new Home(newHome));
        plugin.sendPlayerMessage(player, "Set home!");
        return true;
    }
}
