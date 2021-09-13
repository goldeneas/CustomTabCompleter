package com.xgolden.tabcompleter.events;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompleterEvent implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> defaultList = Arrays.asList("reload");

        switch (args[0]) {
            case "":
                return defaultList;
            default:
                return null;
        }
    }

}
