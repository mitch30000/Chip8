package com.mitchellbdunn.chip8.system;

import com.mitchellbdunn.chip8.util.Chip8Constants;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Mitch
 */
public class Screen extends JPanel {

    private boolean[][] screen;

    public Screen() {
        initializeScreen();
    }

    public final void initializeScreen() {
        screen = new boolean[Chip8Constants.SCREEN_HEIGHT][Chip8Constants.SCREEN_WIDTH];
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

    @Override
    public void paintComponent(Graphics g) {
        for (int x = 0; x < Chip8Constants.SCREEN_WIDTH; x++) {
            for (int y = 0; y < Chip8Constants.SCREEN_HEIGHT; y++) {
                if (isPixelSet(x, y)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x * Chip8Constants.SCREEN_MULTIPLIER, y * Chip8Constants.SCREEN_MULTIPLIER,
                        Chip8Constants.SCREEN_MULTIPLIER, Chip8Constants.SCREEN_MULTIPLIER);
            }
        }
    }

}
