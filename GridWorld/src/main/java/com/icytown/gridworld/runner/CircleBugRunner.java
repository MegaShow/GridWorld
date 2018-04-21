package com.icytown.gridworld.runner;

import com.icytown.gridworld.actor.CircleBug;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public final class CircleBugRunner {
    private CircleBugRunner() {
        // Do nothing.
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(5, 2), new CircleBug(2));
        world.show();
    }
}
