package BackWork;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



public class Timer {
    private long beginTime;
    private long frameLength;
    
    /**
     * Initalizes the beginTime and frameLength
     */
    void Timer() {
        beginTime = 0;
        frameLength = 0;
    }
    
    void tick() {
        beginTime = System.currentTimeMillis();
    }
    
    double getFPS() {
        frameLength = System.currentTimeMillis();
        
        
        //return 20 /(frameLength - beginTime); // 20 seconds per frame
        return 0;
    }
    
    double getDelta() {
        // in seconds
        
        double currTime = System.currentTimeMillis();
        double total = (currTime - beginTime) / 1000;
        return total;
    }
}
