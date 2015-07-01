package Bullet;


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
public interface Bullet {
    Point2D getPoint2D();
    Vector2D getVector2D();
    String getName();
    int getDamage();
    void update();
    void setDestination(Point2D destPoint);
     Point2D getDestination();
    
}
