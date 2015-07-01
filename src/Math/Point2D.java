/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math;
import java.util.Random;

/**
 *
 * @author miranda
 */

public class Point2D {
    private double x;
    private double y;
    public static final double TOL = 0.000000001; // 1.0E-8 
    
    /**
     * This constructor sets the point to the original
     */
    public Point2D() {
        x = 0;
        y = 0;
    }
    
    /**
     * Sets the Point object's x and y to the given x and y
     * 
     * @param x x-point
     * @param y y-point
     */
    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
        
    }
    
    
    /**
     * Sets this Point object's point to the given Point object
     * @param p 
     */
    public Point2D(Point2D p) {
        this.x = p.getX();
        this.y = p.getY();
    }
    
    /**
     * This overrides
     * Passes an object that is a Point2D object. If this Point2D object's x and y
     * equals to the given Point2D's x and y, return true, which means both 
     * objects are equal. Otherwise, both objects are not equal so, return false
     * 
     * @param obj passes a Point2D object in the form of a general object 
     * @return boolean
     */ 
    @Override
    public boolean equals(Object obj) {
        Point2D v = (Point2D) obj;
        
        double checkX = Math.abs((double)(x - v.getX()));
        double checkY = Math.abs((double)(y - v.getY()));
        
        if (checkX < TOL && checkY < TOL) {
            return true;
        }
        
        return false;
    }
    
    /**
     * Returns the point object's x-value
     * @return x
     */
    public double getX() {
        return x;
    }
    
    /**
     * Returns the point object's y-value
     * @return y
     */
    public double getY() {
        return y;
    }
    
    @Override
    public int hashCode() {
        int hash = 1;
        hash = 7 * hash + (int)x; // is casting a good idea here?
        hash = 7 * hash + (int)y;
        
        return hash;
    }
    
    public Vector2D minus(Point2D rhs) {        
        return new Vector2D(x - rhs.getX(), y - rhs.getY());
    }
    
    //actually domod
    public void modEquals(double xMax, double yMax) {
        
        
       if (x <= 0 && x < xMax) {
           x = xMax % x;
       } else {
           x = x % xMax;
       }
       
       if (y <= 0 && y < yMax) {
           y =  yMax % y;
       } else {
           y = y % yMax;
       }
       
    } 
    
    public Point2D plus(Vector2D rhs) {
        return new Point2D(x + rhs.getX(), y + rhs.getY());
    }
    
    public void plusEquals (Vector2D rhs) {
        x = x + rhs.getX();
        y = y + rhs.getY();
        
    }
    
    public static Point2D randomPoint(double xMin, double xMax, double yMin, double yMax) {
        double randX = (xMin + ( Math.random()*xMax)) ; 
        double randY = (yMin + ( Math.random()*yMax)) ; 
        
        return new Point2D(randX, randY);
    }
    
    public Point2D scalePlus(double scalar, Vector2D v) {
        double tmpX = x + scalar * v.getX();
        double tmpY = y + scalar * v.getY();
        
        return new Point2D(tmpX, tmpY);
    }
    
    public void scalePlusEquals (double scalar, Vector2D v) {
        x = x + scalar * v.getX();
        y = y + scalar * v.getY();
        
    }
    
    /**
     * Sets this Point2D object's x and y with the help of a given Point2D object
     * @param p 
     */
    public void set(Point2D p) {
        this.x = p.getX();
        this.y = p.getY();
        
    }
    
    /**
     * sets X
     * @param x 
     */
    public void setX(double x) {
        this.x = x;
        
    }
    
    /**
     * Sets Y
     * @param y 
     */
    public void setY(double y) {
        this.y = y;
        
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
