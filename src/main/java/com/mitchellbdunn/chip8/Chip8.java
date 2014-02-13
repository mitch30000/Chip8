
package com.mitchellbdunn.chip8;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Mitch
 */
public class Chip8 {
    
    public static int SCREEN_WIDTH = 64;
    public static int SCREEN_HEIGHT = 32;
    public static int SCREEN_MULTIPLIER = 8;
    
    private static Cpu cpu;
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(SCREEN_WIDTH * SCREEN_MULTIPLIER, SCREEN_HEIGHT * SCREEN_MULTIPLIER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Chip8 Emulator");
        
        cpu = new Cpu();
        Memory memory = new Memory();
        cpu.setMemory(memory);
        
        Keyboard keyboard = new Keyboard();
        frame.addKeyListener(keyboard);
        frame.setFocusable(true);
        cpu.setKeyboard(keyboard);
        
        Screen screen = new Screen();
        frame.add(screen);
        cpu.setScreen(screen);
        screen.repaint();
    }
}
