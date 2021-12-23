package com.kirik.ttcraft.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.PacketConstructor;
import com.comphenix.protocol.reflect.IntEnum;
import com.kirik.ttcraft.main.util.AbstractPacket;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class WrapperPlayServerSpawnEntity extends AbstractPacket {

    public static final PacketType TYPE = PacketType.Play.Server.SPAWN_ENTITY;
    private static PacketConstructor entityConstructor;

    public static class ObjectTypes extends IntEnum {
        public static final int BOAT = 1;
        public static final int ITEM_STACK = 2;
        public static final int AREA_EFFECT_CLOUD = 3;
        public static final int MINECART = 10;
        public static final int ACTIVATED_TNT = 50;
        public static final int ENDER_CRYSTAL = 51;
        public static final int TIPPED_ARROW_PROJECTILE = 60;
        public static final int SNOWBALL_PROJECTILE = 61;
        public static final int EGG_PROJECTILE = 62;
        public static final int GHAST_FIREBALL = 63;
        public static final int BLAZE_FIREBALL = 64;
        public static final int THROWN_ENDERPEARL = 65;
        public static final int WITHER_SKULL_PROJECTILE = 66;
        public static final int SHULKER_BULLET = 67;
        public static final int FALLING_BLOCK = 70;
        public static final int ITEM_FRAME = 71;
        public static final int EYE_OF_ENDER = 72;
        public static final int THROWN_POTION = 73;
        public static final int THROWN_EXP_BOTTLE = 75;
        public static final int FIREWORK_ROCKET = 76;
        public static final int LEASH_KNOT = 77;
        public static final int ARMORSTAND = 78;
        public static final int FISHING_FLOAT = 90;
        public static final int SPECTRAL_ARROW = 91;
        public static final int DRAGON_FIREBALL = 93;
        public static final int LIGHTNING_BOLT = 11; // probably wont work

        private static ObjectTypes INSTANCE = new ObjectTypes();

        public static ObjectTypes getInstance() {
            return INSTANCE;
        }
    }

    public WrapperPlayServerSpawnEntity() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
    }

    public WrapperPlayServerSpawnEntity(PacketContainer packet) {
        super(packet, TYPE);
    }

    public WrapperPlayServerSpawnEntity(Entity entity, int type, int objectData) {
        super(fromEntity(entity, type, objectData), TYPE);
    }

    private static PacketContainer fromEntity(Entity entity, int type, int objectData) {
        if(entityConstructor == null)
            entityConstructor = ProtocolLibrary.getProtocolManager().createPacketConstructor(TYPE, entity, type, objectData);
        return entityConstructor.createPacket(entity, type, objectData);
    }

    public int getEntityID() {
        return handle.getIntegers().read(0);
    }

    public Entity getEntity(World world) {
        return handle.getEntityModifier(world).read(0);
    }

    public Entity getEntity(PacketEvent event) {
        return getEntity(event.getPlayer().getWorld());
    }

    public void setEntityID(int value) {
        handle.getIntegers().write(0, value);
    }

    public UUID getUniqueID() {
        return handle.getUUIDs().read(0);
    }

    public void setUniqueID(UUID value) {
        handle.getUUIDs().write(0, value);
    }

    public double getX() {
        return handle.getDoubles().read(0);
    }

    public void setX(double value) {
        handle.getDoubles().write(0, value);
    }

    public double getY() {
        return handle.getDoubles().read(1);
    }

    public void setY(double value) {
        handle.getDoubles().write(1, value);
    }

    public double getZ() {
        return handle.getDoubles().read(0);
    }

    public void setZ(double value) {
        handle.getDoubles().write(2, value);
    }

    // Never case about these in most cases, can leave default
   /* public double getOptionalSpeedX() {
        return handle.getIntegers().read(1);
    }

    public void setOptionalSpeedX(double value) {
        handle.getIntegers().write(1, (int)(value * 8000.0D));
    }*/

    public float getPitch() {
        return (handle.getIntegers().read(4) * 360.0f) / 256.0f;
    }

    public void setPitch(int value) {
        handle.getIntegers().write(4, (int)(value * 256.0f / 360.0f));
    }

    public float getYaw() {
        return (handle.getIntegers().read(5) * 360.0f) / 256.0f;
    }

    public void setYaw(int value) {
        handle.getIntegers().write(5, (int)(value * 256.0f / 360.0f));
    }

    public EntityType getType() {
        return handle.getEntityTypeModifier().read(0);
    }

    public void setType(EntityType value) {
        handle.getEntityTypeModifier().write(0, value);
    }

    public int getObjectData() {
        return handle.getIntegers().read(6);
    }

    public void setObjectData(int value) {
        handle.getIntegers().write(6, value);
    }
}
