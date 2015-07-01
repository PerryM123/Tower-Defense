/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Game.TowerGame;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JFrame;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Perry
 */
public class TowerGameTest {
    
    @Test
    public void handleMousePressedTestWithoutTowers() throws IOException {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("handleMousePressedTestWithoutTowers");
        TowerGame g = new TowerGame(800, 600);
        
        MouseEvent msOnButton;
        JFrame jframe = new JFrame();
        msOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,614,9,2,false );
        
        System.out.println("mouse: " + msOnButton.getX());
        System.out.println("mouse: " + msOnButton.getY());
        
        g.handleMousePressed(msOnButton);
        
        MouseEvent msNotOnButton;
        msNotOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,0,0,2,false );
        
        g.handleMousePressed(msNotOnButton);
        
        
    }
    
    @Test
    public void handleMousePressedTestTowers() throws IOException {
        // tests adding towers
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("handleMousePressedTestTowers");
        TowerGame g = new TowerGame(800, 600);
        System.out.println("Money: " + g.getMoney());
        MouseEvent msOnButton;
        JFrame jframe = new JFrame();
        msOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,1,0,2,false );
        
        System.out.println("mouse: " + msOnButton.getX());
        System.out.println("mouse: " + msOnButton.getY());
        
        System.out.println("BEFORE");
        g.getGrid().printGrid();
        g.handleMousePressed(msOnButton);
        
        System.out.println("AFTER");
        g.getGrid().printGrid();
        System.out.println("Money: " + g.getMoney());
        
        msOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,799,599,2,false );
        System.out.println("BEFORE");
        g.getGrid().printGrid();
        g.handleMousePressed(msOnButton);
        System.out.println("AFTER");
        g.getGrid().printGrid();
        System.out.println("Money: " + g.getMoney());
        
    }
    
    @Test
    public void handleMouseMoveTest() throws IOException {
        // test moving mouse around
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("handleMouseMoveTest");
        TowerGame g = new TowerGame(800, 600);
        //g.towerOnMouse = true;
        MouseEvent msOnButton;
        JFrame jframe = new JFrame();
        
        msOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,614,9,2,false );
        g.handleMouseMove(msOnButton);
        System.out.println(g.getTowerGen().getPoint2D().toString());
        
        msOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,615,9,2,false );
        g.handleMouseMove(msOnButton);
        System.out.println(g.getTowerGen().getPoint2D().toString());
        
        msOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,616,9,2,false );
        g.handleMouseMove(msOnButton);
        System.out.println(g.getTowerGen().getPoint2D().toString());
        
        msOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,617,9,2,false );
        g.handleMouseMove(msOnButton);
        System.out.println(g.getTowerGen().getPoint2D().toString());
        
        msOnButton = new MouseEvent(jframe, MouseEvent.MOUSE_CLICKED,0, 0,618,9,2,false );
        g.handleMouseMove(msOnButton);
        System.out.println(g.getTowerGen().getPoint2D().toString());
        
    }
}
