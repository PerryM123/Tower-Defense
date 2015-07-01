package Enemy;


import Math.Point2D;
import Math.Vector2D;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Perry
 */
public interface Enemy {
    Point2D getPoint2D();
    Vector2D getVector2D();
    String getName();
    int getHealth();
    void update();
    Point2D getNextDestination();
    int getSizeOfDestinationList();
    
}
