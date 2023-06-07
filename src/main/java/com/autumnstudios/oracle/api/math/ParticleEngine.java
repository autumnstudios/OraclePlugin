package com.autumnstudios.oracle.api.math;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ParticleEngine {

    private JavaPlugin plugin;

    public ParticleEngine(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // Public functions

    /**
     * A function to create a particle circle using scale, location, and effect!
     * @param scale The scale of the circle, usually 1
     * @param loc The anchor of the circle
     * @param effect What effect to show?
     */
    public void circle(int scale, Location loc, Effect effect, float delayBetweenSpawn) {
        ParticleThreaded threaded = new ParticleThreaded();
        threaded.setupCircle(scale, loc, effect, delayBetweenSpawn);
        threaded.start();

    }




}
