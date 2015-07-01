package Game;

import AgentManager.AgentManager;
import Bullet.Bullet;
import Enemy.Enemy;
import Grid.Grid;
import Math.Point2D;
import Tower.SomeTower;
import Tower.Tower;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class TowerGame implements Game
{
    private int worldWidth; // coordinate translator
    private int worldHeight; // coordinate translator
    
    private AgentManager agentManager;
    
    //agentManager
    
    ArrayList <Point2D> targetPoints = new ArrayList<Point2D>();
    
    int life;
    Grid grid;
    int money;
    private int mouseX;
    private int mouseY;
    private boolean placeTower;
    private boolean start;
    boolean offButton;
    boolean gameOver;
    
    Tower towerGen;
    
    double rero = 0;
    double rera = 0;
    
    // all ovject data base here
    private BufferedImage bgImage;
    private BufferedImage towerImage;
    private BufferedImage enemyImage;
    private BufferedImage startButtonImage;
    private BufferedImage addButtonImage;
    private BufferedImage resetButtonImage;
    private BufferedImage bulletImage;
    private BufferedImage gameOverImage;
    private BufferedImage quitButtonImage;
    
    
    
    /**
     * Create the game
     *
     * @param worldWidth the width of the world
     * @param worldHeight the height of the world
     */
    public TowerGame(int worldWidth, int worldHeight) throws IOException
    {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        offButton = false;
        
        game_init();
    }

    /**
     * Initalizes all the variables used in the class
     * @throws IOException 
     */
    @Override
    public void game_init() throws IOException {
        
        agentManager = new AgentManager();
        
        gameOver = false;
        
        mouseX = 0;
        mouseY = 0;
        
        start = false;
        placeTower = false;
        
        towerGen = new SomeTower(new Point2D(0,0));
        
        grid = new Grid();
        grid.initGrid(); // prepares the non placable areas
        
        money = 200;
        life = 100;
        
        bgImage =  ImageIO.read(getClass().getResource("/Images/background.png"));
        towerImage =  ImageIO.read(getClass().getResource("/Images/b_tower.png"));
        enemyImage =  ImageIO.read(getClass().getResource("/Images/m.png"));
        startButtonImage =  ImageIO.read(getClass().getResource("/Images/start_button.png"));
        addButtonImage =  ImageIO.read(getClass().getResource("/Images/add_button.png"));
        bulletImage =  ImageIO.read(getClass().getResource("/Images/bullet.png"));
        resetButtonImage = ImageIO.read(getClass().getResource("/Images/reset_button.png"));
        gameOverImage = ImageIO.read(getClass().getResource("/Images/gameover.png"));
        quitButtonImage = ImageIO.read(getClass().getResource("/Images/quit_button.png"));
        
    }
    
    /**
     * Handles Mouse Move
     * @param e 
     */
    @Override
    public void handleMouseMove(MouseEvent e) {
        if (placeTower == false) {
            return;
        } else {
            // update the tower on mouse coordinates
            towerGen.getPoint2D().set(new Point2D(e.getX(), e.getY()));
        }
    }
    
    /**
     * Handles Mouse Click
     * @param e 
     */
    @Override
    public void handleMousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        
        // if quit
        if (mouseX>= 0 && mouseX<= 0 + 116 && mouseY >= 0 && mouseY <= 22) {
                offButton = true;
        }
        
        // if click on START button
        if (mouseX>= 220 && mouseX<= 220 + 116 && mouseY >= 0 && mouseY <= 22) {
                start = true;
        }
        // if click on RESET button
        if (mouseX>= 350 && mouseX<= 350 + 116 && mouseY >= 0 && mouseY <= 22) {
            try {
                reset();
            } catch (IOException ex) {
                Logger.getLogger(TowerGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
        // if clicked on ADD TOWER button,
        if (mouseX>= 612 && mouseX<= 612 + 116 && mouseY >= 0 && mouseY <= 22) {
                placeTower = true;
                // turn the mouseOnTower to true
            } else if (placeTower == true){
                //click on map
                // if spot is available (true) for tower,
                // add tower to TOWERS_TO_DRAW list
                placeTower = false; // or if statement?
                
                if (money - towerGen.getCost() >= 0 &&
                    grid.isOccupied(mouseX, mouseY) != true) {
                    grid.addOccupied(mouseX, mouseY);
                    // multiplied by 100 since the number is the array locations
                    agentManager.addTower(new SomeTower(new Point2D(grid.getLastX() * 100, grid.getLastY() * 100))); // must figure how to add it into the spot exactly......
                    money -= towerGen.getCost();
                    placeTower = false;
                    
                }
                
        }
    }
    
    /**
     * Update the game to the next frame
     */
    @Override
    public void update()
    {
        if (start == true) {
            agentManager.update();
        }   
        
        // enemy has 100 health....., remove!!!
        
        ArrayList<Integer> markForDeletion1 = new ArrayList<Integer>();
        for (Bullet b : agentManager.getBulletList()) {
            for (int count = 0; count < agentManager.getEnemyList().size() ;count++) {
                
                if (b.getPoint2D().getX()+40 >= agentManager.getEnemy(count).getPoint2D().getX() &&
                    b.getPoint2D().getX()+40 <= agentManager.getEnemy(count).getPoint2D().getX() + 79 &&
                    b.getPoint2D().getY() + 40 >= agentManager.getEnemy(count).getPoint2D().getY() &&
                    b.getPoint2D().getY() + 40 <= agentManager.getEnemy(count).getPoint2D().getY() + 94) {
                    markForDeletion1.add(count);
                    agentManager.getEnemyList().remove(count);
                    
                    
                }
               
            }
        }
        
        
        for (int x = markForDeletion1.size() - 1; x > -1; x--) {
            money+=20;
        }
        
        
        // remove after hitting wall
        markForDeletion1 = new ArrayList<Integer>();
        for (int x = 0; x < agentManager.getBulletList().size() - 1; x++) {
            if (agentManager.getBullet(x).getPoint2D().getX() > worldWidth + 20 ||
                agentManager.getBullet(x).getPoint2D().getX() < 0 - 20 ||
                agentManager.getBullet(x).getPoint2D().getY() > worldHeight + 20 ||
                agentManager.getBullet(x).getPoint2D().getY() < 0 - 20) {
                    markForDeletion1.add(x);
                }
            }
        
        
        for (int x = markForDeletion1.size() - 1; x > -1; x--) {
            agentManager.getBulletList().remove((int)markForDeletion1.get(x));
            
        }
        
        // when bullet reaches off screen, delete from list and detete the sprite
        // when monster's detination list is completed, remove monster from list and delete sprite and remove money
        // when monster's health is 0 or less, delete from monster list and delete sprite and add money
    }
    
    /**
     * Draws onto screen
     * @param graphicsList 
     */
    @Override
    public void draw(ArrayList<Graphics> graphicsList)
    {
         ArrayList<Graphics2D> g2dList = new ArrayList<Graphics2D>();
       
        // store the Graphics into a Graphics2D array list and store them as Graphics2D
        for(int x = 0; x < graphicsList.size(); x++) {
            g2dList.add((Graphics2D)graphicsList.get(x));
        }
        
        // background map
        g2dList.get(1).drawImage(bgImage, 0, 0, Color.yellow, null);
        
        // tower on mouse
        if (placeTower == true) {
            g2dList.get(0).drawImage(towerImage, (int)towerGen.getPoint2D().getX() - 40, (int)towerGen.getPoint2D().getY() - 50, null, null);
            
            g2dList.get(0).drawOval((int)towerGen.getPoint2D().getX() - towerGen.getRange()/2,
                    (int)towerGen.getPoint2D().getY() - towerGen.getRange()/2, 
                    towerGen.getRange(), towerGen.getRange());
            
        } 
        // drawing towers
        ArrayList <Tower> tList = agentManager.getTowerList();
        
        if(tList.isEmpty() == false) {
            for (int x = 0; x < tList.size(); x++) {
                g2dList.get(2).drawImage(towerImage, (int)tList.get(x).getPoint2D().getX(), (int)tList.get(x).getPoint2D().getY(), null, null);
                
                if (start == true) {
                    for (Enemy villian : agentManager.getEnemyList()) {
                        double distX = villian.getPoint2D().getX() - tList.get(x).getPoint2D().getX();
                        double distY = villian.getPoint2D().getY() - tList.get(x).getPoint2D().getY();
                        
                        distX *= distX;
                        distY *= distY;
                        
                        rero = Math.sqrt(distX + distY) ;
                        rera = tList.get(x).getRange();
                        
                        if (rero < tList.get(x).getRange()) {
                            if (tList.get(x).readyToFire(villian) == true) {
                                Bullet bullet;
                                bullet = tList.get(x).shootBullet(villian);
                                agentManager.addBullet(bullet);
                            }
                        }
                    }
                }
            }
        }
        
        // starts enemies and deleting enemies
        ArrayList <Integer> markForDeletion = new ArrayList <Integer>();
        
        if (start == true) { 
            ArrayList <Bullet> bList = agentManager.getBulletList();
            if(agentManager.getBulletList().isEmpty() == false) {
                for (int x = 0; x < bList.size() ; x++) {
                    g2dList.get(2).drawImage(bulletImage, (int)bList.get(x).getPoint2D().getX(), (int)bList.get(x).getPoint2D().getY(), null, null);
                }
            }
            
            //here
            for (int x = 0; x < agentManager.getEnemyList().size(); x++) {
                g2dList.get(2).drawImage(enemyImage, (int)agentManager.getEnemy(x).getPoint2D().getX() - 40, (int)agentManager.getEnemy(x).getPoint2D().getY() - 40, Color.yellow, null);
                if (agentManager.getEnemy(x).getSizeOfDestinationList() == 0)  {
                    markForDeletion.add(x);
                    //System.out.println("was marked for agent deletion: " + markForDeletion.get(x));
                } 
                
                if (agentManager.getEnemyList().isEmpty()) {
                    start = false;
                }
            }
        }
        
        for (int x = markForDeletion.size() - 1; x > -1; x--) {
            agentManager.getEnemyList().remove((int)markForDeletion.get(x));
            if (agentManager.getEnemyList().size() == 1) {
                agentManager.getEnemyList().remove(0);
            }
            if (gameOver != true) {
                  life -= 10;
            }
        }
        
        if (life <= 0) {
            gameOver = true;
        }
        
        if (gameOver == true) {
            g2dList.get(2).drawImage(gameOverImage, 250, 250, Color.yellow, null);
        }
        
        
        
        // buttons
        g2dList.get(2).drawImage(addButtonImage, 612, 0, Color.yellow, null);
        g2dList.get(2).drawImage(startButtonImage, 220, 0, Color.yellow, null);
        g2dList.get(2).drawImage(resetButtonImage, 350, 0, Color.yellow, null);
        g2dList.get(2).drawImage(quitButtonImage, 0, 0, Color.yellow, null);
        
        g2dList.get(2).drawString("Money: " + money, 0, 40);
        g2dList.get(2).drawString("Life left: " + life, 0, 60);
            
            
            // add lives here

            
    }
        
   // reset here with reset button setting all things back to default

    /**
     * Re-initializes the variables
     * @throws IOException 
     */
    @Override
    public void reset() throws IOException {
        game_init();
    }
    
    /**
     * Returns the money of the game
     * @return 
     */
    public int getMoney() {
        return money;
    }
    
    /**
     * Returns the grid of the game
     * @return 
     */
    public Grid getGrid() {
        return grid;
    }
    
    /**
     * Returns the general tower of the game
     * @return 
     */
    public Tower getTowerGen() {
        return towerGen;
    }

    @Override
    public boolean done()
    {
        if (offButton == true) {
            return true;
        }
        return false;
    }
}
