package com.mitchellbdunn.chip8.gui;

import com.mitchellbdunn.chip8.Chip8;
import com.mitchellbdunn.chip8.listeners.KeyboardListener;
import com.mitchellbdunn.chip8.system.Cpu;
import com.mitchellbdunn.chip8.system.Keyboard;
import com.mitchellbdunn.chip8.system.Memory;
import com.mitchellbdunn.chip8.system.Screen;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.WindowConstants;

/**
 *
 * @author Mitch
 */
public class Frame extends JFrame {
    
    public Frame(final Cpu cpu, final Memory memory, final Keyboard keyboard, final Screen screen) {
        // Create frame
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Chip8 Emulator");

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        
        // Create panel
        final Panel panel = new Panel(screen);
        cpu.addObserver(panel);
        
        // Create menu options
        FileMenu fileMenu = new FileMenu(cpu, memory, keyboard, screen);
        menuBar.add(fileMenu);
        OptionsMenu optionsMenu = new OptionsMenu(screen, panel, Chip8.DEFAULT_SCREEN_MULTIPLIER);
        menuBar.add(optionsMenu);

        KeyboardListener keyboardListener = new KeyboardListener(keyboard);
        this.addKeyListener(keyboardListener);
        this.setFocusable(true);
        panel.setPreferredSize(new Dimension(Chip8.SCREEN_WIDTH * Chip8.DEFAULT_SCREEN_MULTIPLIER,
                Chip8.SCREEN_HEIGHT * Chip8.DEFAULT_SCREEN_MULTIPLIER));
        this.getContentPane().add(panel);
        this.pack();
    }
}
