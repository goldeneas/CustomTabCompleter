package com.xgolden.tabcompleter;

import com.xgolden.tabcompleter.events.CommandEvent;
import com.xgolden.tabcompleter.events.CommandPreProcessEvent;
import com.xgolden.tabcompleter.events.CommandSendEvent;
import com.xgolden.tabcompleter.events.PlayerConnectEvent;
import com.xgolden.tabcompleter.events.TabCompleterEvent;
import com.xgolden.tabcompleter.utils.UpdateUtil;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main _instance;

    @Override
    public void onEnable() {
        _instance = this;

        // Setup bStats - there's no need to store the metrics object since we're not using it.
        new Metrics(this, 12786);
        // Check for updates
        UpdateUtil.check();

        this.getCommand("tabcompleter").setExecutor(new CommandEvent());
        this.getCommand("tabcompleter").setTabCompleter(new TabCompleterEvent());

        this.getServer().getPluginManager().registerEvents(new CommandSendEvent(), this);
        this.getServer().getPluginManager().registerEvents(new CommandPreProcessEvent(), this);
        this.getServer().getPluginManager().registerEvents(new PlayerConnectEvent(), this);

        this.saveDefaultConfig();
        
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static Main getInstance() {
        return _instance;
    }

}
