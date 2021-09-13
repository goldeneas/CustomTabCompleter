package com.xgolden.tabcompleter.events;

import com.xgolden.tabcompleter.utils.ChatUtil;
import com.xgolden.tabcompleter.utils.ConfigUtil;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEvent implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            // We cannot use ChatUtil because we have to prevent a cast to player for the ConsoleSender.
            // It would otherwise result in an error in console.

            // TODO: Implement usage from console.
            sender.sendMessage(ConfigUtil.NO_CONSOLE_USAGE.getString());
            return true;
        }

        if (args.length == 0) {
            ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_ARGUMENTS);
            return true;
        }

        switch (args[0]) {
            case "reload":

                if(!sender.hasPermission(ConfigUtil.reloadPermission)) {
                    ChatUtil.sendMessage(sender, ConfigUtil.NOT_ENOUGH_PERMISSIONS);
                    return true;
                }
                
                ChatUtil.sendMessage(sender, ConfigUtil.RELOADED_CONFIG);
                ConfigUtil.reloadConfig();
                break;
            default:
                ChatUtil.sendMessage(sender, ConfigUtil.UNKNOWN_ARGUMENT);
                break;
        }

        return true;
    }

}
