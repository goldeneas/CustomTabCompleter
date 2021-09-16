package com.xgolden.tabcompleter.events;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompleterEvent implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> firstArgumentList = Arrays.asList("reload", "add", "remove");
        List<String> commandManagementList = Arrays.asList("<command>");

        switch(args[0]) {
            case "":
                return firstArgumentList;

            case "add":
            case "remove":
                return commandManagementList;
                
            default:
                return null;
        }
    }

}
