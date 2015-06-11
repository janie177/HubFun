package com.minegusta.hubfun.tasks;

import com.minegusta.hubfun.Main;
import me.confuser.barapi.BarAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class BarMessageTask {
    public static final String[] messages = new String[]{
            "&eWelcome to the &4DG&8+&3MG&8 Club&e!",
            "&eJoin our forum community at &dDG-MG.club&e today!",
            "&eLooking for factions survival? Join &dMinegus&e!",
            "&eNeed an invite for &6Seasons&e? Request one on our forum!",
            "&eZombie survival? Try out &2Wasteland&e!",
            "&eFeeling creative? Visit our &aPlots &eserver!",
            "&5Minigames&e are coming soon!"
    };

    private static int id = -1;
    private static int count = 0;
    private static int percentagePerMessage = 100 / messages.length;

    public static void start() {
        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, () -> {

            if (count >= messages.length) count = 0;

            Bukkit.getOnlinePlayers().stream().forEach(p -> BarAPI.setMessage(p, ChatColor.translateAlternateColorCodes('&', messages[count]), count * percentagePerMessage));

            count++;

        }, 20 * 10, 20 * 10);
    }

    public static void stop() {
        if (id != -1) {
            Bukkit.getScheduler().cancelTask(id);
        }
    }


}
