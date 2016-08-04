package com.minegusta.hubfun.util;

import com.google.common.collect.Lists;
import com.minegusta.hubfun.Main;
import net.minegusta.mglib.bossbars.BossBarUtil;
import net.minegusta.mglib.bossbars.TimedBossBarHolder;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class BossBarTask {
	private static int ID = -1;

	private static final List<BossBarMessage> messages = Lists.newArrayList();

	public static void init(FileConfiguration conf) {
		int amount = 0;
		int failed = 0;
		for (String path : conf.getConfigurationSection("messages").getKeys(false)) {
			try {
				String message = conf.getString("messages." + path + ".message");
				BarColor color = BarColor.valueOf(conf.getString("messages." + path + ".color").toUpperCase());
				BarStyle style = BarStyle.valueOf(conf.getString("messages." + path + ".style").toUpperCase());
				messages.add(new BossBarMessage(message, color, style));
			} catch (Exception ignored) {
				Bukkit.getLogger().info("Error while trying to read a config entry in HubFun.");
				failed++;
			}
			amount++;
		}
		Bukkit.getLogger().info("[HubFun] Attempting to load " + amount + " messages.");
		Bukkit.getLogger().info("[HubFun] " + failed + " messages failed to load.");
	}

	private static int currentId = 0;

	public static void start() {
		ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
			@Override
			public void run() {
				BossBarMessage current = messages.get(currentId);
				TimedBossBarHolder bar = BossBarUtil.createSecondCountdown(current.getMessage(), current.getColor(), current.getStyle(), 8);
				Bukkit.getOnlinePlayers().stream().forEach(bar::addPlayer);

				currentId++;
				if (currentId >= messages.size()) {
					currentId = 0;
				}
			}
		}, 82, 82);
	}

	public static void stop() {
		if (ID != -1) {
			Bukkit.getScheduler().cancelTask(ID);
		}
	}
}
