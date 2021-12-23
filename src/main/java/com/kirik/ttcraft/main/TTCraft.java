package com.kirik.ttcraft.main;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.kirik.ttcraft.commands.ICommand;
import com.kirik.ttcraft.events.PlayerListener;
import com.kirik.ttcraft.player.TTPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;

/**
 * TTCraft is a custom plugin for basic
 * commands for the TT Minecraft server
 * @author kirik
 */

public class TTCraft extends JavaPlugin {

    public static TTCraft instance;

    // Dependencies
    private ProtocolManager protocolManager;
    /*private HologramManager hologramManager; // Took code from https://github.com/sainttx/Holograms and updated to 1.18.1
    private HologramEntityController hologramController;
    private Runnable updateTask = new HologramUpdateTask(this);*/

    /*public PlayerHelper playerHelper = new PlayerHelper(this);*/

    public HashMap<UUID, TTPlayer> onlinePlayers = new HashMap<>();

    public TTCraft(){
        instance = this;
    }

    @Override
    public void onEnable(){
        /*log("Plugin enabled");*/
        /*config = new Configuration(getDataFolder());*/
        /*getCommand("ping").setExecutor(commandHandler);
        getCommand("spawn").setExecutor(commandHandler);
        getCommand("setnick").setExecutor(commandHandler);
        getCommand("setspawn").setExecutor(commandHandler);*/

        sendConsoleMsg("Loading Dependencies...");
        protocolManager = ProtocolLibrary.getProtocolManager();
        /*hologramManager = HologramLibrary.getHologramManager();*/
        sendConsoleMsg("Dependencies Loaded.");

        sendConsoleMsg("Loading Config Defaults...");
        if(getWorldSpawn() == null) {
            setDefaultWorldSpawn();
        }else{
            getServer().getWorld("world").setSpawnLocation(getWorldSpawn());
            saveConfig();
        }
        if(getMOTD() == null) {
            setDefaultMOTD();
        }
        sendConsoleMsg("Config Defaults Loaded.");

        sendConsoleMsg("Loading Commands...");
        ICommand.registerCommands();
        StateContainer.loadAll();
        sendConsoleMsg("Commands Loaded.");

        sendConsoleMsg("Loading Listeners...");
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        sendConsoleMsg("Listeners Loaded.");

        sendConsoleMsg("Plugin enabled");
    }

    @Override
    public void onDisable() {
        /*log("Plugin disabled");*/
        sendConsoleMsg("Plugin disabled");
        saveConfig();
        /*saveDefaultConfig();*/ // dont think this is needed
    }

    public String getMOTD() {
        return getConfig().getString("MOTD").replace("$", "\u00a7");
    }

    public void setDefaultMOTD() {
        getConfig().set("MOTD", "Welcome back to $4TT$6MC$f!");
        saveConfig();
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }

    //TODO WorldHelper?
    public Location getWorldSpawn() {
        return getConfig().getLocation("spawn");
    }

    public void setDefaultWorldSpawn() {
        getConfig().set("spawn", getServer().getWorld("world").getSpawnLocation());
        saveConfig();
    }

    public void log(String msg) {
        log(Level.INFO, msg);
    }

    public void log(Level level, String msg) {
        getLogger().log(level, msg);
    }

    public void sendServerMessage(String msg) {
        msg = "\u00a75[TT]\u00a7f " + msg;
        this.getServer().broadcastMessage(msg);
    }

    public void sendConsoleMsg(String msg) {
        msg = "\u00a75[TT]\u00a7f " + msg;
        log(msg);
    }

    public void sendConsoleError(String msg){
        msg = "\u00a74[TT]\u00a7f " + msg;
        log(msg);
    }

    public void sendPlayerMessage(Player target, String msg) {
        target.sendMessage("\u00a75[TT] \u00a7f" + msg);
    }

    /*public void sendServerMessage(String msg){
        getServer().broadcastMessage("\u00a75[TT] \u00a7f" + msg);
    }*/
}
