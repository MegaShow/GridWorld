package com.icytown.gridworld.runner;

import com.icytown.gridworld.actor.SpiralBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.UnboundedGrid;

public final class SpiralBugRunner {
    private SpiralBugRunner() {
        // Do nothing.
    }
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new UnboundedGrid<>());
        world.add(new Location(15, 15), new SpiralBug(1));
        world.show();
    }
}
