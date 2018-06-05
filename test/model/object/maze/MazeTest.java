package model.object.maze;

import Common.Orientation;
import model.generator.MazeGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void neighborsInitTest(){
        Maze maze = new Maze(5, 5);
        assertNotNull(maze.maze[0][0].getConnectedLeaf(Orientation.SOUTH_EAST));
        assertArrayEquals(new Connection[0], maze.maze[0][0].getConnectedLeaf(Orientation.SOUTH_EAST).getPassages());
    }

    @Test
    public void mazeIsComplete() {
        Maze maze = MazeGenerator.generate(1000, 1000);
        HexagonalLeaf[][] mazeLeafs = maze.getLeafs();
        int cellsInMaze = getLeafConnectedInMaze(mazeLeafs[0][0], Orientation.NORTH);
        assertEquals(1_000_000, cellsInMaze);
    }

    public int getLeafConnectedInMaze(Leaf leaf, Orientation visitedFrom ){
        int cellsConnected = 1;
        for (Connection passage: leaf.getPassages()) {
            if (passage.getDirection() != visitedFrom){
                cellsConnected += getLeafConnectedInMaze(passage.getConnectedLeaf(), passage.getDirection().getOpposite());
            }
        }
        return cellsConnected;
    }
}