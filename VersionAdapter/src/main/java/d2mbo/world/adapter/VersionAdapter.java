package d2mbo.world.adapter;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public interface VersionAdapter {
    default ItemStack getItemHand(final Player player) {
        return null;
    }
    default void setItemHand(final Player player, final ItemStack itemStack) {}
    default String toColor(final String text){ return text;}
    default int getSkullShort(){return 0;}
    default void setSkullOwner(final SkullMeta meta, final String owner){}
    default String getSkull() {return null;}
    default int sidebarSize() {return 16;}
    default void setNameTab(final Player player, final String prefix, final String suffix) {}
    default void setNameTag(final Player player, final String prefix, final String suffix) {}
}
