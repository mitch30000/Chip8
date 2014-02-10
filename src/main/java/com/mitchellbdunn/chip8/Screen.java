
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
}
