package com.xgolden.tabcompleter.events;

import com.xgolden.tabcompleter.utils.ChatUtil;
import com.xgolden.tabcompleter.utils.ConfigUtil;
import com.xgolden.tabcompleter.utils.GroupsUtil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandEvent implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int argsLength = args.length;
        if(argsLength == 0) {
            ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
            return true;
        }

        switch (args[0]) {
            case "reload":
                reloadConfig(sender);
                break;

            case "create":
                checkArgsLenght(sender, argsLength, 3);
                createGroup(sender, args[1], args[2]);
                break;

            case "delete":
                checkArgsLenght(sender, argsLength, 2);
                deleteGroup(sender, args[1]);
                break;

            case "addCommand":
                checkArgsLenght(sender, argsLength, 3);
                addCommand(sender, args[1], args[2]);
                break;

            case "removeCommand":
                checkArgsLenght(sender, argsLength, 3);
                removeCommand(sender, args[1], args[2]);
                break;

            default:
                unknownCommand(sender);
                break;
        }

        return true;
    }

    private void reloadConfig(CommandSender sender) {
        if(!isUserAdmin(sender))
            return;

        ConfigUtil.reloadConfig();
        ChatUtil.sendMessage(sender, ConfigUtil.RELOADED_CONFIG);
    }

    private void createGroup(CommandSender sender, String groupName, String basePermission) {
        if(!isUserAdmin(sender))
            return;

        GroupsUtil.createGroup(groupName, basePermission);
        ChatUtil.sendMessage(sender, ConfigUtil.GROUP_CREATED);
    }

    private void deleteGroup(CommandSender sender, String groupName) {
        if(!isUserAdmin(sender) || !doesGroupExist(sender, groupName))
            return;

        GroupsUtil.deleteGroup(groupName);
        ChatUtil.sendMessage(sender, ConfigUtil.GROUP_DELETED);
    }

    private void addCommand(CommandSender sender, String groupName, String command) {
        if(!isUserAdmin(sender) || !doesGroupExist(sender, groupName))
            return;

        GroupsUtil.addCommandToGroup(groupName, command);
        ChatUtil.sendMessage(sender, ConfigUtil.ADDED_COMMAND);
    }

    private void removeCommand(CommandSender sender, String groupName, String command) {
        if(!isUserAdmin(sender) || !doesGroupExist(sender, groupName))
            return;

        GroupsUtil.removeCommandFromGroup(groupName, command);
        ChatUtil.sendMessage(sender, ConfigUtil.REMOVED_COMMAND);
    }

    private void unknownCommand(CommandSender sender) {
        ChatUtil.sendMessage(sender, ConfigUtil.UNKNOWN_ARGUMENT);
    }

    private boolean isUserAdmin(CommandSender sender){
        String p = ConfigUtil.ADMIN_PERMISSION;
        boolean isAdmin = sender.hasPermission(p);

        if(!isAdmin)
            ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_PERMISSIONS);

        return isAdmin;
    }

    private boolean checkArgsLenght(CommandSender sender, int argsLenght, int requiredArgsLenght) {
        boolean isCheckPassed = argsLenght >= requiredArgsLenght;

        if(!isCheckPassed)
            ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);

        return isCheckPassed;
    }

    private boolean doesGroupExist(CommandSender sender, String groupName) {
        boolean doesExist = GroupsUtil.doesGroupExist(groupName);

        if(!doesExist)
            ChatUtil.sendMessage(sender, ConfigUtil.COULD_NOT_FIND_GROUP);

        return doesExist;
    }

}
