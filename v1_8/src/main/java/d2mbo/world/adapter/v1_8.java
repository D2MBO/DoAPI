package d2mbo.world.adapter;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;

public final class v1_8 implements VersionAdapter {
    @Override
    public String toColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    @Override
    public int sidebarSize() {
        return 16;
    }

    @Override
    public void setNameTab(Player player, String prefix, String suffix) {
        EntityPlayer handle = ((CraftPlayer) player).getHandle();

        String name = prefix + player.getName() + suffix;

        PacketPlayOutPlayerInfo remove = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER,
                handle
        );

        IChatBaseComponent component = CraftChatMessage.fromString(name)[0];
        handle.listName = component;

        PacketPlayOutPlayerInfo add = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,
                handle
        );

        // Modificar display name (requiere reflexión o wrapper tipo ProtocolLib)
        for (Player online : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) online).getHandle().playerConnection.sendPacket(remove);
            ((CraftPlayer) online).getHandle().playerConnection.sendPacket(add);
        }

    }
}
