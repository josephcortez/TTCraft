/*
package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.main.TTCraft;
import com.kirik.ttcraft.main.util.PermissionDeniedException;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import com.kirik.ttcraft.main.util.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandSystem {

    private final TTCraft plugin;
    private final Map<String, ICommand> commands = new HashMap<>();

    public CommandSystem(TTCraft plugin){
        this.plugin = plugin;
        plugin.commandSystem = this;
        scanCommands();
    }

    public void scanCommands(){
        commands.clear();
        //add for every package that commands are made in
        scanCommands("com.kirik.ttcraft.main.commands");
    }

    public void scanCommands(String packageName){
        for(Class<? extends ICommand> commandClass : Utils.getSubClasses(ICommand.class, packageName)) {
            try {
                commandClass.newInstance();
                plugin.sendConsoleMsg("Found command: " + commandClass.getName());
            }catch(InstantiationException e){
                continue;
            }catch(IllegalAccessException e){
                continue;
            }catch(Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    public void registerCommand(String name, ICommand command) {
        commands.put(name, command);
    }

    public Map<String, ICommand> getCommands() {
        return commands;
    }

    public boolean runCommand(CommandSender commandSender, String cmd, String argStr) {
        String args[];
        if(argStr.isEmpty()){
            args = new String[0];
        }else{
            args = argStr.split(" +");
        }
        return runCommand(commandSender, cmd, args, argStr);
    }

    public boolean runCommand(CommandSender commandSender, String cmd, String[] args, String argStr) {
        if(commands.containsKey(cmd)) {
            final String playerName = commandSender.getName();
            final ICommand icmd = commands.get(cmd);
            try {
                String logmsg = "TT Command: " + playerName + ": " + cmd + " " + argStr;
                plugin.log(logmsg);
                icmd.run(commandSender, args, argStr, cmd);
            }catch(PermissionDeniedException e) {
                String logmsg = "Command denied: " + playerName + ": " + cmd + " " + argStr;
                plugin.log(logmsg);

                plugin.sendPlayerMessage((Player)commandSender, e.getMessage());
            }catch(TTCraftCommandException e){
                plugin.sendPlayerMessage((Player)commandSender, e.getMessage());
            }catch(Exception e) {
                plugin.sendPlayerMessage((Player)commandSender, "Command error!");
            }
            return true;
        }
        return false;
    }

    public boolean runCommand(CommandSender commandSender, String baseCmd) {
        int posSpace = baseCmd.indexOf(' ');
        String cmd;
        String argStr;
        if(posSpace < 0) {
            cmd = baseCmd.toLowerCase();
            argStr = "";
        }else{
            cmd = baseCmd.substring(0, posSpace).trim().toLowerCase();
            argStr = baseCmd.substring(posSpace).trim();
        }
        return runCommand(commandSender, cmd, argStr);
    }
}
*/
