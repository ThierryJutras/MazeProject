/**
 * Made by Thierry Jutras
 * 2018
 */

package vue;

import Common.Orientation;
import model.object.maze.Connection;
import model.object.maze.Leaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import static java.lang.Math.PI;

public class HexagonView extends JPanel implements MouseListener {
    private final HashMap<Orientation, Boolean> walls = new HashMap<>(6);
    private final Point[] hexApex = new Point[6];
    private Color cellColor = Color.CYAN;

    public HexagonView(Leaf model) {
        setOpaque(false);
        setBackground(new Color(0,0,0,255));
        boolean defaultValue = true;

        walls.put(Orientation.NORTH_WEST, defaultValue);
        walls.put(Orientation.NORTH_EAST, defaultValue);
        walls.put(Orientation.EAST, defaultValue);
        walls.put(Orientation.WEST, defaultValue);
        walls.put(Orientation.SOUTH_EAST, defaultValue);
        walls.put(Orientation.SOUTH_WEST, defaultValue);

        for (Connection conn: model.getPassages()) {
            walls.replace(conn.getDirection(), false);
        }

        for (int i = 0; i < 6; i++) {
            hexApex[i] = new Point();
        }

        this.setSize(100, 100);
        refreshApexCoordinate();
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        fillHexagon(cellColor ,g);

        if (walls.get(Orientation.NORTH_WEST)){
            drawHexLine(0, 1, g);
        }
        if (walls.get(Orientation.NORTH_EAST)){
            drawHexLine(0, 5, g);
        }
        if (walls.get(Orientation.EAST)){
            drawHexLine(5, 4, g);
        }
        if (walls.get(Orientation.WEST)){
            drawHexLine(1, 2, g);
        }
        if (walls.get(Orientation.SOUTH_WEST)){
            drawHexLine(2, 3, g);
        }
        if (walls.get(Orientation.SOUTH_EAST)){
            drawHexLine(4, 3, g);
        }
    }

    private void drawHexLine(int startingApex, int endindApex, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(hexApex[startingApex].x, hexApex[startingApex].y, hexApex[endindApex].x, hexApex[endindApex].y);
    }

    private void fillHexagon(Color fillColor, Graphics g) {
        Color tmp = g.getColor();
        g.setColor(fillColor);
        int[] pointsX = new int[6];
        int[] pointsY = new int[6];

        for (int i = 0; i < 6; i++) {
            pointsX[i] = hexApex[i].x;
            pointsY[i] = hexApex[i].y;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.fillPolygon(pointsX, pointsY, 6);
        g2.drawPolygon(pointsX, pointsY, 6);
        g.setColor(tmp);
    }

    private void refreshApexCoordinate() {
        /*   0
             /\
          1 /  \ 5
           |    |
           |    |
          2 \  / 4
             \/
             3
         */

        int[] centerPanel = {this.getWidth()/2, this.getHeight() / 2};
        int radius = this.getWidth()/2;

        for (int i = 0; i < 6; i++) {
            int xval = (int) (centerPanel[1] + radius * Math.sin(i * 2 * PI / 6d + PI));
            int yval = (int) (centerPanel[0] + radius * Math.cos(i * 2 * PI / 6d + PI));
            hexApex[i].x = xval;
            hexApex[i].y = yval;
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        cellColor = Color.LIGHT_GRAY;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        cellColor = Color.gray;
    repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        cellColor = Color.CYAN;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
