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

        if (args.length == 0) {
            ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
            return true;
        }

        switch (args[0]) {

            case "reload":
                if (!sender.hasPermission(ConfigUtil.reloadPermission)) {
                    ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_PERMISSIONS);
                    return true;
                }

                ChatUtil.sendMessage(sender, ConfigUtil.RELOADED_CONFIG);
                ConfigUtil.reloadConfig();
                break;

            case "create":
                if (args.length < 2) {
                    ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
                    return true;
                }

                // We avoid using dots since they will cause another section in the yml file to
                // be created.
                // tabcompleter.helper will create a tabcompleter section and under it an helper
                // one.
                GroupsUtil.createGroupWithPermission(args[1].replace('.', '_'));
                ChatUtil.sendMessage(sender,
                        "Successfully created group with permission: " + args[1].replace('.', '_'));
                break;

            case "delete":
                if (args.length < 2) {
                    ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
                    return true;
                }

                GroupsUtil.deleteGroupWithPermission(args[1].replace('.', '_'));
                ChatUtil.sendMessage(sender,
                        "Successfully deleted group with permission: " + args[1].replace('.', '_'));
                break;

            case "add":
                if (args.length < 3) {
                    ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
                    return true;
                }

                // args[1] is the command.
                // args[2] is the group being targeted.
                GroupsUtil.addCommandToGroup(args[1], args[2]);
                break;

            case "remove":
                if (args.length < 3) {
                    ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
                    return true;
                }

                // args[1] is the command.
                // args[2] is the group being targeted.
                GroupsUtil.removeCommandFromGroup(args[1], args[2]);
                break;

            default:
                ChatUtil.sendMessage(sender, ConfigUtil.UNKNOWN_ARGUMENT);
                break;
        }

        return true;
    }

}
