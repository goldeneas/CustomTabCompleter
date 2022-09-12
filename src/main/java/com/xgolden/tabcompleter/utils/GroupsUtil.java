package com.xgolden.tabcompleter.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;

public class GroupsUtil extends CustomConfig {

    private static final String GROUPS_PATH = "groups";

    private static FileConfiguration config = getCustomConfig("groups.yml");

    // static {
    //     saveConfig();
    // }

    public static void createGroupWithPermission(String permission) {
        config.set(GROUPS_PATH +"."+ permission +"."+ "is_list_whitelist", false);
        config.set(GROUPS_PATH +"."+ permission +"."+ "commands", Arrays.asList("default_command", "anothercommand"));
        saveConfig();
    }

    public static void deleteGroupWithPermission(String permission) {
        config.set(GROUPS_PATH +"."+ permission, null);
        saveConfig();
    }

    public static void addCommandToGroup(String command, String permission) {
        List<String> _currentCommands = getCommandsForGroup(permission);
        _currentCommands.add(command);

        config.set(GROUPS_PATH +"."+ permission +"."+ "commands", _currentCommands);
        saveConfig();
    }

    public static void removeCommandFromGroup(String command, String permission) {
        List<String> _currentCommands = getCommandsForGroup(permission);
        _currentCommands.remove(command);

        config.set(GROUPS_PATH +"."+ permission +"."+ "commands", _currentCommands);
        saveConfig();
    }

    public static void setToWhitelist(String permission, boolean bool) {
        config.set(GROUPS_PATH +"."+ permission +"."+ "is_list_whitelist", bool);
        saveConfig();
    }

    public static boolean isWhitelist(String permission) {
        return config.getBoolean(GROUPS_PATH +"."+ permission +"."+ "is_list_whitelist");
    }

    public static Set<String> getGroups() {
        return config.getConfigurationSection(GROUPS_PATH).getKeys(false);
    }

    public static List<String> getCommandsForGroup(String permission) {
        return config.getStringList(GROUPS_PATH +"."+ permission +"."+ "commands");
    }

}
