package Enemy;

import Math.Point2D;
import Math.Vector2D;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Perry
 */
public class SomeEnemy implements Enemy {
    
    Point2D point;
    Vector2D vector;
    String name;
    int health;
    LinkedList<Point2D> destinationList;
    int count = 0;
    
    public SomeEnemy(Point2D p) {
        point = p;
        
        vector = new Vector2D(0,2);
        
        destinationList = new LinkedList<Point2D>();
        
        destinationList.add(new Point2D(650, 550));
        destinationList.add(new Point2D(650, 350));
        destinationList.add(new Point2D(150, 350));
        destinationList.add(new Point2D(150, 160));
        destinationList.add(new Point2D(900, 160));
        
    }
    
    @Override
    public int getSizeOfDestinationList() {
        return destinationList.size();
    }
    
    public void destinationReached() {
        destinationList.remove(0);
    }
    
    @Override
        public Point2D getNextDestination() {
            if (destinationList.size() == 0) {
                
            }
            return destinationList.get(count);

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
    public int getHealth() {
        return health;
    }
    
    @Override
    public void update() {
        
        if (destinationList.isEmpty() == false) {
            vector.set(destinationList.get(0).minus(point));
            vector.normalize();
            vector.scalePlusEquals(2, vector); // scale plus to adjust speed

            vector = destinationList.get(0).minus(point);
            vector.normalize();        

            // scale plus to adjust speed
            point.scalePlusEquals(2, vector);

            if ((int)point.getX() == (int)destinationList.get(0).getX() &&
                    point.getY() == destinationList.get(0).getY()) {
                
                     // if there aren't anymore destinations......
                    destinationList.remove(0);
            }

        }
    }
}
