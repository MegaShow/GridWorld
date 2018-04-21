package com.icytown.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>ChameleonKid</code> takes on the color of actors in front or behind as it moves through the grid.
 */
public class ChameleonKid extends ModifiedChameleonCritter {
    /**
     * Gets the actors in front or behind.
     * @return a list of actors that this critter wishes to process.
     */
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> list = new ArrayList<>();
        Grid gr = getGrid();
        for (int i = 0; i < Location.FULL_CIRCLE; i += Location.HALF_CIRCLE) {
            Location loc = getLocation().getAdjacentLocation(getDirection() + i);
            if (!gr.isValid(loc)) {
                continue;
            }
            Actor actor = (Actor) gr.get(loc);
            if (actor != null) {
                list.add(actor);
            }
        }
        return list;
    }
}
