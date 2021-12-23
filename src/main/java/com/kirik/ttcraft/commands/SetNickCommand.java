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
        /*Scoreboard mainScoreboard = plugin.getServer().getScoreboardManager().getMainScoreboard();
        Team playerTeam = mainScoreboard.registerNewTeam("SLOT_" + plugin.getServer().getScoreboardManager().getMainScoreboard().getTeams().size());
        playerTeam.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS);
        playerTeam.setDisplayName(player_.getNickname());
        playerTeam.addEntry(player.getName());*/
        plugin.sendPlayerMessage(player, "Set nickname to " + newNickname);
        return true;
    }
}
