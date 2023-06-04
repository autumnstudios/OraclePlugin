package com.autumnstudios.oracle.systems.worlds.threads;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class WorldCreationThread extends Thread {

    String worldName;
    WorldType worldType;
    public World world;

    public void create(String worldName, WorldType worldType) {
        this.worldName = worldName;
        this.worldType = worldType;
        this.start();
    }

    public void run() {
        if (Bukkit.getWorld(worldName) != null) {
            WorldCreator worldCreator = new WorldCreator(worldName);
            world = worldCreator.createWorld();

        }

    }


}
