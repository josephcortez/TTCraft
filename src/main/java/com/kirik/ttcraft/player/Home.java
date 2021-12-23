package com.kirik.ttcraft.player;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Home {

    private final Location fullLoc;
    private final double x, y, z, pitch, yaw;

    public Home(Location location) {
        this.fullLoc = location;
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.pitch = location.getPitch();
        this.yaw = location.getYaw();
    }

    public Home(Player player) {
        this(player.getLocation());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getPitch() {
        return pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public Location getFullLoc() {
        return fullLoc;
    }
}
