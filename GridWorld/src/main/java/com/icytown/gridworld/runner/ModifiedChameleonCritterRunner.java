package com.icytown.gridworld.runner;

import com.icytown.gridworld.actor.ModifiedChameleonCritter;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains chameleon critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class ModifiedChameleonCritterRunner {
    private ModifiedChameleonCritterRunner() {
        // Do nothing.
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        Actor actor =  new ModifiedChameleonCritter();
        actor.setColor(Color.PINK);
        world.add(new Location(5, 8), actor);
        world.show();
    }
}