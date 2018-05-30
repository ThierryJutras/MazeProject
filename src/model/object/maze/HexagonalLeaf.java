/**
 * Made by Thierry Jutras
 * 2018
 */

package model.object.maze;

import Common.Orientation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HexagonalLeaf implements Leaf {

    private final HashMap<Orientation, Connection> connections = new HashMap<>(5);
    private boolean isVisited;

    public HexagonalLeaf() {
        isVisited = false;
    }

    @Override
    public Connection[] getPassages() {
        ArrayList<Connection> passages = new ArrayList<>();
        for (Connection entry : connections.values()) {
            if (entry.isPassage()){
                passages.add(entry);
            }
        }
        return passages.toArray(new Connection[passages.size()]);
    }

    @Override
    public void initConnection(HashMap<Orientation, Leaf> connectedLeafs) {
        for (Map.Entry<Orientation, Leaf> connections : connectedLeafs.entrySet()) {
            if (connections.getKey() != Orientation.NORTH
                    && connections.getKey() != Orientation.SOUTH) {
                this.connections.put(connections.getKey(), new Connection(connections.getKey(), connections.getValue()));
            }
        }
    }

    @Override
    public Leaf getConnectedLeaf(Orientation orientation) {
        return connections.get(orientation).getConnectedLeaf();
    }

    @Override
    public void visit(Orientation visitFrom) {
        if (connections.get(visitFrom).isWall()) {
            connections.get(visitFrom).createPassage();
        }
        isVisited = true;
    }

    @Override
    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public ArrayList<Connection> getWalls() {
        ArrayList<Connection> walls = new ArrayList<>();
        for (Connection entry : connections.values()) {
            if (entry.isWall()){
                walls.add(entry);
            }
        }
        return walls;
    }
}
