package com.xgolden.tabcompleter.events;

import java.util.List;

import com.xgolden.tabcompleter.utils.ChatUtil;
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
        // We need to use substring to remove the forward slash from the command.
        String eventMessage = event.getMessage();
        String commandName = eventMessage.substring(1, eventMessage.indexOf(" ")).toLowerCase();

        for(String group : GroupsUtil.getGroupNames()) {
            List<String> commands = GroupsUtil.getCommandsForGroup(group);
            boolean isBlacklist = GroupsUtil.isGroupBlacklist(group);

            if(isBlacklist && commands.contains(commandName)
            || (!isBlacklist && !commands.contains(commandName))) {
                ChatUtil.sendMessage(player, ConfigUtil.FAKE_NO_PERMISSION);
                event.setCancelled(true);
                break;
            }
        }
    }

}
