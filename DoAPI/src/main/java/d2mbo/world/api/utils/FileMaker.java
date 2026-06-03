package d2mbo.world.api.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class FileMaker extends YamlConfiguration {
    private JavaPlugin plugin;
    private String fileName;
    private File config;

    public FileMaker(final JavaPlugin plugin, final String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.config = new File(plugin.getDataFolder(), fileName);

        createFolder();
        createFile();
    }

    private void createFolder() {
        File parent = config.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
    }
    private void createFile() {
        if (!config.exists()) {
            try {
                plugin.saveResource(fileName, false);
            } catch (IllegalArgumentException e) {
                try {
                    config.createNewFile(); // fallback
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        reload();
    }
    public void reload() {
        try {
            super.load(config);
        } catch (Exception ex){ex.printStackTrace();}
    }

    public void save() {
        try {
            super.save(config);
        } catch (Exception ex){ex.printStackTrace();}
    }

}