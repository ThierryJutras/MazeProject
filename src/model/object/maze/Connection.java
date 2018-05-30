/**
 * Made by Thierry Jutras
 * 2018
 */

package model.object.maze;

import Common.Orientation;
import model.object.id.ConnectionType;

public class Connection {

    private final Leaf connectedLeaf;
    private ConnectionType connectionType;
    private final Orientation direction;

    public Connection(Orientation orientation, Leaf connectedLeaf) {
        this.connectedLeaf = connectedLeaf;
        connectionType = ConnectionType.WALL;
        direction = orientation;
    }

    public void createPassage() {
        connectionType = ConnectionType.PASSAGE;
        connectedLeaf.visit(direction.getOpposite());
    }

    public Leaf getConnectedLeaf() {
        return connectedLeaf;
    }

    public boolean isPassage() {
        return connectionType == ConnectionType.PASSAGE;
    }

    public boolean isWall() {
        return connectionType == ConnectionType.WALL;
    }

    public Orientation getDirection() {
        return direction;
    }
}
