package com.xgolden.tabcompleter.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompleterEvent implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> firstArgumentList = Arrays.asList("reload");
        ArrayList<String> finalList = new ArrayList<>();

        for(String s : firstArgumentList) {
            if(s.startsWith(args[0])) {
                finalList.add(s);
            }
        }

        return finalList;
    }

}
