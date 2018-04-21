package com.icytown.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.CrabCritter;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

/**
 * A QuickCrab is a CrabCritter that can move two cells.
 */
public class QuickCrab extends CrabCritter {
    /**
     * If the QuickCrab can't move two cells, it move as if crab critter.
     */
    @Override
    public void makeMove(Location loc) {
        if (loc.equals(getLocation())) {
            super.makeMove(loc);
        } else {
            Location nextLoc = loc.getAdjacentLocation(getLocation().getDirectionToward(loc));
            if (getGrid().isValid(nextLoc)) {
                Actor actor = getGrid().get(nextLoc);
                if (actor == null || actor instanceof Flower) {
                    moveTo(nextLoc);
                } else {
                    super.makeMove(loc);
                }
            } else {
                super.makeMove(loc);
            }
        }
    }
}
