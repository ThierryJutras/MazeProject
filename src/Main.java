import model.generator.MazeGenerator;
import model.object.maze.Maze;
import vue.HomeWindow;

/**
 Made by Thierry Jutras
 2018
 */

public class Main {

    public static void main(String[] args) {
        Maze maze = MazeGenerator.generate(10,15);
        HomeWindow homeWindow = new HomeWindow(maze);
        homeWindow.setVisible(true);
    }
}
