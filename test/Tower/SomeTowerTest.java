/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tower;

import Bullet.Bullet;
import Bullet.SomeBullet;
import Enemy.Enemy;
import Enemy.SomeEnemy;
import Math.Point2D;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Perry
 */
public class SomeTowerTest {
    
    
    @Test
    public void other() {
        Bullet b1 = new SomeBullet(new Point2D(0,0));
        Point2D point = new Point2D(0,0);
        Tower sm = new SomeTower(point);
        
        Bullet b = new SomeBullet(point);
        
        Enemy e = new SomeEnemy(point);
        
        while (true) {
            System.out.println(sm.readyToFire(e));
        }
    }

}
