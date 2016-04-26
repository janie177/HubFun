package com.minegusta.hubfun.util;

import com.google.common.collect.Lists;
import com.minegusta.hubfun.Main;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.inventivetalent.bossbar.BossBarAPI;

import java.util.List;

public class BossBarTask {
	private static int ID = -1;

	private static final List<BossBarMessage> messages = Lists.newArrayList(

	);

	public static void init(FileConfiguration conf) {
		for (String path : conf.getConfigurationSection("messages").getKeys(false)) {
			try {
				String message = conf.getString("messages." + path + ".message");
				BossBarAPI.Color color = BossBarAPI.Color.valueOf(conf.getString("messages." + path + ".color"));
				BossBarAPI.Style style = BossBarAPI.Style.valueOf(conf.getString("messages." + path + ".style"));

				messages.add(new BossBarMessage(message, color, style));
			} catch (Exception ignored) {
			}
		}
	}

	private static int currentId = 0;
	private static BossBarMessage current = messages.get(0);

	public static void start() {
		ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
			@Override
			public void run() {

				Bukkit.getOnlinePlayers().stream().forEach(player -> BossBarAPI.addBar(player, new TextComponent(current.getMessage().replace("%name%", player.getName())), current.getColor(), current.getStyle(), 1.0f, 40, 2));

				currentId++;
				if (currentId >= messages.size()) {
					currentId = 0;
				}
				current = messages.get(currentId);
			}
		}, 80, 80);
	}

	public static void stop() {
		if (ID != -1) {
			Bukkit.getScheduler().cancelTask(ID);
		}
	}
}
