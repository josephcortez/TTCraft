package com.kirik.ttcraft.commands;

import com.kirik.ttcraft.main.TTCraft;
import com.kirik.ttcraft.main.util.TTCraftCommandException;
import com.kirik.ttcraft.main.util.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.List;

public abstract class ICommand implements CommandExecutor {

    @Retention(RetentionPolicy.RUNTIME) public @interface Name { String value(); }
    @Retention(RetentionPolicy.RUNTIME) public @interface Help { String value(); }
    @Retention(RetentionPolicy.RUNTIME) public @interface Usage { String value(); }
    @Retention(RetentionPolicy.RUNTIME) public @interface Level { int value(); }

    protected static TTCraft plugin = TTCraft.instance;

    @Override
    public final boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        try {
            return onCommandAll(commandSender, command, s, args);
        }catch(Exception e){
            plugin.sendConsoleError("Error: "+e.getMessage());
            return false;
        }
    }

    public boolean onCommandAll(CommandSender commandSender, Command command, String s, String[] args) throws TTCraftCommandException {
        if(commandSender instanceof Player)
            return onCommandPlayer((Player)commandSender, command, s, args);
        else
            return onCommandConsole(commandSender, command, s, args);
    }

    public boolean onCommandConsole(CommandSender commandSender, Command command, String s, String[] args) throws TTCraftCommandException {
        plugin.sendConsoleError("Sorry, this command cannot be used by the console");
        return true;
    }

    public boolean onCommandPlayer(Player player, Command command, String s, String[] args) throws TTCraftCommandException {
        plugin.sendPlayerMessage(player, "Sorry, this command cannot be used by a player");
        return true;
    }

    public static void registerCommands() {
        List<Class<? extends ICommand>> commands = Utils.getSubClasses(ICommand.class);
        for(Class<? extends ICommand> command : commands) {
            registerCommand(command);
        }
    }

    private static void registerCommand(Class<? extends ICommand> commandClass) {
        try {
            Constructor<? extends ICommand> ctor = commandClass.getConstructor();
            ICommand command = ctor.newInstance();
            if(!commandClass.isAnnotationPresent(Name.class))
                return;
            plugin.getCommand(commandClass.getAnnotation(Name.class).value()).setExecutor(command);
        }catch(Exception ignored){}
    }

    /*public boolean canPlayerUseCommand(CommandSender commandSender) {
        final int playerLevel = playerHelper.getPlayerLevel(commandSender);
        final int requiredLevel = getRequiredLevel();

        return playerLevel >= requiredLevel;
    }*/

    public String getName() {
        final Name nameAnnotation = this.getClass().getAnnotation(Name.class);
        if(nameAnnotation == null)
            return "";

        return nameAnnotation.value();
    }

    public final String getHelp() {
        final Help helpAnnotation = this.getClass().getAnnotation(Help.class);
        if(helpAnnotation == null)
            return "";
        return helpAnnotation.value();
    }

    public final String getUsage() {
        final Usage usageAnnotation = this.getClass().getAnnotation(Usage.class);
        if(usageAnnotation == null)
            return "";
        return usageAnnotation.value();
    }

    public final int getRequiredLevel() {
        final Level levelAnnotation = this.getClass().getAnnotation(Level.class);
        if(levelAnnotation == null)
            throw new UnsupportedOperationException("You need either a GetMinLevel method or an @Level annotation");
        return levelAnnotation.value();
    }

}
