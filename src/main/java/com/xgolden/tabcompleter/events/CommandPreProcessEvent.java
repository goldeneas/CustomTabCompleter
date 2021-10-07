package com.xgolden.tabcompleter.events;

import java.util.List;

import com.xgolden.tabcompleter.utils.ConfigUtil;
import com.xgolden.tabcompleter.utils.GroupsUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandPreProcessEvent implements Listener {

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        if (!ConfigUtil.shouldFakeNoPermission || player.hasPermission(ConfigUtil.bypassPermission)) {
            return;
        }

        // We need to use substring to remove the forward slash from the command.
        String commandName = event.getMessage().substring(1);

        for (String groupPermission : GroupsUtil.getGroups()) {
            if (player.hasPermission(groupPermission)) {
                List<String> _commandsGroupList = GroupsUtil.getCommandsForGroup(groupPermission);
                boolean isWhitelist = GroupsUtil.isWhitelist(groupPermission);

                if ((isWhitelist && !_commandsGroupList.contains(commandName))
                        || (!isWhitelist && _commandsGroupList.contains(commandName))) {
                    // We avoid using ChatUtil here because we want to prevent the plugin's prefix from showing
                    // before the no permission message.
                    event.getPlayer().sendMessage(ConfigUtil.FAKE_NO_PERMISSION.getString());
                    event.setCancelled(true);
                    break;
                }

            }
        }
    }

}
