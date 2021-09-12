package com.xgolden.tabcompleter.events;

import java.util.Collection;
import java.util.List;

import com.xgolden.tabcompleter.Main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class CommandSendEvent implements Listener {

    FileConfiguration config = Main.getInstance().getConfig();

    private boolean isListWhitelist = config.getBoolean("whitelist_instead_of_blacklist");
    private List<String> listCommands = config.getStringList("blacklisted_commands");
    
    private String bypassPermission = config.getString("bypass_tab_filtering_permission");

    @EventHandler
    public void onCommandSend(PlayerCommandSendEvent event) {
        Player player = event.getPlayer();
        if(player.hasPermission(bypassPermission)) { return; }

        Collection<String> commands = event.getCommands();

        if(isListWhitelist) {
            commands.retainAll(listCommands);
        } else {
            commands.removeAll(listCommands);
        }
    }

}
