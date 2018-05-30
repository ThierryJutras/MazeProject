/**
 * Made by Thierry Jutras
 * 2018
 */

package vue;

import model.object.maze.Maze;

import javax.swing.*;

public class Board extends JPanel {

    public Board(Maze maze) {
        setLayout(null);
        setDoubleBuffered(true);
        setSize(1000, 1000);
        this.setSize(maze.getLeafs()[0].length * 90 + 44, maze.getLeafs().length * 78);
        for (int row = 0 ; row < maze.getLeafs().length; row++) {
            int offset = (row%2==0)? 0 : 44;
            for (int col = 0; col < maze.getLeafs()[row].length; col++) {
                HexagonView leafView = new HexagonView(maze.getLeafs()[row][col]);
                int debug = 0;
                leafView.setBounds(col * (88 + debug) + offset, row * (75 + debug), 100, 100);
                add(leafView);
            }
        }

    }
}
