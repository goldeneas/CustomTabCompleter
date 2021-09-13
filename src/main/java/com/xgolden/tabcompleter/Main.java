package com.xgolden.tabcompleter;

import com.xgolden.tabcompleter.events.CommandEvent;
import com.xgolden.tabcompleter.events.CommandSendEvent;
import com.xgolden.tabcompleter.events.TabCompleterEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main _instance;

    @Override
    public void onEnable() {
        _instance = this;

        this.getCommand("tabcompleter").setExecutor(new CommandEvent());
        this.getCommand("tabcompleter").setTabCompleter(new TabCompleterEvent());

        this.getServer().getPluginManager().registerEvents(new CommandSendEvent(), this);

        this.saveDefaultConfig();
        
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static Main getInstance() {
        return _instance;
    }

}
