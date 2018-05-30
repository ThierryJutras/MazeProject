/**
 * Made by Thierry Jutras
 * 2018
 */

package model.object.maze;

import Common.Orientation;

public interface Generable {

    void visit(Orientation oposite);

    boolean isVisited();
}
