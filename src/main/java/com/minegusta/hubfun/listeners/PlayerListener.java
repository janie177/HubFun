package com.minegusta.hubfun.listeners;


import com.minegusta.hubfun.util.PlayEffect;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.*;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener
{
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPop(EntityDamageByEntityEvent event)
    {
        Entity damager = event.getDamager();
        Entity entity = event.getEntity();
        if (damager instanceof Player) {
            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1F, 1F);
            entity.playEffect(EntityEffect.WOLF_SMOKE);
            Location location = entity.getLocation();
            entity.getWorld().spigot().playEffect(location, Effect.HEART, 1, 1, 1F, 1.5F, 1F, 0.5F, 7, 5);
            entity.getWorld().spigot().playEffect(location, Effect.FLAME, 1, 1, 0.5F, 2F, 0.5F, 0.5F, 15, 5);
            entity.getWorld().spigot().playEffect(location, Effect.CLOUD, 1, 1, 1F, 2F, 1F, 0.5F, 30, 5);
            entity.getWorld().spigot().playEffect(location, Effect.MAGIC_CRIT, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 5, 5);
            entity.getWorld().spigot().playEffect(location, Effect.PARTICLE_SMOKE, 1, 1, 0.5F, 1F, 0.5F, 0.5F, 5, 5);
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
            Vector v = e.getPlayer().getLocation().getDirection();
            passenger.eject();
            e.getPlayer().eject();
            passenger.teleport(passenger.getLocation().add(v.getX(), 0.01, v.getZ()));
            passenger.setVelocity(v.add(new Vector(v.getX(), v.getY(), v.getZ()).multiply(1.6)));
            new PlayEffect(passenger);
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
        e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.GRAY + e.getPlayer().getDisplayName() + ChatColor.GRAY + " joined the hub..."));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCrouch(PlayerToggleSneakEvent e) {
        if (e.getPlayer().getPassenger() != null && e.getPlayer().getPassenger() instanceof Player) {
            Player passenger = (Player) e.getPlayer().getPassenger();
            Vector v = e.getPlayer().getLocation().getDirection();
            passenger.eject();
            e.getPlayer().eject();
            passenger.teleport(passenger.getLocation().add(v.getX(), 0.01, v.getZ()));
            passenger.setVelocity(v.add(new Vector(v.getX(), v.getY(), v.getZ()).multiply(1.6)));
            new PlayEffect(passenger);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.isSneaking() && !p.isFlying() && p.getLocation().getY() < 50) {
            Vector victor = ((p.getPassenger() != null) && (p.getLocation().getDirection().getY() > 0.0D) ? p.getLocation().getDirection().clone().setY(0) : p.getLocation().getDirection()).normalize().multiply(1.3D);
            p.setVelocity(victor);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onLeaveHub(PlayerQuitEvent e)
    {
        e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.GRAY + e.getPlayer().getDisplayName() + ChatColor.GRAY + " left the hub..."));
    }
}
