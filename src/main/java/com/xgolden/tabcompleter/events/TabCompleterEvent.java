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
        //List<String> firstArgumentList = Arrays.asList("reload", "create", "delete", "add", "remove");
        List<String> firstArgumentList = Arrays.asList("create", "delete", "add", "remove");
        List<String> commandCreationList = Arrays.asList("<group>");
        List<String> commandManagementList = Arrays.asList("<command> <group>");

        switch(args[0]) {
            case "":
                return firstArgumentList;

            case "create":
            case "delete":
                return commandCreationList;

            case "add":
            case "remove":
                return commandManagementList;
                
            default:
                return Collections.emptyList();
        }
    }

}
