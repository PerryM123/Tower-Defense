package Math;
/**
 *
 * @author miranda
 */

// http://www.cs.moravian.edu/~coleman/330/prog1/

// http://electron9.phys.utk.edu/vectors/math.htm

public class Vector2D {
    
    private double x;
    private double y;
    static double TOL  = 0.000000001; // 1.0E-8
    static Vector2D zero = new Vector2D(0,0);
    
    /**
     * Creates a new point at the origin
     */
    
    public Vector2D() {
        x = 0;
        y = 0;
        
    }
    
    
    /**
     * Creates a Vector2D object with the given x and y value
     *  
     * @param x x-point
     * @param y y-point
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
        
        //TOL is the threshold for determining whether a component "goes to zero." In any vector where |x| < TOL or |y| < TOL, then that value becomes zero. 
        
    }
    
    /**
     * Creates a vector based on the given vector
     * 
     * @param v a given Vector 
     */
    
    public Vector2D(Vector2D v) {
        x = v.getX();
        y = v.getY();       
        
    }
    
    /**
     * Finds the angle of the triangle and converts from radians to degrees
     * @return double the degrees of the triangle
     */
    public double angle() {
        double tan = Math.atan2(y,x);
        return tan;
    }
    
    public Vector2D divide(double d) {
        return new Vector2D(1/d * x, 1/d * y);
    }
    
    public void divideEquals(double d) {
        try {
            x = 1/d * x;
            y = 1/d * y;
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot Divide by Zero");
        }
        
    }
    
    public double dot(Vector2D rhs) {
        
        double tmpX = x;
        double tmpY = y;
        
        tmpX = tmpX * rhs.getX();
        tmpY = tmpY * rhs.getY();
        
        return (tmpX + tmpY);
    }
    
    @Override
    public boolean equals(Object obj) {
        Vector2D v = (Vector2D) obj;
        
        double checkX = Math.abs((double)(x - v.getX()));
        double checkY = Math.abs((double)(y - v.getY()));
        
        if (checkX < TOL && checkY < TOL) {
            return true;
        }
        
        return false;
    }
    
    public Vector2D getLeftOrtho() {
        return new Vector2D(x, -y);
    }
    
    // this is on page 21
    public Vector2D getNormalized() {
        double mag = magnitude();
        
        if (x == 0 && y == 0) {
            return new Vector2D(0,0);
        }
        
        x = x / mag;
        y = y / mag;
        
        return new Vector2D(x, y);
    }
    
    public Vector2D getRightOrtho() {
        return new Vector2D(-x,y);
    }
    
    /**
     * Return the x-value of the vector
     * @return x
     */
    public double getX() {
        return x;
    }
    
    /**
     * Return the y-value of the vector
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
    
    /**
     * Calculates and returns the magnitude 
     * 
     * Performs the Pythagorean Theorem with the x and y to find the hypotenuse
     * 
     * @return magnitude
     */
    public double magnitude() {
        return Math.sqrt((x * x) + (y * y));
    }
    
    /**
     * Maybe this is the magnitude in the form of c^2
     * @return 
     */
    public double magnitudeSq() {
        return (x * x) + (y * y);
    }
    
    /**
     * 
     * @param rhs right hand side
     * @return 
     */
    
     
    public Vector2D minus (Vector2D rhs) {
        
        return new Vector2D(x - rhs.getX(), y + (-1) * rhs.getY());
    }
    /**
     * 
     * @param rhs 
     */
    public void minusEquals(Vector2D rhs) {
        x = x - rhs.getX();
        y = y - rhs.getY();
        
    }
   
    public void negate() {
        set(-1 * this.getX(), -1 * this.getY());
    }
    
    public void normalize() {
        double mag = magnitude();
        
        if (x == 0 && y == 0) {
            return;
        }
        
        x = x / mag;
        y = y / mag;
        
    }    
    
    
    public Vector2D plus(Vector2D rhs) {
        return new Vector2D(x + rhs.getX(), y + rhs.getY());
    }
    
    public void plusEquals(Vector2D rhs) {
        x = x + rhs.getX();
        y = y + rhs.getY();
        
    }
    
    public static Vector2D randomVectorFixedMagnitude(double magnitude) {
        double a = Math.random() * magnitude;
       
        magnitude = (magnitude * magnitude);
        a = a * a;
        
        double b = magnitude - a;
        
        a = Math.sqrt(a);
        b = Math.sqrt(b);
        
        return new Vector2D(a, b);
    }
    
    public static Vector2D randomVectorMaxMagnitude(double maxMagnitude)  {
        double c =  Math.random() * maxMagnitude;
        
        double a = Math.random() * c;
       
        c = (c * c);
        a = a * a;
        
        double b = c - a;
        
        a = Math.sqrt(a);
        b = Math.sqrt(b);
        
        return new Vector2D(a, b);
    }
    
    /**
     * Changes the sign of the x
     * 
     * If x is negative, it will becomes positive
     * If x is positive, it will become negative
     */
    public void reflectX() {
        x = -x;
        
    }
    
    /**
     * Changes the sign of the y
     * 
     * If y is negative, it will becomes positive
     * If y is positive, it will become negative
     */
    public void reflectY() {
        y = -y;
        
    }
    
    public Vector2D scalePlus(double scalar, Vector2D v) {
        return new Vector2D(x + scalar * v.getX(), y + scalar * v.getY());
    }
    
    
    public void scalePlusEquals(double scalar, Vector2D v) {
        x = x + scalar * v.getX();
        y = y + scalar * v.getY();
        
    }
    /**
     * Set this vector's x and y to the given parameters
     * @param x - a given x-value
     * @param y - a given y-value
     */
    public void set(double x, double y) {
        this.x = x;
        this.y = y;
        
    }
    
    /**
     * Set's this vector equal to the given vector in the parameter
     * @param v a given vector
     */
    public void set(Vector2D v) {
        x = v.getX();
        y = v.getY();
        
    }
    
    public void setLeftOrtho() {
        y = -y;
        
    }
    
    public void setRightOrtho() {
        x = -x;
        
    }
    
    /**
     * Multiplies the given double by this Vector object. The result is returned in the form of a Vector object
     * @param d
     * @return 
     */
    public Vector2D times(double d) {
        return new Vector2D(x * d, y * d);
    }
    
    
    /**
     * This Vector object gets multiplied by the given double
     * @param d 
     */
    public void timesEquals(double d) {
        x = this.x * d;
        y = this.y * d;
        
    }
    
    /**
     * Returns the string from of the vector object 
     * Example: (3,1)
     * @return 
     */
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
    
    
    public void truncate(double max) {
        
        if (max > magnitude()) {
            double mag = Math.sqrt(x * x + y * y);
            if(mag > Double.MIN_VALUE && mag > max) {
                double f = max / mag;
                this.x *= f;
                this.y *= f;
            }
            
        }
        
        
        
        return;
    }
    
    
}
