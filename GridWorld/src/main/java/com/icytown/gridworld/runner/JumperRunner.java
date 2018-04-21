package com.icytown.gridworld.runner;

import com.icytown.gridworld.actor.Jumper;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class JumperRunner {
    private JumperRunner() {
        // Do nothing.
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(5, 2), new Jumper());
        world.add(new Location(5, 3), new Flower());
        world.add(new Location(8, 4), new Rock());
        world.show();
    }
}
