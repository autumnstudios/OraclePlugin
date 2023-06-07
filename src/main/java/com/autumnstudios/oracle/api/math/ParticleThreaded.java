package com.autumnstudios.oracle.api.math;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.nio.Buffer;

class ParticleThreaded extends Thread {

    private MathParticleType type;

    float delayBetweenSpawn;
    boolean useDelay;

    // Circle info
    double scale;
    Location loc;
    Effect effect;

    // Arc info

    Location from;
    Location to;



    public ParticleThreaded() {
        this.type = MathParticleType.NONE;
        this.delayBetweenSpawn = 1;
        this.useDelay = false;
    }

    public void setupCircle(double scale, Location loc, Effect effect, float delayBetweenSpawn) {
        this.type = MathParticleType.CIRCLE;
        this.scale = scale;
        this.loc = loc;
        this.effect = effect;
        if (delayBetweenSpawn == 0) {
            this.useDelay = false;
        } else {
            this.delayBetweenSpawn = delayBetweenSpawn;
            this.useDelay = true;
        }


    }



    public void run() {
        switch (type) {
            case CIRCLE:
                createCircle();
                break;
            case NONE:
                break;
        }
    }

    public void createCircle() {
        for (int degree = 0; degree < 360; degree++) {
            double radians = Math.toRadians(degree);
            double x = Math.cos(radians) * scale;
            double z = Math.sin(radians) * scale;
            loc.add(x,0,z);
            loc.getWorld().playEffect(loc, effect, 1);
            loc.subtract(x,0,z);
            if (useDelay) {
                try {
                    Thread.sleep((long) (delayBetweenSpawn * 1000));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


}
