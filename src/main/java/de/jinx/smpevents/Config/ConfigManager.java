package de.jinx.smpevents.Config;

import de.jinx.smpevents.SMPEvents;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    File progressFile;
    FileConfiguration progressCfg;

    public ConfigManager() {
        progressFile = new File(SMPEvents.getPlugin().getDataFolder() + File.separator + "progress.yml");
        progressCfg = YamlConfiguration.loadConfiguration(progressFile);
    }

    public void reload(){
        progressFile = new File(SMPEvents.getPlugin().getDataFolder() + File.separator + "progress.yml");
        progressCfg = YamlConfiguration.loadConfiguration(progressFile);
    }

    public File getProgressFile() {
        return progressFile;
    }

    public FileConfiguration getProgressCfg() {
        return progressCfg;
    }

    public void save() {
        try {
            progressCfg.save(progressFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
