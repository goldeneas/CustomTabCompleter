package com.xgolden.tabcompleter.events;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompleterEvent implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        // TODO: fix reload command
        List<String> noneOptions = Arrays.asList("create", "delete", "addCommand", "removeCommand");
        List<String> groupOptions = Arrays.asList("<group>");
        List<String> commandOptions = Arrays.asList("<group> <command>");

        switch(args[0]) {
            case "":
                return noneOptions;

            case "create":
            case "delete":
                return groupOptions;

            case "addCommand":
            case "removeCommand":
                return commandOptions;
                
            default:
                return Collections.emptyList();
        }
    }

}
