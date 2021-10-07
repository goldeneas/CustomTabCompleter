package com.xgolden.tabcompleter.utils;

import java.io.File;

import com.xgolden.tabcompleter.Main;
import com.xgolden.tabcompleter.components.TextComponent;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtil {
    
    // Settings
    public static boolean shouldAutomaticallyRemoveCommands;
    public static boolean shouldFakeNoPermission;

    // Permissions
    public static String bypassPermission;
    public static String reloadPermission;
    public static String adminPermission;

    // Feedback
    public static String PREFIX;
    public static TextComponent UPDATE_AVAILABLE;
    public static TextComponent FAKE_NO_PERMISSION;
    public static TextComponent NOT_ENOUGH_PERMISSIONS;
    public static TextComponent NOT_ENOUGH_ARGUMENTS;
    public static TextComponent UNKNOWN_ARGUMENT;
    public static TextComponent NO_CONSOLE_USAGE;
    public static TextComponent RELOADED_CONFIG;
    public static TextComponent ADDED_COMMAND;
    public static TextComponent REMOVED_COMMAND;

    static {
        reloadConfig();
    }

    public static void reloadConfig() {
        File file = new File(Main.getInstance().getDataFolder().getAbsolutePath() + "/config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        shouldAutomaticallyRemoveCommands = config.getBoolean("automatically_remove_no_permission_commands");
        shouldFakeNoPermission = config.getBoolean("fake_no_permission_when_using_blacklisted_command");

        bypassPermission = config.getString("permission_bypass_tab_filtering");
        reloadPermission = config.getString("permission_reload_config");
        adminPermission = config.getString("permission_admin");

        PREFIX = config.getString("prefix");
        UPDATE_AVAILABLE = new TextComponent(config.getString("update_is_available"));
        FAKE_NO_PERMISSION = new TextComponent(config.getString("fake_no_permission_message"));
        NOT_ENOUGH_ARGUMENTS = new TextComponent(config.getString("not_enough_arguments"));
        NOT_ENOUGH_PERMISSIONS = new TextComponent(config.getString("not_enough_permissions"));
        UNKNOWN_ARGUMENT = new TextComponent(config.getString("unknown_command_argument"));
        NO_CONSOLE_USAGE = new TextComponent(config.getString("no_usage_from_console"));
        RELOADED_CONFIG = new TextComponent(config.getString("successfully_reloaded_config"));
        ADDED_COMMAND = new TextComponent(config.getString("succesfully_added_command_to_list"));
        REMOVED_COMMAND = new TextComponent(config.getString("succesfully_removed_command_to_list"));
    }

    public static void set(String path, Object value) {
        Main plugin = Main.getInstance();
        plugin.getConfig().set(path, value);
        plugin.saveConfig();
    }

}
