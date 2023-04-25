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
        if(player.hasPermission(ConfigUtil.BYPASS_PERMISSION))
            return;

        Collection<String> commands = event.getCommands();

        for(String group : GroupsUtil.getGroupNames()) {
            String groupPermission = GroupsUtil.getPermissionForGroup(group);

            if(player.hasPermission(groupPermission)) {
                boolean isBlacklist = GroupsUtil.isGroupBlacklist(group);
                List<String> groupCommands = GroupsUtil.getCommandsForGroup(group);

                if(isBlacklist) {
                    commands.removeAll(groupCommands);
                } else {
                    commands.retainAll(groupCommands);
                }
            }
        }
    }

}
