/**
 * Made by Thierry Jutras
 * 2018
 */

package model.generator;

import model.object.maze.Connection;
import model.object.maze.Leaf;
import model.object.maze.Maze;

import java.util.ArrayList;
import java.util.Random;

public class MazeGenerator {

    public static Maze generate(int height, int width) {
        Random rng = new Random();
        Maze maze = new Maze(height, width);
        Leaf[][] mazeNodes = maze.getLeafs();
        ArrayList<Connection> wallList = new ArrayList<>(mazeNodes[mazeNodes.length/2][mazeNodes[0].length/2].getWalls());

        while (!wallList.isEmpty()){
            int rndIndex = rng.nextInt(wallList.size());
            Connection targetConn = wallList.get(rndIndex);
            Leaf targetLeaf = targetConn.getConnectedLeaf();
            if (!targetLeaf.isVisited()) {
                targetConn.createPassage();
                targetLeaf.visit(targetConn.getDirection().getOpposite());
                wallList.addAll(targetLeaf.getWalls());
            }
            wallList.remove(rndIndex);
        }
        return maze;
    }
}
