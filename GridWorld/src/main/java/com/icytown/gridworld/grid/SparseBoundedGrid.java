package com.icytown.gridworld.grid;

import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A SparseBoundedGrid implemented by ArrayList and LinkedList.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E> {
    private ArrayList<LinkedList<OccupantInCol<E>>> arrayRows;
    private int maxRow;
    private int maxCol;

    /**
     * The occupant entry.
     */
    public class OccupantInCol<E> {
        private E occupant;
        private int col;

        public OccupantInCol(E obj, int objCol) {
            occupant = obj;
            col = objCol;
        }

        public int getCol() {
            return col;
        }

        public E getOccupant() {
            return occupant;
        }
    }

    /**
     * Construct a sparse bounded grid.
     * @param rows the rows initially
     * @param cols the cols initially
     */
    public SparseBoundedGrid(int rows, int cols) {
        arrayRows = new ArrayList<>(rows);
        for (int i = 0; i < rows; i++) {
            arrayRows.add(new LinkedList<OccupantInCol<E>>());
        }
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
        E oldOccupant = get(loc);
        arrayRows.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
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
        E r = get(loc);
        LinkedList<OccupantInCol<E>> target = arrayRows.get(loc.getRow());
        for (OccupantInCol<E> item : target) {
            if (item.getCol() == loc.getCol()) {
                target.remove(item);
                break;
            }
        }
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
        LinkedList<OccupantInCol<E>> target = arrayRows.get(loc.getRow());
        for (OccupantInCol<E> item : target) {
            if (item.getCol() == loc.getCol()) {
                return item.getOccupant();
            }
        }
        return null;
    }

    /**
     * Get all the locations which there is an occupant.
     * @return the array list of locations
     */
    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<>();
        for (int i = 0; i < arrayRows.size(); i++) {
            for (OccupantInCol<E> item : arrayRows.get(i)) {
                theLocations.add(new Location(i, item.getCol()));
            }
        }
        return theLocations;
    }
}
