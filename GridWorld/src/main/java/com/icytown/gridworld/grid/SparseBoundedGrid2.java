package com.icytown.gridworld.grid;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A SparseBoundedGrid implemented by HashMap.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private Map<Location, E> occupantMap;
    private int maxRow;
    private int maxCol;

    /**
     * Construct a sparse bounded grid.
     * @param rows the rows initially
     * @param cols the cols initially
     */
    public SparseBoundedGrid2(int rows, int cols) {
        occupantMap = new HashMap<>();
        maxRow = rows;
        maxCol = cols;
    }

    /**
     * Get the number of rows
     * @return the number of rows
     */
    @Override
    public int getNumRows() {
        return maxRow;
    }

    /**
     * Get the number of cols
     * @return the number of cols
     */
    @Override
    public int getNumCols() {
        return maxCol;
    }

    /**
     * Check if the location is valid.
     * @return valid or not
     */
    @Override
    public boolean isValid(Location loc) {
        return (loc.getRow() >= 0 && loc.getRow() < maxRow) && (loc.getCol() >= 0 && loc.getCol() < maxCol);
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
        return occupantMap.put(loc, obj);
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
        return occupantMap.remove(loc);
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
        return occupantMap.get(loc);
    }

    /**
     * Get all the locations which there is an occupant.
     * @return the array list of locations
     */
    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<>();
        for (Location loc : occupantMap.keySet()) {
            theLocations.add(loc);
        }
        return theLocations;
    }
}
