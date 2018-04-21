package com.icytown.gridworld.actor;

import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * A <code>DancingBug</code> traces with a given array about turning times.
 */
public class DancingBug extends Bug {
    private int[] turnPattern;
    private int turnIndex;

    /**
     * Constructs a bug that traces with a given array.
     * @param pattern the array about turning times
     */
    public DancingBug(int[] pattern) {
        turnPattern = new int[pattern.length];
        System.arraycopy(pattern, 0, turnPattern, 0, pattern.length);
        turnIndex = 0;
    }

    /**
     * Moves to the next location.
     */
    @Override
    public void act() {
        if (canMove()) {
            move();
        } else {
            if (turnIndex < turnPattern.length) {
                setDirection(getDirection() + Location.HALF_RIGHT * turnPattern[turnIndex]);
                turnIndex++;
            } else {
                turnIndex = 0;
            }
        }
    }
}
