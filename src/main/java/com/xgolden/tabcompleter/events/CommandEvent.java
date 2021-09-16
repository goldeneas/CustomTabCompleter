package com.xgolden.tabcompleter.events;

import java.util.List;

import com.xgolden.tabcompleter.utils.ChatUtil;
import com.xgolden.tabcompleter.utils.ConfigUtil;

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
            
            case "add":
                if (args.length < 2) {
                    ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
                    return true;
                }

                // Get current command list and add the new command
                // then set it as the new list in config.
                List<String> _currentAddList = ConfigUtil.commandsList;
                _currentAddList.add(args[1]);
                ConfigUtil.set("blacklisted_commands", _currentAddList);

                ChatUtil.sendMessage(sender, ConfigUtil.ADDED_COMMAND);
                break;
            
            case "remove":
                if (args.length < 2) {
                    ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
                    return true;
                }

                // Get current command list and remove the specified command
                // then set it as the new list in config.
                List<String> _currentRemoveList = ConfigUtil.commandsList;
                _currentRemoveList.remove(args[1]);
                ConfigUtil.set("blacklisted_commands", _currentRemoveList);

                ChatUtil.sendMessage(sender, ConfigUtil.REMOVED_COMMAND);
                break;

            default:
                ChatUtil.sendMessage(sender, ConfigUtil.UNKNOWN_ARGUMENT);
                break;
        }

        return true;
    }

}
