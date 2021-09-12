package com.xgolden.tabcompleter;

import com.xgolden.tabcompleter.events.CommandSendEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main _instance;

    @Override
    public void onEnable() {
        _instance = this;

        this.getServer().getPluginManager().registerEvents(new CommandSendEvent(), this);

        this.saveDefaultConfig();
    }

    public static Main getInstance() {
        return _instance;
    }

}
