package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.commands.ICommand.Name;
import com.kirik.ttcraft.commands.ICommand.Help;
import com.kirik.ttcraft.commands.ICommand.Usage;
import com.kirik.ttcraft.commands.ICommand.Level;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

@Name("test")
@Help("Test command for new command system")
@Usage("/test")
@Level(0)
public class TestCommand extends ICommand {

    @Override
    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        /*scoreboard.setName("team_test1");
        scoreboard.setMode((byte)0);
        scoreboard.setDisplayName(WrappedChatComponent.fromText(args[0]));
        scoreboard.setColor(ChatColor.RED);
        scoreboard.setEntityCount(1);
        List<String> players = new ArrayList<String>();
        players.add(player.getName());
        scoreboard.setPlayers(players);
        scoreboard.setPrefix(WrappedChatComponent.fromText(args[1]));
        scoreboard.sendPacket(player);*/

        plugin.sendPlayerMessage(player, "Entity Spawned");
        return true;
    }
}
