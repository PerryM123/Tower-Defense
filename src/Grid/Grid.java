package Grid;

public class Grid {
  private boolean grid[][];
  int lastX;
  int lastY;
  
  public Grid() {
    grid = new boolean[6][8]; 
    lastX = 0;
    lastY = 0;
  }
  
  /**
   * Returns this object's 2D boolean representing the grid 
   * @return 
   */
  public boolean[][] getGrid() {
      return grid;
  }
  
  /*
   * 
   * false false false false false false false false 
    false TRUE TRUE TRUE TRUE TRUE TRUE TRUE 
    false TRUE false false false false false false 
    false TRUE TRUE TRUE TRUE TRUE TRUE false 
    false false false false false false TRUE false 
    TRUE TRUE TRUE TRUE TRUE TRUE TRUE false 
   */
  
  public int getLastX() {
      System.out.println("getLastX() - " + lastX);
      return lastX;
  }
  
  public int getLastY() {
      System.out.println("getLastY() - " + lastY);
      return lastY;
  }
  
  public double gridToCoordinates(int num) {
    num = num * 100;
    num = num + 100;
    
    return num;
  }
  
  public void initGrid() {
      grid[1][1] = true;
      grid[1][2] = true;
      grid[1][3] = true;
      grid[1][4] = true;
      grid[1][5] = true;
      grid[1][6] = true;
      grid[1][7] = true;
      
      grid[2][1] = true;
      
      grid[3][1] = true;
      grid[3][2] = true;
      grid[3][3] = true;
      grid[3][4] = true;
      grid[3][5] = true;
      grid[3][6] = true;
      
      grid[4][6] = true;
      
      grid[5][0] = true;
      grid[5][1] = true;
      grid[5][2] = true;
      grid[5][3] = true;
      grid[5][4] = true;
      grid[5][5] = true;
      grid[5][6] = true;
  }
  
  /**
   * Converts a screen coordinate into a grid coordinate
   * @param num a given screen coordinate
   * @return the resulting grid coordinate
   */
  public int coordinatesToGrid(int num) {
      num = roundToCeilingOfHundred(num);
      num = num - 100;
      num = num / 100;
      return num;
  }
  
  public boolean isOccupied(int x, int y) {
      //System.out.println("before, isOccupied x: " + x + ", y: " + y);
    if(x != 0) {
      x = coordinatesToGrid(x);
    } if(y != 0) {
      y = coordinatesToGrid(y);
    }
      //System.out.println("after, isOccupied x: " + x + ", y: " + y);
      //System.out.println("--------------------------------------------------------------------------" + x + ", " + y + " is " + grid[y][x]);
    if (grid[y][x] == true) {
        return true;
    } else {
        return false;
    }
 }
  
  public void printGrid() {
      for (int y = 0; y < 6; y++) {
          for (int x = 0; x < 8; x++) {
              if (grid[y][x] == true) {
                 System.out.print("TRUE ");
              } else {
                  System.out.print("false ");
              }
          }
          System.out.println("");
      }
      System.out.println("-------------------------------");
  }
  
  /**
   * Adds an occupied space resulting in an occupied space. 
   * Returns early if it is already occupied
   * 
   * @param screenX the x-coordinate of the screen coordinate
   * @param screenY the y-coordinate of the screen coordinate
   */
  public void addOccupied(int screenX, int screenY) {
      //System.out.println(isOccupied(screenX, screenY));
      if (isOccupied(screenX, screenY) == true) { // if false, right?
          // already occupied, return immediately
          //System.out.println("A: early escape");
          return;
      }
      
      if(screenX != 0) {
          //System.out.println("screenX before addOccupied: " + screenX);
        screenX = coordinatesToGrid(screenX);
          //System.out.println("screenX after addOccupied: " + screenX);
      }
        if (screenY != 0) {
          //System.out.println("screenX before addOccupied: " + screenY);
            screenY = coordinatesToGrid(screenY);
          //System.out.println("screenX before addOccupied: " + screenY);
        }
        
        
       // System.out.println("FINAL addOccupied: screenX: " + screenX + ", screenY: " + screenY);
        grid[screenY][screenX] = true;
        lastX = screenX;
        lastY = screenY;
  }
  
  /**
   * Removes an occupied space resulting in an unoccupied space
   * Returns early if space is already unoccupied
   * 
   * @param screenX the x-coordinate of the screen coordinate
   * @param screenY the y-coordinate of the screen coordinate
   */
  public void removeOccupied(int screenX, int screenY) {
      
      //System.out.println(isOccupied(screenX, screenY));
      if (isOccupied(screenX,screenY) == false) { // if false, right?
          // nothing to remove at current space, return immediately
          //System.out.println("R: early escape");
          return;
      }
      
      // if statements used to avoid coordinatesToGrid becoming -1 and causing an out of bounds exception
        if(screenX != 0) {
          //System.out.println("screenX before removeOccupied: " + screenX);
        screenX = coordinatesToGrid(screenX);
          //System.out.println("screenX after removeOccupied: " + screenX);
      }
        if (screenY != 0) {
         // System.out.println("screenX before removeOccupied: " + screenY);
            screenY = coordinatesToGrid(screenY);
          //System.out.println("screenX before removeOccupied: " + screenY);
        }
        
        grid[screenY][screenX] = false;
      
  }
  
/**
 * Rounds coordinates given to the ceiling by 100, unless the number is perfectly divisible by 100
 * 
 * Ex) 
 *   544 ----> 600
 *   238 ----> 300
 *   20  -----> 100
 *   500 ----> 500
 * @param x number to be rounded
 * @return the resulting number
 */
  public int roundToCeilingOfHundred(int x) {
     // System.out.print("round, " + x + ", to : " );
      if (x % 100 == 0) {
          //System.out.println(x);
          return x;
      }
    
    double y = x % 100;
    double z = 100 - y;
    
    x += z;
    //System.out.println(x);
    return x;
  
  }
}