package com.xgolden.tabcompleter.events;

import com.xgolden.tabcompleter.utils.ChatUtil;
import com.xgolden.tabcompleter.utils.ConfigUtil;
import com.xgolden.tabcompleter.utils.UpdateUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerConnectEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(!player.hasPermission(ConfigUtil.adminPermission) || !UpdateUtil.isUpdateAvailable) { return; }

        ChatUtil.sendMessage(player, ConfigUtil.UPDATE_AVAILABLE);
    }

}
