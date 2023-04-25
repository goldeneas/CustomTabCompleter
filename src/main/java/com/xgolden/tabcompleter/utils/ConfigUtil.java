package com.xgolden.tabcompleter.utils;

import java.io.File;

import com.xgolden.tabcompleter.Main;
import com.xgolden.tabcompleter.components.TextComponent;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtil {

    // Permissions
    public static String BYPASS_PERMISSION;
    public static String ADMIN_PERMISSION;

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
    public static TextComponent GROUP_CREATED;
    public static TextComponent GROUP_DELETED;
    public static TextComponent COULD_NOT_FIND_GROUP;

    static {
        reloadConfig();
    }

    public static void reloadConfig() {
        File file = new File(Main.getInstance().getDataFolder().getAbsolutePath() + "/config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        BYPASS_PERMISSION = config.getString("permission_bypass");
        ADMIN_PERMISSION = config.getString("permission_admin");

        PREFIX = config.getString("prefix");
        UPDATE_AVAILABLE = new TextComponent(config.getString("update_is_available"));
        FAKE_NO_PERMISSION = new TextComponent(config.getString("fake_no_permission_message"));
        NOT_ENOUGH_ARGUMENTS = new TextComponent(config.getString("not_enough_arguments"));
        NOT_ENOUGH_PERMISSIONS = new TextComponent(config.getString("not_enough_permissions"));
        UNKNOWN_ARGUMENT = new TextComponent(config.getString("unknown_command_argument"));
        NO_CONSOLE_USAGE = new TextComponent(config.getString("no_usage_from_console"));
        RELOADED_CONFIG = new TextComponent(config.getString("config_reloaded"));
        ADDED_COMMAND = new TextComponent(config.getString("command_added"));
        REMOVED_COMMAND = new TextComponent(config.getString("command_removed"));
        GROUP_CREATED = new TextComponent(config.getString("group_created"));
        GROUP_DELETED = new TextComponent(config.getString("group_deleted"));
        COULD_NOT_FIND_GROUP = new TextComponent(config.getString("could_not_find_group"));
    }
}
