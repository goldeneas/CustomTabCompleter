package com.xgolden.tabcompleter.utils;

import com.xgolden.tabcompleter.components.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

public class ChatUtil {

    public static void sendMessage(CommandSender sender, TextComponent component) {
        // Translate common symbol (&) to the one that's used by Spigot (§).
        String translatedMessage = translateAlternateColorCodes('&', component.getString());
        String translatedPrefix = translateAlternateColorCodes('&', ConfigUtil.PREFIX);

        // Add plugin prefix to final message.
        TextComponent cc = new TextComponent(translatedPrefix);
        cc.append(translatedMessage);

        // Send message.
        sender.sendMessage(cc.getString());
    }

    public static void sendMessage(CommandSender sender, String message) {
        sendMessage(sender, new TextComponent(message));
    }

    public static void sendMessage(Player player, String message) {
        sendMessage((CommandSender) player, new TextComponent(message));
    }

    public static void sendMessage(Player sender, TextComponent component) {
        sendMessage((CommandSender) sender, component);
    }

    public static void sendMessage(String username, String message) {
        sendMessage((CommandSender) Bukkit.getPlayer(username), message);
    }

    public static void sendMessage(String username, TextComponent component) {
        sendMessage((CommandSender) Bukkit.getPlayer(username), component);
    }
    
}
