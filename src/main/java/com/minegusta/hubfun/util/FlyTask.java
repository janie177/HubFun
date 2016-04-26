package com.minegusta.hubfun.util;

import com.minegusta.hubfun.Main;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;

public class FlyTask {


	private static int ID = -1;

	public static void start() {
		ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.PLUGIN, new Runnable() {
			@Override
			public void run() {
				Bukkit.getOnlinePlayers().stream().filter(p -> p.isSneaking() && !p.isFlying() && p.getLocation().getY() <= 50).forEach(p -> {
					Vector victor = ((p.getPassenger() != null) && (p.getLocation().getDirection().getY() > 0.0D) ? p.getLocation().getDirection().clone().setY(0) : p.getLocation().getDirection()).normalize().multiply(1.3D);
					p.setVelocity(victor);
				});
			}
		}, 10, 10);
	}

	public static void stop() {
		if (ID != -1) {
			Bukkit.getScheduler().cancelTask(ID);
		}
	}
}
