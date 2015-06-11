package com.minegusta.hubfun.tasks;

import com.minegusta.hubfun.Main;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.List;

public class BarMessageTask {
    public static final List<String> messages = Main.PLUGIN.getConfig().getStringList("messages");
    private static int id = -1;
    private static int count = 0;
    private static int percentagePerMessage = 100 / messages.size();

    public static void start() {
        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, () -> {

            if (count >= messages.size()) count = 0;

            float percentage = count * percentagePerMessage;

            Bukkit.getOnlinePlayers().stream().forEach(p -> BarAPI.setMessage(p, ChatColor.translateAlternateColorCodes('&', messages.get(count)), percentage));

            count++;

        }, 20 * 10, 20 * 10);
    }

    public static void stop() {
        if (id != -1) {
            Bukkit.getScheduler().cancelTask(id);
        }
    }


}
