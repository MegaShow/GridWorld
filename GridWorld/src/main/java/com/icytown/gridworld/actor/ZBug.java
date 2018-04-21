package com.icytown.gridworld.actor;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A <code>ZBug</code> traces with a given side length.
 */
public class ZBug extends Bug {
    private int steps;
    private int sideLength;

    /**
     * Constructs a bug with a given side length.
     * @param length the size length
     */
    public ZBug(int length) {
        steps = 0;
        sideLength = length;
        setDirection(Location.EAST);
    }

    /**
     * Moves to the next location.
     */
    @Override
    public void act() {
        if (steps < sideLength) {
            if (getDirection() != Location.EAST) {
                setDirection(Location.EAST);
            }
        } else if (steps < sideLength * 2) {
            if (getDirection() != Location.SOUTHWEST) {
                setDirection(Location.SOUTHWEST);
            }
        } else if (steps < sideLength * 3 && getDirection() != Location.EAST) {
            setDirection(Location.EAST);
        }
        if (steps < sideLength * 3 && canMove()) {
            move();
            steps++;
        }
    }
}
