package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.*;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import com.kirik.ttcraft.player.TTPlayer;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

@Name("setnick")
@Help("Sets your nickname")
@Usage("/setnick <nickname>")
@Level(0)
public class SetNickCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        TTPlayer player_ = plugin.onlinePlayers.get(player.getUniqueId());
        String newNickname = args[0];
        if(newNickname.equalsIgnoreCase("reset")) {
            newNickname = "";
        }
        newNickname = newNickname.replace("$", "\u00a7");
        player_.setNickname(newNickname);
        player.setPlayerListName(newNickname);
        player.setDisplayName(newNickname);
        plugin.sendPlayerMessage(player, "Set nickname to " + newNickname);
        return true;
    }
}
