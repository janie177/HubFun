package com.minegusta.hubfun;

import com.minegusta.hubfun.listeners.PlayerListener;
import com.minegusta.hubfun.util.FlyTask;
import com.minegusta.hubfun.util.Task;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin PLUGIN;
    private static int SPAWNTASK;

    @Override
    public void onEnable() {
        //Plugin
        PLUGIN = this;
        //Listeners
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

        //config
        saveDefaultConfig();

        //Fly task
        FlyTask.start();

        //Task
        SPAWNTASK = Task.start();
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTask(SPAWNTASK);
        FlyTask.stop();
    }
}
