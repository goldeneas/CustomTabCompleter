package com.xgolden.tabcompleter.events;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.xgolden.tabcompleter.utils.ConfigUtil;
import com.xgolden.tabcompleter.utils.GroupsUtil;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CommandSendEvent implements Listener {

    @EventHandler
    public void onCommandSend(PlayerCommandSendEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(ConfigUtil.bypassPermission)) { return; }

        Collection<String> commands = event.getCommands();

        for(String groupPermission : GroupsUtil.getGroups()) {
            if(player.hasPermission(groupPermission)) {
                boolean isWhitelist = GroupsUtil.isWhitelist(groupPermission);
                List<String> _groupCommandsList = GroupsUtil.getCommandsForGroup(groupPermission);

                if(isWhitelist) {
                    commands.retainAll(_groupCommandsList);
                } else {
                    commands.removeAll(_groupCommandsList);
                }
            }
        }
        
        // We have to use an iterator in order to prevent a concurrent modificatione exception.
        // This section check if an user has the permissions required to execute a command
        // and if he doesn't, the commands are automatically removed from the completition list.
        if(ConfigUtil.shouldAutomaticallyRemoveCommands) {
            for(Iterator<String> iterator = commands.iterator(); iterator.hasNext();) {
                String commandName = iterator.next();
                PluginCommand c = Bukkit.getPluginCommand(commandName);

                if(c != null && c.testPermissionSilent(player)) {
                    // If user doesn't have permission
                    iterator.remove();
                }
            }
        }
    }

}
