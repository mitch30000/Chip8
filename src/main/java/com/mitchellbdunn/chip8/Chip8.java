package com.mitchellbdunn.chip8;

import com.mitchellbdunn.chip8.view.Frame;
import com.mitchellbdunn.chip8.model.Screen;
import com.mitchellbdunn.chip8.model.Memory;
import com.mitchellbdunn.chip8.model.Keyboard;
import com.mitchellbdunn.chip8.model.Cpu;
import java.awt.Color;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mitch
 */
public class Chip8 {
    public static int SCREEN_WIDTH = 64;
    public static int SCREEN_HEIGHT = 32;
    public static int HERTZ = 1000 / 60;
    public static int DEFAULT_SCREEN_MULTIPLIER = 6;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Cpu cpu = new Cpu();

                Memory memory = new Memory();
                cpu.setMemory(memory);

                Keyboard keyboard = new Keyboard();
                cpu.setKeyboard(keyboard);

                Screen screen = new Screen(Color.BLACK, Color.WHITE);
                cpu.setScreen(screen);
                
                Frame frame = new Frame(cpu, memory, keyboard, screen);
                frame.setVisible(true);
            }
        });
    }
}
