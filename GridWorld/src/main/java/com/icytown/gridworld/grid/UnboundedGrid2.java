package com.icytown.gridworld.grid;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A UnboundedGrid implemented with the array changed dymatically.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> {
    private static final int INIT_BOUND = 16;

    private Object[][] occupantArray;
    private int maxBound;

    /**
     * Construct a unbounded grid.
     */
    public UnboundedGrid2() {
        maxBound = INIT_BOUND;
        occupantArray = new Object[maxBound][maxBound];
    }

    /**
     * Get the number of rows
     * @return the number of rows
     */
    @Override
    public int getNumRows() {
        return -1;
    }

    /**
     * Get the number of cols
     * @return the number of cols
     */
    @Override
    public int getNumCols() {
        return -1;
    }

    /**
     * Check if the location is valid.
     * @return valid or not
     */
    @Override
    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    /**
     * Put an object into the grid.
     * @param loc the location to put in
     * @param obj the object needed to put
     * @return the old object in this location
     */
    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        if (loc.getCol() >= maxBound || loc.getRow() >= maxBound) {
            int rawBound = maxBound;
            while (loc.getCol() >= maxBound || loc.getRow() >= maxBound) {
                maxBound *= 2;
            }
            Object[][] newArray = new Object[maxBound][maxBound];
            for (int i = 0; i < rawBound; i++) {
                System.arraycopy(occupantArray[i], 0, newArray[i], 0, rawBound);
            }
            occupantArray = newArray;
        }
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
     * Remove an object.
     * @param loc the location to remove
     * @return the object to be removed
     */
    @Override
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc.getCol() >= maxBound || loc.getRow() >= maxBound) {
            return null;
        }
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }

    /**
     * Get an object.
     * @param loc the location to get
     * @return the object
     */
    @Override
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc.getCol() >= maxBound || loc.getRow() >= maxBound) {
            return null;
        }
        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    /**
     * Get all the locations which there is an occupant.
     * @return the array list of locations
     */
    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<>();
        for (int r = 0; r < maxBound; r++) {
            for (int c = 0; c < maxBound; c++) {
                Location loc = new Location(r, c);
                if (get(loc) != null) {
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }
}

