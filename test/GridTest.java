/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Grid.Grid;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Perry
 */
public class GridTest {
    
    @Test
    public void contructorTest() {
        Grid grid = new Grid();
        
        boolean[][] tmpGrid = grid.getGrid();
        
        for (int x = 0; x < 6; x++) {
            for (int y = 0; y < 8; y++) {
                assertFalse(tmpGrid[x][y]);
            }
        }

    }
    
    @Test
    public void initTest() {
        System.out.println("initTest");
        Grid grid = new Grid();
        grid.initGrid();
        
        grid.printGrid();
    }
    
    /*
    @Test
    public void gridToCoordinatesTest() {
        Grid grid = new Grid();
        assertTrue(grid.gridToCoordinates(0) == 0);
        assertTrue(grid.gridToCoordinates(5) == 600);
    }
    */
    @Test
    public void coordinatesToGridTest() {
        Grid grid = new Grid();
        int xcoor = 500;
        int ycoor = 300;
        
        xcoor = grid.roundToCeilingOfHundred(xcoor);
        xcoor -= 100;
        xcoor = xcoor / 100;
        
        ycoor = grid.roundToCeilingOfHundred(ycoor);
        ycoor -= 100;
        ycoor = ycoor / 100;
        
        assertTrue(xcoor == 4);
        assertTrue(ycoor == 2);
        
        Grid grid2 = new Grid();
        xcoor = 741;
        ycoor = 555;
        
        xcoor = grid2.roundToCeilingOfHundred(xcoor);
        xcoor -= 100;
        xcoor = xcoor / 100;
        
        ycoor = grid.roundToCeilingOfHundred(ycoor);
        ycoor -= 100;
        ycoor = ycoor / 100;
        
        assertTrue(xcoor == 7);
        assertTrue(ycoor == 5);
        
    }

    @Test
    public void isOccupiedAndAddTest() {
        Grid grid = new Grid();
        System.out.println("=====ADD1=====");
        int XmouseClick = 300;
        int YmouseClick = 500;
        System.out.println("XmouseClick: " + XmouseClick);
        System.out.println("YmouseClick: " + YmouseClick);
        grid.addOccupied(XmouseClick, YmouseClick);
        grid.printGrid();
        boolean check = grid.isOccupied(XmouseClick, YmouseClick);
        
        assertTrue(check == true); // adds
        
        XmouseClick = 300;
        YmouseClick = 500;
        grid.addOccupied(XmouseClick, YmouseClick);
        grid.printGrid();
        check = grid.isOccupied(XmouseClick, YmouseClick);
        
        assertTrue(check == true);
        
        System.out.println("=====ADD2====="); // early escape
        XmouseClick = 0;
        YmouseClick = 0;
        System.out.println("XmouseClick: " + XmouseClick);
        System.out.println("YmouseClick: " + YmouseClick);
        grid.addOccupied(XmouseClick, YmouseClick);
        grid.printGrid();
        check = grid.isOccupied(XmouseClick, YmouseClick);
        
        assertTrue(check == true);
        
        System.out.println("=====REMOVE3====="); // remove
        XmouseClick = 0;
        YmouseClick = 0;
        System.out.println("XmouseClick: " + XmouseClick);
        System.out.println("YmouseClick: " + YmouseClick);
        grid.removeOccupied(XmouseClick, YmouseClick);
        grid.printGrid();
        check = grid.isOccupied(XmouseClick, YmouseClick);
        
        assertFalse(check);
        
        System.out.println("=====REMOVE4=====");
        XmouseClick = 252;
        YmouseClick = 500;
        System.out.println("XmouseClick: " + XmouseClick);
        System.out.println("YmouseClick: " + YmouseClick);
        grid.removeOccupied(XmouseClick, YmouseClick);
        grid.printGrid();
        check = grid.isOccupied(XmouseClick, YmouseClick);
        
        assertFalse(check);
        
        System.out.println("=====ADD5=====");
        XmouseClick = 350;
        YmouseClick = 500;
        System.out.println("XmouseClick: " + XmouseClick);
        System.out.println("YmouseClick: " + YmouseClick);
        grid.addOccupied(XmouseClick, YmouseClick);
        grid.printGrid();
        check = grid.isOccupied(XmouseClick, YmouseClick);
        
        assertTrue(check);
        
        System.out.println("=====REMOVE6=====");
        //XmouseClick = 350;
        //YmouseClick = 470;
        XmouseClick = 351;
        YmouseClick = 499;
        System.out.println("XmouseClick: " + XmouseClick);
        System.out.println("YmouseClick: " + YmouseClick);
        grid.removeOccupied(XmouseClick, YmouseClick);
        grid.printGrid();
        check = grid.isOccupied(XmouseClick, YmouseClick);
        
        assertFalse(check);
        
    }
    
    @Test
    public void roundToCeilingOfHundredTest() {
      Grid grid = new Grid();
      
      int xcoor = 0;
      xcoor = grid.roundToCeilingOfHundred(xcoor); // corner case
      assertTrue(xcoor == 0);
      
      xcoor = 500;
      xcoor = grid.roundToCeilingOfHundred(xcoor); // corner case
      assertTrue(xcoor == 500);
      
      xcoor = 677;
      xcoor = grid.roundToCeilingOfHundred(xcoor);
      assertTrue(xcoor == 700);
      
      xcoor = 800;
      xcoor = grid.roundToCeilingOfHundred(xcoor); // corner
      assertTrue(xcoor == 800);
      
      xcoor = 233;
      xcoor = grid.roundToCeilingOfHundred(xcoor);
      assertTrue(xcoor == 300);
  }
}
