package AgentManager;



import BackWork.LoadData;
import Math.Vector2D;
import Math.Point2D;
import Enemy.Enemy;
import java.util.ArrayList;
import Bullet.Bullet;
import Enemy.SomeEnemy;
import Tower.Tower;

public class AgentManager {
    public ArrayList <Enemy> enemyList;
    public ArrayList <Bullet> bulletList;
    public ArrayList <Tower> towerList; // but why?
    
    public AgentManager() {
        enemyList = new ArrayList<Enemy>();
        bulletList = new ArrayList<Bullet>();
        towerList = new ArrayList<Tower>();
        
        
        // how many enemies?
        
        // load data
        LoadData loadData = new LoadData();
        
        loadData.createData();
        
        int chosenNum = loadData.getAmountEnemy();
        System.out.println("num of agents: " + chosenNum);
        int space = -20;
        
        for (int x = 0; x < chosenNum; x++) {
            Enemy e = new SomeEnemy(new Point2D(space, 550));
            enemyList.add(e);
            
            space -=100;
        }
    }
    
    public ArrayList<Bullet> getBulletList() {
        return bulletList;
    }
    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }
    public ArrayList<Tower> getTowerList() {
        return towerList;
    }
    
    public Enemy getEnemy(int x) {
        return enemyList.get(x);
    }
    
    public Bullet getBullet(int x) { // ?
        return bulletList.get(x);
    }
    
    public Tower getTower(int x) {
        return towerList.get(x);
    }
    
    public void addEnemy(Enemy enemy) {
        enemyList.add(enemy);
    }
    
    public void addBullet(Bullet bullet) { // ?
        bulletList.add(bullet);
    }
    
    public void addTower(Tower tower) {
        towerList.add(tower);
    }
    
    /**
     * Updates the game of the agents' fatigue and hunger and vector(?)
     */
    public void update() {
        // enemies must update
        // bullets must update
        // towers don't update
        /*
        for (Tower t : towerList) {
            a.update();
        }
        */
        
        
        for (Enemy a : enemyList) {
            a.update();
        }
        
        // if size of list is greater than 0.... but isn't that by default? haha
        for (Bullet b : bulletList) {
            b.update();
        }
        
        
    }
}
