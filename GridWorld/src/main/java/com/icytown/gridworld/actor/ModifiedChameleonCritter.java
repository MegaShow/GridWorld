package com.icytown.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * A <code>ModifiedChameleonCritter</code> takes on the color of neighboring actors as it moves through the grid.
 */
public class ModifiedChameleonCritter extends Critter {
    private static final double DARKENING_FACTOR = 0.05;

    /**
     * Randomly selects a neighbor and changes this critter's color to be the same as that neighbor's.
     * If there are no neighbors, the color of the ChameleonCritter will darken.
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n == 0) {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        } else {
            Random random = new Random();
            int r = (int) (random.nextDouble() * n);
            Actor other = actors.get(r);
            setColor(other.getColor());
        }
    }

    /**
     * Turns towards the new location as it moves.
     */
    @Override
    public void makeMove(Location loc) {
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
    }
}
