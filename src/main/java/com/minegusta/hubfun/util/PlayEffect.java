package com.minegusta.hubfun.util;

import com.minegusta.hubfun.Main;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PlayEffect {
    private final Player p;


    public PlayEffect(Player p) {
        this.p = p;
        play();
    }

    private void play() {
        for (int i = 0; i <= 20 * 3; i++) {
            if (i % 4 == 0) {
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                    @Override
                    public void run() {

                        if (p == null || !p.isOnline()) return;
                        p.getWorld().playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 5F, 1F);
                        p.getWorld().spigot().playEffect(p.getLocation(), Effect.FLAME, 1, 1, 0.5F, 2F, 0.5F, 0.5F, 6, 20);
                        p.getWorld().spigot().playEffect(p.getLocation(), Effect.CLOUD, 1, 1, 1F, 2F, 1F, 0.5F, 6, 20);
                        p.getWorld().spigot().playEffect(p.getLocation(), Effect.MAGIC_CRIT, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 6, 20);
                        p.getWorld().spigot().playEffect(p.getLocation(), Effect.PARTICLE_SMOKE, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 6, 20);
                        p.getWorld().spigot().playEffect(p.getLocation(), Effect.POTION_SWIRL, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 6, 20);

                    }
                }, i);
            }
        }
    }

}
