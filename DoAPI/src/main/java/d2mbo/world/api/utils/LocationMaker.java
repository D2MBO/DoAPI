package d2mbo.world.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationMaker {

    public String getString(final Location location) {
        return location.getWorld().getName()+":"+location.getX()+":"+location.getY()+":"+location.getZ()+":"+location.getYaw()+":"+location.getPitch();
    }
    public Location getLocation(final String string) {
        final String[] value = string.split(":");
        return new Location(Bukkit.getWorld(value[0]), Double.parseDouble(value[1]), Double.parseDouble(value[2]), Double.parseDouble(value[3]), Float.parseFloat(value[4]), Float.parseFloat(value[5]));
    }
}
