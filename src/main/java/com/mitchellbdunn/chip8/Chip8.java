package com.mitchellbdunn.chip8;

import com.mitchellbdunn.chip8.gui.Frame;
import com.mitchellbdunn.chip8.system.Screen;
import com.mitchellbdunn.chip8.system.Memory;
import com.mitchellbdunn.chip8.system.Keyboard;
import com.mitchellbdunn.chip8.system.Cpu;
import java.awt.Color;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mitch
 */
public class Chip8 {

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
