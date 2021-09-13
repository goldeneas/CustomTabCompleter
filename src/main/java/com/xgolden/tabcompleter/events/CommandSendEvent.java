package com.xgolden.tabcompleter.events;

import java.util.Collection;

import com.xgolden.tabcompleter.utils.ConfigUtil;

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

        if(ConfigUtil.isListWhitelist) {
            commands.retainAll(ConfigUtil.commandsList);
        } else {
            commands.removeAll(ConfigUtil.commandsList);
        }
    }

}
