/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BackWork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Perry
 */
public class LoadData  {
    
    int enemyAmt = 0;
    
    public void createData() {
        
    
File file = new File("C:\\Users\\Perry\\Documents\\NetBeansProjects\\JavaApplication16\\src\\file.ini");
 
            boolean check[] = new boolean[4];
            int enemyAmt = 1;
            
            for (int x = 0; x < 4; x++) {
                check[x] = false;
            } 
                    
        try {
 
            Scanner scanner = new Scanner(file);
 
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String strArr[] = new String[2];
                strArr[0] = new String();
                strArr[1] = new String();
                strArr = line.split(" ");
                
                if (strArr[0].equals("EnemyAmount") ) {
                    enemyAmt = Integer.parseInt(strArr[1]);
                    System.out.println("float: " + enemyAmt);
                    check[0] = true;
                }    
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        
                System.out.println("one didn;t check out D:");
                    System.out.println("enemyAmt: " + enemyAmt);
                    this.enemyAmt = enemyAmt;
         
    }
    
    public int getAmountEnemy() {
        return enemyAmt;
    }
}
