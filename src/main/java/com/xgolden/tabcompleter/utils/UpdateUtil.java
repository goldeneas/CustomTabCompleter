package com.xgolden.tabcompleter.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import com.xgolden.tabcompleter.Main;

import org.bukkit.Bukkit;

public class UpdateUtil {

    private static Main plugin = Main.getInstance();

    public static boolean isUpdateAvailable = false;

    public static void check() {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=96184").openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    String currentVersion = plugin.getDescription().getVersion();
                    String newestVersion = scanner.next();

                    if(!(newestVersion.equalsIgnoreCase(currentVersion))) {
                        plugin.getLogger().info("New update is available! Please download it from our spigot page.");
                        isUpdateAvailable = true;
                    }
                }
            } catch (IOException exception) {
                plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
            }
        });
    }

}
