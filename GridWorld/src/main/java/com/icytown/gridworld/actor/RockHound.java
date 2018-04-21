package com.icytown.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;

import java.util.ArrayList;

/**
 * A RockHound is a critter which can also remove critter.
 */
public class RockHound extends Critter {
    /**
     * A RockHound can eat everything except Critter
     * @param actors the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        for (Actor a : actors) {
            if (!(a instanceof Critter)) {
                a.removeSelfFromGrid();
            }
        }
    }
}
