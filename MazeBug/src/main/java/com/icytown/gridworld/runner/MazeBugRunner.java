package com.icytown.gridworld.runner;

import com.icytown.gridworld.actor.MazeBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.Rock;

/**
 * This class runs a world that contains maze bugs.
 * This class is not tested on the AP CS A and AB exams.
 */
public final class MazeBugRunner {
    private MazeBugRunner() {
        // Do nothing
    }

    public static void main(String[] args) {
        UnboundedGrid ugr = new UnboundedGrid();
        ActorWorld world = new ActorWorld(ugr);
        world.add(new Location(0, 0), new MazeBug());
        world.add(new Location(1, 1), new Rock());
        world.show();
    }
}
