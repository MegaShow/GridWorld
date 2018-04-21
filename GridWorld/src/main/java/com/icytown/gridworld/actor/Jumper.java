package com.icytown.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

public class Jumper extends Bug {
    /**
     * Construct a green jumper.
     */
    public Jumper() {
        setColor(Color.GREEN);
    }

    /**
     * Moves the jumper forward two cells
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this jumper can move forward into a location two cells that is empty or
     * contains a flower.
     *
     * @return true if this jumper can move.
     */
    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (!(neighbor instanceof Jumper) && !(neighbor instanceof Rock));
    }
}
