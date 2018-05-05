package com.icytown.gridworld.actor;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * A MazeBug can find its way in a maze.
 */
public class MazeBug extends Bug {
    private Location next;
    private Location last;
    private boolean isEnd = false;
    private Stack<ArrayList<Location>> crossLocation = new Stack<>();
    private Integer stepCount = 0;
    private boolean hasShown = false;
    private int[] probability = new int[4];
    private boolean isProbabilityEnabled = true;
    private boolean isRandomlyEnabled = false;

    /**
     * Constructs a maze bug that can trace in the maze.
     */
    public MazeBug() {
        setColor(Color.GREEN);
        last = null;
        next = null;
    }

    /**
     * Set if move with probability or not.
     *
     * @param probabilityEnabled probability is enabled to be used or not
     */
    public void setProbabilityEnabled(boolean probabilityEnabled) {
        isProbabilityEnabled = probabilityEnabled;
    }

    /**
     * Set if move randomly or not.
     *
     * @param randomlyEnabled random is enabled to be used or not
     */
    public void setRandomlyEnabled(boolean randomlyEnabled) {
        isRandomlyEnabled = randomlyEnabled;
    }

    /**
     * Moves to the next location in the maze.
     */
    public void act() {
        boolean willMove = canMove();
        if (isEnd && !hasShown) {
            String msg = stepCount.toString() + " steps";
            JOptionPane.showMessageDialog(null, msg);
            hasShown = true;
        } else if (willMove) {
            move();
            stepCount++;
        }
    }

    /**
     * Find all positions that can be move to.
     *
     * @param loc the location to detect
     * @return list of positions
     */
    private ArrayList<Location> getValid(Location loc) {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return null;
        }
        ArrayList<Map.Entry<Location, Integer>> validPair = new ArrayList<>();
        for (int i = 0; i < Location.FULL_CIRCLE; i += Location.RIGHT) {
            Location locNext = loc.getAdjacentLocation(i);
            if (!gr.isValid(locNext)) {
                continue;
            }
            Actor actor = gr.get(locNext);
            if (actor == null || actor instanceof Flower || (actor instanceof Rock && actor.getColor().equals(Color.RED))) {
                validPair.add(new AbstractMap.SimpleEntry<>(locNext, probability[i / Location.RIGHT]));
            }
        }
        return sortArrayListRandomly(getValidWithProbability(validPair));
    }

    /**
     * Find all position that can be move to.
     * The return values may be sorted by the probability if isProbabilityEnabled is true.
     *
     * @param loc the location to detect
     * @return list of positions and probability
     */
    private ArrayList<Location> getValidWithProbability(ArrayList<Map.Entry<Location, Integer>> validPair) {
        ArrayList<Location> valid = new ArrayList<>();
        if (isProbabilityEnabled) {
            validPair.sort(Map.Entry.comparingByValue());
            for (int i = validPair.size() - 1; i >= 0; i--) {
                valid.add(validPair.get(i).getKey());
            }
        } else {
            for (int i = 0; i < validPair.size(); i++) {
                valid.add(validPair.get(i).getKey());
            }
        }
        return valid;
    }

    /**
     * Sort the array list randomly.
     *
     * @param valid the array list to be processed
     * @return the array list randomly
     */
    private ArrayList<Location> sortArrayListRandomly(ArrayList<Location> valid) {
        if (!isRandomlyEnabled) {
            return valid;
        }
        ArrayList<Location> validRandomly = new ArrayList<>();
        Random random = new Random();
        while (!valid.isEmpty()) {
            int index = random.nextInt(valid.size());
            validRandomly.add(valid.get(index));
            valid.remove(index);
        }
        return validRandomly;
    }

    /**
     * Initialize before canMove method.
     */
    private ArrayList<Location> canMoveInit() {
        if (last == null) {
            ArrayList<Location> firstList = new ArrayList<>();
            firstList.add(getLocation());
            firstList.add(null);
            crossLocation.push(firstList);
        }
        if (crossLocation.empty() || isEnd) {
            return null;
        }
        Grid gr = getGrid();
        if (gr == null) {
            return null;
        }
        last = getLocation();
        return getValid(getLocation());
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     *
     * @return true if this bug can move.
     */
    public boolean canMove() {
        ArrayList<Location> valid = canMoveInit();
        if (valid == null || valid.isEmpty()) {
            return false;
        }
        ArrayList<Location> lastCross = crossLocation.peek();
        for (Location loc : valid) {
            Actor actor = (Actor) getGrid().get(loc);
            if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
                next = loc;
                isEnd = true;
                return false;
            }
            if (!lastCross.contains(loc)) {
                lastCross.add(loc);
                next = loc;
                ArrayList<Location> nextCross = new ArrayList<>();
                nextCross.add(next);
                nextCross.add(last);
                crossLocation.push(nextCross);
                return probabilityAdd();
            }
        }
        next = lastCross.get(1);
        crossLocation.pop();
        return probabilitySub();
    }

    public boolean probabilityAdd() {
        int direction = getLocation().getDirectionToward(next);
        if (direction != getDirection()) {
            probability[direction / Location.RIGHT]++;
        }
        return true;
    }

    public boolean probabilitySub() {
        int direction = getLocation().getDirectionToward(next);
        if (next != null && direction != getDirection()) {
            probability[direction / Location.RIGHT]--;
            return true;
        }
        return next != null;
    }

    /**
     * Moves the bug forward, putting a flower into the location it previously
     * occupied.
     */
    public void move() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        if (gr.isValid(next)) {
            setDirection(getLocation().getDirectionToward(next));
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
}
