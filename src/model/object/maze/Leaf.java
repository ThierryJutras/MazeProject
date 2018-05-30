/**
 * Made by Thierry Jutras
 * 2018
 */

package model.object.maze;

import Common.Orientation;

import java.util.ArrayList;
import java.util.HashMap;

public interface Leaf extends Generable{

    Connection[] getPassages();

    ArrayList<Connection> getWalls();

    void initConnection(HashMap<Orientation, Leaf> leaf);

    Leaf getConnectedLeaf(Orientation orientation);


}
