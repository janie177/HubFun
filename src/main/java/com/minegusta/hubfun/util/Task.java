package com.minegusta.hubfun.util;

import com.minegusta.hubfun.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Task {
    public static int start() {
        return Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getLocation().getY() < 1.0) p.teleport(p.getWorld().getSpawnLocation());
                }
            }
        }, 20, 20);
    }
}
