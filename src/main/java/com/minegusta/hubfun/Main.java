package com.minegusta.hubfun;

import com.minegusta.hubfun.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin PLUGIN;

    @Override
    public void onEnable() {
        //Plugin
        PLUGIN = this;
        //Listeners
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
