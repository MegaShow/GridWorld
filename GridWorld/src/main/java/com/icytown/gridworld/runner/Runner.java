package com.icytown.gridworld.runner;

import info.gridworld.actor.ActorWorld;

public final class Runner {
    private Runner() {
        // Do nothing.
    }

    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        // Add info.gridworld Class
        world.addOccupantClass("info.gridworld.actor.Actor");
        world.addOccupantClass("info.gridworld.actor.Bug");
        world.addOccupantClass("info.gridworld.actor.BoxBug");
        world.addOccupantClass("info.gridworld.actor.ChameleonCritter");
        world.addOccupantClass("info.gridworld.actor.CrabCritter");
        world.addOccupantClass("info.gridworld.actor.Critter");
        world.addOccupantClass("info.gridworld.actor.Flower");
        world.addOccupantClass("info.gridworld.actor.Rock");

        // Add Actor Class
        world.addOccupantClass("com.icytown.gridworld.actor.CircleBug");
        world.addOccupantClass("com.icytown.gridworld.actor.SpiralBug");
        world.addOccupantClass("com.icytown.gridworld.actor.ZBug");
        world.addOccupantClass("com.icytown.gridworld.actor.DancingBug");

        world.addOccupantClass("com.icytown.gridworld.actor.Jumper");

        world.addOccupantClass("com.icytown.gridworld.actor.ModifiedChameleonCritter");
        world.addOccupantClass("com.icytown.gridworld.actor.ChameleonKid");
        world.addOccupantClass("com.icytown.gridworld.actor.RockHound");
        world.addOccupantClass("com.icytown.gridworld.actor.BlusterCritter");
        world.addOccupantClass("com.icytown.gridworld.actor.QuickCrab");
        world.addOccupantClass("com.icytown.gridworld.actor.KingCrab");

        // Add Grid Class
        world.addGridClass("com.icytown.gridworld.grid.SparseBoundedGrid");
        world.addGridClass("com.icytown.gridworld.grid.SparseBoundedGrid2");
        world.addGridClass("com.icytown.gridworld.grid.UnboundedGrid2");
        world.show();
    }

}