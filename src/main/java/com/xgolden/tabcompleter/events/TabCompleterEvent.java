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
        List<String> noneOptions = Arrays.asList("create", "delete", "addCommand", "removeCommand", "reload");
        List<String> commandOptions = Arrays.asList("<group> <command>");

        switch(args[0]) {
            case "":
                return noneOptions;

            case "create":
                return Arrays.asList("<name> <permission>");

            case "delete":
                return Arrays.asList("<group>");

            case "addCommand":
            case "removeCommand":
                return commandOptions;
                
            default:
                return Collections.emptyList();
        }
    }

}
