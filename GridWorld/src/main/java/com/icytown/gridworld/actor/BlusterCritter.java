package com.icytown.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

/**
 *  A BlusterCritter is a Critter that can set its color brighter or darker.
 */
public class BlusterCritter extends Critter {
    private static final int CELLS = 2;
    private static final double DARKENING_FACTOR = 0.05;
    private static final int MAX_COLOR_VALUE = 255;
    private int courage;

    /**
     * Construct a BlusterCritter with critter's courage.
     * @param critterCourage the courage of Critter
     */
    public BlusterCritter(int critterCourage) {
        courage = critterCourage;
    }

    /**
     * Get the critters that in two-cells location of the BlusterCritter.
     * @return a list of critters that in two-cells location of the BlusterCritter.
     */
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> list = new ArrayList<>();
        Grid gr = getGrid();
        for (int r = -CELLS; r <= CELLS; r++) {
            for (int c = -CELLS; c <= CELLS; c++) {
                if (r == 0 && c == 0) {
                    continue;
                }
                Location loc = new Location(getLocation().getRow() + r, getLocation().getCol() + c);
                if (gr.isValid(loc)) {
                    Actor actor = (Actor) gr.get(loc);
                    if (actor instanceof Critter) {
                        list.add(actor);
                    }
                }
            }
        }
        return list;
    }

    /**
     * Compare the size of actors with courage.
     * @param actors the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        if (actors.size() < courage) {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
            if (red > MAX_COLOR_VALUE) {
                red = MAX_COLOR_VALUE;
            }
            if (green > MAX_COLOR_VALUE) {
                green = MAX_COLOR_VALUE;
            }
            if (blue > MAX_COLOR_VALUE) {
                blue = MAX_COLOR_VALUE;
            }
            setColor(new Color(red, green, blue));
        } else {
            Color c = getColor();
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
            setColor(new Color(red, green, blue));
        }
    }
}
