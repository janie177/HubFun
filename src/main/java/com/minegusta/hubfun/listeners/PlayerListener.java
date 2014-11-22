package com.minegusta.hubfun.listeners;


import com.minegusta.hubfun.Main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPop(EntityDamageByEntityEvent event)
    {
        Entity damager = event.getDamager();
        Entity entity = event.getEntity();
        if (damager instanceof Player) {
            entity.getWorld().playSound(entity.getLocation(), Sound.CHICKEN_EGG_POP, 5F, 1F);
            entity.playEffect(EntityEffect.WOLF_SMOKE);
            Location location = entity.getLocation();
            entity.getWorld().spigot().playEffect(location, Effect.HEART, 1, 1, 0.35F, 1.5F, 0.35F, 0.5F, 15, 5);
            entity.getWorld().spigot().playEffect(location, Effect.FLAME, 1, 1, 0.5F, 2F, 0.5F, 0.5F, 15, 5);
            entity.getWorld().spigot().playEffect(location, Effect.CLOUD, 1, 1, 1F, 2F, 1F, 0.5F, 30, 5);
            entity.getWorld().spigot().playEffect(location, Effect.MAGIC_CRIT, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 20, 5);
            entity.getWorld().spigot().playEffect(location, Effect.PARTICLE_SMOKE, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 40, 5);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMount(PlayerInteractEntityEvent e)
    {
        if(!(e.getRightClicked() instanceof Player))return;

        Player clicker = e.getPlayer();
        Player mount = (Player) e.getRightClicked();
        mount.setPassenger(clicker);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLaunch(PlayerInteractEvent e)
    {
        if (!e.hasBlock() && e.getAction() == Action.LEFT_CLICK_AIR && e.getPlayer().getPassenger() != null && e.getPlayer().getPassenger() instanceof Player)
        {
            Player passenger = (Player) e.getPlayer().getPassenger();
            Vector v = e.getPlayer().getVelocity();
            passenger.eject();
            passenger.setVelocity(v.add(new Vector(0, 1, 0)).multiply(4.0).normalize());
            launchEffect((Player) e.getPlayer().getPassenger());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onFoodDrain(FoodLevelChangeEvent e)
    {
        if(e.getFoodLevel() != 20)e.setFoodLevel(20);
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoinHub(PlayerJoinEvent e)
    {
        e.setJoinMessage(ChatColor.GRAY + e.getPlayer().getName() + " joined the hub...");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeaveHub(PlayerQuitEvent e)
    {
        e.setQuitMessage(ChatColor.GRAY + e.getPlayer().getName() + " left the hub...");
    }

    public static void launchEffect(Player pl) {
        for (int i = 0; i <= 20 * 3; i++) {
            if (i % 4 != 0) return;
            else {
                final Location location = pl.getLocation();
                final Player p = pl;
                Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                    @Override
                    public void run() {

                        p.getWorld().playSound(location, Sound.CHICKEN_EGG_POP, 5F, 1F);
                        p.getWorld().spigot().playEffect(location, Effect.FLAME, 1, 1, 0.5F, 2F, 0.5F, 0.5F, 15, 5);
                        p.getWorld().spigot().playEffect(location, Effect.CLOUD, 1, 1, 1F, 2F, 1F, 0.5F, 30, 5);
                        p.getWorld().spigot().playEffect(location, Effect.MAGIC_CRIT, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 20, 5);
                        p.getWorld().spigot().playEffect(location, Effect.PARTICLE_SMOKE, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 40, 5);
                        p.getWorld().spigot().playEffect(location, Effect.POTION_SWIRL, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 40, 5);

                    }
                }, i);
            }
        }
    }
}
