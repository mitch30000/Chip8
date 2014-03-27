package com.mitchellbdunn.chip8.model;

import com.mitchellbdunn.chip8.Chip8;
import java.awt.Color;

/**
 *
 * @author Mitch
 */
public class Screen {

    private boolean[][] screen;
    private int screenMultiplier;
    private Color backgroundColor;
    private Color foregroundColor;

    public Screen(Color backgroundColor, Color foregroundColor) {
        this.backgroundColor = backgroundColor;
        this.foregroundColor = foregroundColor;
        initializeScreen();
    }

    public final void initializeScreen() {
        screen = new boolean[Chip8.SCREEN_HEIGHT][Chip8.SCREEN_WIDTH];
    }

    public boolean isPixelSet(int x, int y) {
        return screen[y][x];
    }

    public void setPixel(int x, int y, boolean value) {
        screen[y][x] = value;
    }

    public void drawPixel(int x, int y) {
        // XOR the pixel
        screen[y][x] = !screen[y][x];
    }

    public void setScreenMultipler(int screenMultiplier) {
        this.screenMultiplier = screenMultiplier;
    }
    
    public int getScreenMultiplier() {
        return screenMultiplier;
    }
    
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public Color getBackgroundColor() {
        return backgroundColor;
    }
    
    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }
    
    public Color getForegroundColor() {
        return foregroundColor;
    }
}
