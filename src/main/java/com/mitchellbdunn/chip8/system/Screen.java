package com.mitchellbdunn.chip8.system;

import com.mitchellbdunn.chip8.Chip8;
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

    @Override
    public void paintComponent(Graphics g) {
        for (int x = 0; x < Chip8.SCREEN_WIDTH; x++) {
            for (int y = 0; y < Chip8.SCREEN_HEIGHT; y++) {
                if (isPixelSet(x, y)) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x * Chip8.SCREEN_MULTIPLIER, y * Chip8.SCREEN_MULTIPLIER,
                        Chip8.SCREEN_MULTIPLIER, Chip8.SCREEN_MULTIPLIER);
            }
        }
    }

}
