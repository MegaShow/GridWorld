package com.icytown.gridworld.runner;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

public final class GridRunner {
    private GridRunner() {
        // Do nothing.
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.addGridClass("com.icytown.gridworld.grid.SparseBoundedGrid");
        world.addGridClass("com.icytown.gridworld.grid.SparseBoundedGrid2");
        world.addGridClass("com.icytown.gridworld.grid.UnboundedGrid2");
        world.add(new Location(5, 5), new Bug());
        world.show();
    }

}
