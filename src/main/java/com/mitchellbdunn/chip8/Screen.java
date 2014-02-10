
package com.mitchellbdunn.chip8;

/**
 *
 * @author Mitch
 */
public class Screen {
    
    private boolean[][] screen;
    
    public Screen() {
        initializeScreen();
    }
    
    public final void initializeScreen() {
        screen = new boolean[32][64];
    }
    
    public boolean isPixelSet(int x, int y) {
        return screen[y][x];
    }
    
    public void setPixel(int x, int y, boolean value) {
        screen[y][x] = value;
    }
    
    public boolean drawPixel(int x, int y) {
        // XOR the pixel
        screen[y][x] = !screen[y][x];
        // We return a boolean representing if the pixel was
        // set from 1 to 0.  This is so we can know how to set
        // register VF during drawing.
        return !screen[y][x];
    }
}
