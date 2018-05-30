/**
 * Made by Thierry Jutras
 * 2018
 */

package model.object.maze;

import Common.Orientation;

import java.util.HashMap;

public class Maze {

    protected final HexagonalLeaf[][] maze;

    public Maze(int height, int width) {
        maze = new HexagonalLeaf[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++){
                maze[row][column] = new HexagonalLeaf();
            }
        }
        initialiseConnection();
    }

    private void initialiseConnection() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                maze[i][j].initConnection(getAllNeighbor(i, j));
            }
        }
    }

    private HashMap<Orientation, Leaf> getAllNeighbor(int i, int j) {
        HashMap<Orientation, Leaf> neighbor = new HashMap<>();

        addToNeighbor(neighbor, Orientation.NORTH_WEST, getNorthWestNeighbor(i,j));
        addToNeighbor(neighbor, Orientation.NORTH_EAST, getNorthEastNeighbor(i,j));
        addToNeighbor(neighbor, Orientation.SOUTH_WEST, getSouthWestNeighbor(i,j));
        addToNeighbor(neighbor, Orientation.SOUTH_EAST, getSouthEastNeighbor(i,j));

        if (j != 0) {
            addToNeighbor(neighbor, Orientation.WEST, maze[i][j-1]);
        }
        if (j < maze[0].length-1) {
            addToNeighbor(neighbor, Orientation.EAST, maze[i][j+1]);
        }

        return neighbor;
    }

    private void addToNeighbor(HashMap<Orientation, Leaf> neighbors, Orientation orientation,
                               Leaf leaf){
        if (leaf != null) {
            neighbors.put(orientation, leaf);
        }
    }

    private Leaf getSouthEastNeighbor(int row, int column) {
        if (row >= maze.length - 1 || (column == maze[row].length - 1 && !isPair(row))){
            return null;
        } else {
            return (isPair(row)) ? maze[row+1][column] : maze[row+1][column+1];
        }
    }

    private Leaf getSouthWestNeighbor(int row, int column) {
        if (row >= maze.length - 1 || (column == 0 && isPair(row))){
            return null;
        } else {
            return (isPair(row)) ? maze[row+1][column - 1] : maze[row+1][column];
        }
    }

    private Leaf getNorthWestNeighbor(int row, int column){
        if (row == 0 || (column == 0 && isPair(row))){
            return null;
        } else {
            return (isPair(row)) ? maze[row - 1][column - 1] : maze[row - 1][column];
        }
    }

    private Leaf getNorthEastNeighbor(int row, int column) {
        if (row == 0 || (column == maze[row].length - 1 && !isPair(row))){
            return null;
        } else {
            return (isPair(row)) ? maze[row - 1][column] : maze[row - 1][column + 1];
        }
    }

    private boolean isPair(int number){
        return number % 2 == 0;
    }

    public HexagonalLeaf[][] getLeafs() {
        return maze;
    }
}
