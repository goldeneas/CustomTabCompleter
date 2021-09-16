package com.xgolden.tabcompleter.utils;

import java.io.File;
import java.io.IOException;

import com.xgolden.tabcompleter.Main;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class CustomConfig {

    private static File customConfigFile;
    private static FileConfiguration customConfig;

    private static Main plugin = Main.getInstance();

    public static FileConfiguration getCustomConfig(String configName) {
        if(customConfig == null) { createCustomConfigWithExtension(configName); }
        return customConfig;
    }

    protected static void createCustomConfigWithExtension(String configName) {
        customConfigFile = new File(plugin.getDataFolder(), configName);
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            plugin.saveResource(configName, false);
         }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    protected static void saveConfig() {
        try {
            customConfig.save(customConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
