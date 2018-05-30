package model.object.maze;

import Common.Orientation;
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
}