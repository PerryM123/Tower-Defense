package Tower;


import Bullet.Bullet;
import Bullet.SomeBullet;
import Enemy.Enemy;
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
public class SomeTower implements Tower {
    private String name;
    Point2D point;
    Vector2D vector;
    int size;
    int range;
    int coolDownTime;
    long theLastOne;
    boolean fired;
    long recordedTime;
    int cost;
    
    public SomeTower(Point2D p) {
        name = "Some Tower";
        point = new Point2D(p);
        point = p;
        vector = new Vector2D(0, 0);
        coolDownTime = 5000;
        theLastOne = 0;
        fired = false;
        recordedTime = 0;
        cost = 20;
        range = 250;
    } 
   
    @Override
    public Boolean readyToFire(Enemy e) {
        // find the loc of enemy
        // make a bullet to go towards the enemy
        // enemy checks if a bullet is hitting him.... right?
        
        long timeDiff = System.currentTimeMillis() - recordedTime;
        
        if (timeDiff > 1000) {
            recordedTime = System.currentTimeMillis();
            
            return true;
        }
        
        if (timeDiff < 1000){
           // System.out.println("please wait!");
            // have to wait more until you can fire another bullet
            // in other class: if null, do not create bullet at all
            return false;
        }
            return true;
    }
    
    // private to towers?
    /**
     * Get the enemy's vector and fire a bullet towards it
     * Has cool down time for the bullet. Can fire a bullet every one second
     * @param e
     * @return 
     */
    @Override
    public Bullet shootBullet(Enemy e) {
        
        Point2D p = new Point2D(point);
        p.setX(point.getX() + 50);
        p.setY(point.getY() + 50);
        Bullet bull = new SomeBullet(p);
        bull.setDestination(e.getPoint2D());
            return bull;
    }

    @Override
    public String getName() {
        return name;
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
    public int getSize() {
        return size;
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public int getCost() {
        return cost;
    }

    
}
