package com.icytown.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.CrabCritter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A KingCrab can move others away from itself.
 */
public class KingCrab extends CrabCritter {
    /**
     * Move others away from itself.
     * @param actors the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            ArrayList<Location> list = getGrid().getEmptyAdjacentLocations(a.getLocation());
            boolean flag = false;
            for (Location loc : list) {
                if (getGrid().isValid(loc) && !isNeighbors(loc, getLocation())) {
                    a.moveTo(loc);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                a.removeSelfFromGrid();
            }
        }
    }

    /**
     * Check if two locations are neighbors
     * @param loc1 the location to compare
     * @param loc2 the location to compare
     * @return if the two locations are neighbors
     */
    private boolean isNeighbors(Location loc1, Location loc2) {
        return Math.abs(loc1.getCol() - loc2.getCol()) <= 1 && Math.abs(loc1.getRow() - loc2.getRow()) <= 1;
    }
}
