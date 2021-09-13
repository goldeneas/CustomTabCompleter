package com.xgolden.tabcompleter.utils;

import java.io.File;
import java.util.List;

import com.xgolden.tabcompleter.Main;
import com.xgolden.tabcompleter.components.TextComponent;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtil {
    
    // Settings
    public static boolean isListWhitelist;
    public static List<String> commandsList;

    // Permissions
    public static String bypassPermission;
    public static String reloadPermission;

    // Feedback
    public static String PREFIX;
    public static TextComponent NOT_ENOUGH_ARGUMENTS;
    public static TextComponent NOT_ENOUGH_PERMISSIONS;
    public static TextComponent UNKNOWN_ARGUMENT;
    public static TextComponent NO_CONSOLE_USAGE;
    public static TextComponent RELOADED_CONFIG;

    static {
        reloadConfig();
    }

    public static void reloadConfig() {
        File file = new File(Main.getInstance().getDataFolder().getAbsolutePath() + "/config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        isListWhitelist = config.getBoolean("whitelist_instead_of_blacklist");
        commandsList = config.getStringList("blacklisted_commands");

        bypassPermission = config.getString("permission_bypass_tab_filtering");
        reloadPermission = config.getString("permission_reload_config");

        PREFIX = config.getString("prefix");
        NOT_ENOUGH_ARGUMENTS = new TextComponent(config.getString("not_enough_arguments"));
        NOT_ENOUGH_PERMISSIONS = new TextComponent(config.getString("not_enough_permissions"));
        UNKNOWN_ARGUMENT = new TextComponent(config.getString("unknown_command_argument"));
        NO_CONSOLE_USAGE = new TextComponent(config.getString("no_usage_from_console"));
        RELOADED_CONFIG = new TextComponent(config.getString("successfully_reloaded_config"));
    }

}
