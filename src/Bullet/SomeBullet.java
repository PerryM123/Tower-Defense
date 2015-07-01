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
public class SomeBullet implements Bullet {
    Point2D point;
    Vector2D vector;
    String name;
    int damage;
    Point2D destination;
    
    // uses tower's point
    public SomeBullet(Point2D p) {
        point = new Point2D();
        point.set(p); // the villian's point
        vector = new Vector2D(0,0);
        destination = new Point2D();
        //vector
        
        
        System.out.println("some bullet created");
        
    }
    
    @Override
    public void setDestination(Point2D destPoint) {
        destination = destPoint;
        System.out.println("destination point is: " + destination.toString());
        
        vector = destination.minus(point);
                        vector.normalize();        
    }
    
    
    @Override
    public Point2D getPoint2D() {
        return point;
    }

    @Override
    public Vector2D getVector2D() {
        return vector;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getDamage() {
        return damage;
    }

    @Override
    public void update() {
       point.scalePlusEquals(5, vector);
    }

    @Override
    public Point2D getDestination() {
        return destination;
    }
    
}
