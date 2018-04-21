package com.icytown.gridworld.runner;

import com.icytown.gridworld.actor.ZBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public final class ZBugRunner {
    private ZBugRunner() {
        // Do nothing.
    }
    
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(3, 3), new ZBug(4));
        world.show();
    }
}
