package BackWork;





import Game.Game;
import AgentManager.AgentManager;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author mebjc01
 *
 * This class encapsulates a graphics loop for a full-screen game.
 *
 * The class implements Runnable, and is intended to be used as
 * a thread.
 *
 */
class UserInterfaceController extends JFrame implements Runnable, MouseMotionListener, MouseListener
{   
    Timer t;
    
    private Game game;
    private GraphicsEnvironment genv;
    private GraphicsDevice gdev;

    private int width; // width of screen
    private int height; // height of screen
    private int depth;
    
    long time;
    long last_frame;
    long new_frame;
    
    String stext;
    
    AgentManager ballManager;
    
    /**
     * Create an instance of the class with the specified screen
     * configuration using the given game.
     * 
     * @param width the width of the desired screen resolution
     * @param height the height of the desired screen resolution
     * @param depth the color depth of the desired screen resolution
     * @param theGame the game to use in the update loop
     * @throws VideoConfigurationException if the desired video mode is
     *         not available or if fullscreen mode is not allowed
     */
    
    public UserInterfaceController(int width, int height, int depth, Game theGame) 
            throws VideoConfigurationException
    {
        stext = "";
        time = 0;
        last_frame = 0;
        t = new Timer();
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.game = theGame;
        
        
        ballManager = new AgentManager();
        
        genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        gdev = genv.getDefaultScreenDevice();

        if(!hasResolution(width, height, depth))
            throw new VideoConfigurationException("unsupported resolution:");

        if(!hasFullScreen())
            throw new VideoConfigurationException("fullscreen unsupported");

        setResizable(false);
        setUndecorated(true);

        setIgnoreRepaint(true);
    }

    /**
     * Determines whether fullscreen mode is available
     * @return true if fullscreen is available
     */
    private boolean hasFullScreen()
    {
        return gdev.isFullScreenSupported();
    }

    /**
     * Determines whether the specified resolution is available
     * @param width the desired width
     * @param height the desired height
     * @param bitDepth the desired color depth
     * @return true if the specifed resolution is available
     */
    private boolean hasResolution(int width, int height, int bitDepth)
    {
        DisplayMode[] modes = gdev.getDisplayModes();

        for(int i = 0; i < modes.length; i++)
        {
            int w = modes[i].getWidth();
            int h = modes[i].getHeight();
            int b = modes[i].getBitDepth();

            if(width == w && height == h && bitDepth == b)
                return true;
        }

        return false;
    }
 
  
    
    
    /**
     * This method is the process of the loop.  After changing to the
     * specified resolution, the method executes the game as an
     * update loop.  When the game indicates it is done, the loop
     * terminates.
     */
    
    public void run()
    {
        // Save the old resolution so we can go back when we are done.
        DisplayMode oldMode = gdev.getDisplayMode();        
        addMouseMotionListener(this);
        addMouseListener(this);

        try
        {
            gdev.setFullScreenWindow(this);
            gdev.setDisplayMode(new DisplayMode(width, height, depth, DisplayMode.REFRESH_RATE_UNKNOWN));

            this.createBufferStrategy(2);

            BufferStrategy bufStrat = this.getBufferStrategy();
            
            while (!game.done())
            {
                t.tick();
                
                Graphics backgroundG = bufStrat.getDrawGraphics();
                Graphics text = bufStrat.getDrawGraphics();
                ArrayList <Graphics> graphicsList = new ArrayList<Graphics>();

                // for each ball we have, we create a graphic corressponding to each ball
                /*
                 * for (Ball b : ballManager.getAllBalls()) {
                    Graphics gBall = bufStrat.getDrawGraphics();
                    graphicsList.add(gBall);
                }*/
                
                // ^
                // |
                
                Graphics tower1 = bufStrat.getDrawGraphics();
                
                graphicsList.add(tower1);
                
                graphicsList.add(backgroundG);
                Graphics2D g2d = (Graphics2D) text;
                
                graphicsList.add(text);
                //System.out.println("size of graphics list is: " + graphicsList.size());
                
                // Calls update
                // Adds the speed to the x or y of the player Agent
                game.update();


                // draws main graphic on screen
                game.draw(graphicsList);

                /*
                Graphics2D g2d = (Graphics2D) text;
                // g2d.drawString("FPS: " + t.getFPS(), 20, 20);
                g2d.drawString(stext, 20, 20);
                */
                
                bufStrat.show();

                new_frame = System.currentTimeMillis();
                try {
                    Thread.sleep((long)t.getDelta());
                } catch (InterruptedException ex) {
                    Logger.getLogger(UserInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }             
        // A "finally" clause will always execute even if an unhandled
        // exception causes the program to terminate.  We use this to
        // make sure we "clean up" the video
        finally {
            // Before we exit, we want to change back to the original
            // video mode.
            gdev.setDisplayMode(oldMode);
            // This command returns the screen to non-full-screen.
            gdev.setFullScreenWindow(null);
        }

    }
    
    
    @Override
    public void mouseMoved(MouseEvent e) {
        stext = "mouseMoved";
         game.handleMouseMove(e);
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        stext = "mousePressed";
       game.handleMousePressed(e);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
        
    }

    

}
