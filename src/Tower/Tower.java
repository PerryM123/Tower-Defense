package Tower;


import Bullet.Bullet;
import Enemy.Enemy;
import Math.Point2D;
import Math.Vector2D;


/**
 *
 * @author Perry
 */
public interface Tower {
    Bullet shootBullet(Enemy e);

    String getName();
    
    Point2D getPoint2D();
    
    Vector2D getVector2D();
    
    int getSize();
    
    int getRange();
    
    int getCost();
    
    
    Boolean readyToFire(Enemy e);
    
}
