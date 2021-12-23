package com.kirik.ttcraft.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.InternalStructure;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.reflect.IntEnum;
import com.comphenix.protocol.utility.MinecraftReflection;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.kirik.ttcraft.main.util.AbstractPacket;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Wrapper;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class WrapperPlayServerScoreboardTeam extends AbstractPacket {

    public static final PacketType TYPE = PacketType.Play.Server.SCOREBOARD_TEAM;
    private final InternalStructure internalPacket;

    public WrapperPlayServerScoreboardTeam() {
        super(new PacketContainer(TYPE), TYPE);
        handle.getModifier().writeDefaults();
        Optional<InternalStructure> optInternalPacket = this.getHandle().getOptionalStructures().read(0);
        internalPacket = optInternalPacket.get();
    }

    public WrapperPlayServerScoreboardTeam(PacketContainer packet) {
        super(packet, TYPE);
        Optional<InternalStructure> optInternalPacket = this.getHandle().getOptionalStructures().read(0);
        internalPacket = optInternalPacket.get();
    }

    /**
     * Enum containing all known modes
     *
     * @author dmulloy2
     * @editor kirik
     */
    public static class Mode extends IntEnum {
        public static final int CREATE = 0;
        public static final int DESTROY = 1;
        public static final int UPDATE_INFO = 2;
        public static final int ADD_PLAYERS = 3;
        public static final int REMOVE_PLAYERS = 4;

        private static final Mode INSTANCE = new Mode();

        public static Mode getInstance() {
            return INSTANCE;
        }
    }

    @Override
    public void sendPacket(Player receiver) {
         this.getHandle().getOptionalStructures().write(0, Optional.of(this.internalPacket));
         super.sendPacket(receiver);
    }

    /**
     * Retrieve Team Name.
     *
     * Notes: a unique name for the team. (Shared w scoreboard)
     *
     * @return current team name
     */
    public String getName() {
        return handle.getStrings().read(0);
    }

    /**
     * Set Team Name.
     *
     * @param value - new value
     */
    public void setName(String value) {
        handle.getStrings().write(0, value);
    }

    /**
     * Retrieve Team Display Name
     *
     * Notes: only if Mode = 0 or 2
     *
     * @return the current team display name
     */
    public WrappedChatComponent getDisplayName() {
        return internalPacket.getChatComponents().read(0);
    }

    /**
     * Set Team Display Name
     *
     * @param value - new value
     */
    public void setDisplayName(WrappedChatComponent value) {
        internalPacket.getChatComponents().write(0, value);
    }

    /**
     * Retrieve Team Prefix
     *
     * Notes: Only if Mode = 0 or 2. Displayed before the player' name that are part of the team
     *
     * @return the current team prefix
     */
    public WrappedChatComponent getPrefix() {
        return internalPacket.getChatComponents().read(1);
    }

    /**
     * Set Team Prefix
     *
     * @param value - new value
     */
    public void setPrefix(WrappedChatComponent value) {
        internalPacket.getChatComponents().write(1, value);
    }

    /**
     * Retrieve Team Suffix
     *
     * Notes: Only if Mode = 0 or 2. Displayed before the player' name that are part of the team
     *
     * @return the current team suffix
     */
    public WrappedChatComponent getSuffix() {
        return internalPacket.getChatComponents().read(2);
    }

    /**
     * Set Team Prefix
     *
     * @param value - new value
     */
    public void setSuffix(WrappedChatComponent value) {
        internalPacket.getChatComponents().write(2, value);
    }

    public String getNameTagVisibility() {
        return internalPacket.getStrings().read(0);
    }
    public void setNameTagVisibility(String value) {
        internalPacket.getStrings().write(0, value);
    }

    public ChatColor getColor() {
        return internalPacket.getEnumModifier(ChatColor.class, MinecraftReflection.getMinecraftClass("EnumChatFormat")).read(0);
    }
    public void setColor(ChatColor value) {
        internalPacket.getEnumModifier(ChatColor.class, MinecraftReflection.getMinecraftClass("EnumChatFormat")).write(0, value);
    }

    public String getCollisionRule() {
        return internalPacket.getStrings().read(1);
    }
    public void setCollisionRule(String value) {
        internalPacket.getStrings().write(1, value);
    }

    public int getEntityCount() {
        return internalPacket.getIntegers().read(0);
    }
    public void setEntityCount(int value) {
        internalPacket.getIntegers().write(0, value);
    }

    @SuppressWarnings("unchecked")
    public List<String> getPlayers() {
        return (List<String>)handle.getSpecificModifier(Collection.class).read(0);
    }
    public void setPlayers(List<String> players) {
        handle.getSpecificModifier(Collection.class).write(0, players);
    }

    public int getMode() {
        return handle.getIntegers().read(0);
    }
    public void setMode(int value) {
        handle.getIntegers().write(0, value);
    }

    /**
     * Retrieve pack option data. Pack data can be calculated as follows:
     *
     * <pre>
     *     <code>
     *         int data = 0;
     *         if(team.allowFriendlyFire()) {
     *             data |= 1;
     *         }
     *         if(team.canSeeFriendlyInvisible()) {
     *             data |= 2;
     *         }
     *     </code>
     * </pre>
     *
     * @return the current pack option data
     */
    public int getPackOptionData() {
        return internalPacket.getIntegers().read(1);
    }
    public void setPackOptionData(int value) {
        internalPacket.getIntegers().write(1, value);
    }
}
