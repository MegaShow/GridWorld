package com.icytown.gridworld.runner;

import com.icytown.gridworld.actor.DancingBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public final class DancingBugRunner {
    private DancingBugRunner() {
        // Do nothing.
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        int[] arr = {1, 2, 3, 4, 5};
        world.add(new Location(3, 3), new DancingBug(arr));
        world.show();
    }
}
