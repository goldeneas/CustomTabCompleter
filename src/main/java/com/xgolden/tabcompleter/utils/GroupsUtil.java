package com.xgolden.tabcompleter.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.xgolden.tabcompleter.Main;
import org.bukkit.configuration.file.FileConfiguration;

public class GroupsUtil extends CustomConfig {

    private static final String GROUPS_PATH = "groups";

    private static Main instance = Main.getInstance();
    private static FileConfiguration config = getCustomConfig("groups.yml");

    public static boolean createGroup(String groupName, String permission) {
        if(isStringNullOrEmpty(groupName) && isStringNullOrEmpty(permission))
            return false;

        config.set(GROUPS_PATH +"."+ groupName +"."+ "permission", permission);
        config.set(GROUPS_PATH +"."+ groupName +"."+ "blacklist", true);
        config.set(GROUPS_PATH +"."+ groupName +"."+ "fake_no_permission", true);
        config.set(GROUPS_PATH +"."+ groupName +"."+ "commands", Arrays.asList("command", "command2"));
        saveConfig();

        return true;
    }

    public static void deleteGroup(String groupName) {
        config.set(GROUPS_PATH +"."+ groupName, null);
        saveConfig();
    }

    public static void addCommandToGroup(String groupName, String command) {
        List<String> _currentCommands = getCommandsForGroup(groupName);
        _currentCommands.add(command);

        config.set(GROUPS_PATH +"."+ groupName +"."+ "commands", _currentCommands);
        saveConfig();
    }

    public static void removeCommandFromGroup(String groupName, String command) {
        List<String> _currentCommands = getCommandsForGroup(command);
        _currentCommands.remove(groupName);

        config.set(GROUPS_PATH +"."+ command +"."+ "commands", _currentCommands);
        saveConfig();
    }

    public static void setBlacklist(String groupName, boolean b) {
        config.set(GROUPS_PATH +"."+ groupName +"."+ "blacklist", b);
        saveConfig();
    }

    public static boolean isGroupBlacklist(String groupName) {
        return config.getBoolean(GROUPS_PATH +"."+ groupName +"."+ "blacklist");
    }

    public static boolean doesGroupExist(String groupName) {
        return config.getString(GROUPS_PATH +"."+ groupName +"."+ "permission") != null;
    }

    public static boolean shouldGroupFakePermission(String groupName, boolean b) {
        return config.getBoolean(GROUPS_PATH +"."+ groupName +"."+ "fake_no_permission");
    }

    public static Set<String> getGroupNames() {
        return config.getConfigurationSection(GROUPS_PATH).getKeys(false);
    }

    public static String getPermissionForGroup(String groupName) {
        return config.getString(GROUPS_PATH +"."+ groupName +"."+ "permission");
    }

    public static List<String> getCommandsForGroup(String groupName) {
        return config.getStringList(GROUPS_PATH +"."+ groupName +"."+ "commands");
    }

    private static boolean isStringNullOrEmpty(String s) {
        return (s == null || s.trim().isEmpty());
    }

    public static void reloadConfig() {
        config = getCustomConfig("groups.yml");
    }

}
