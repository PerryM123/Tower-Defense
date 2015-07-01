package Game;



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Perry
 */
public interface Game {
    
    /**
     * Update the Game state by one frame
     */
    public void update();

    
    /**
     * Draw the current state of the game
     *
     * @param g2d the graphics with which to draw
     */
   public void draw(ArrayList<Graphics> graphicsList);

   public void game_init()throws IOException;
   public void reset() throws IOException;

    /**
     * Determine whether the game is complete (the program should
     * terminate)
     * @return true if the game is complete
     */
    public boolean done();
    
    public void handleMouseMove(MouseEvent e);
    
    public void handleMousePressed(MouseEvent e);

}

