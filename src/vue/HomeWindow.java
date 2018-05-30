/**
 * Made by Thierry Jutras
 * 2018
 */

package vue;

import model.object.maze.Maze;

import javax.swing.*;

public class HomeWindow extends JFrame {

    public HomeWindow(Maze maze) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Board board = new Board(maze);
        board.setBounds(0,0, board.getWidth(), board.getHeight());
        setSize(board.getWidth(), board.getHeight() + 50);
        getContentPane().add(board);
    }
}
