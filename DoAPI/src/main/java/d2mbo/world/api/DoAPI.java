package d2mbo.world.api;

import d2mbo.world.adapter.VersionAdapter;
import d2mbo.world.adapter.v1_21;
import d2mbo.world.adapter.v1_8;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DoAPI extends JavaPlugin {
    private static DoAPI main;
    @Getter private VersionAdapter version;

    @Override
    public void onEnable() {
        main = this;
        try {
            if(Bukkit.getVersion().contains("1.8") ||
                Bukkit.getVersion().contains("1.9") ||
                Bukkit.getVersion().contains("1.10") ||
                Bukkit.getVersion().contains("1.11") ||
                Bukkit.getVersion().contains("1.12")) {
                version = new v1_8();
            } else version = new v1_21();
        } catch (Exception e) {version = new v1_21();}

    }

    @Override
    public void onDisable() {
        main = null;
    }


    public static DoAPI get(){return main;}
}