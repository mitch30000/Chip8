package com.mitchellbdunn.chip8.gui;

import com.mitchellbdunn.chip8.system.Cpu;
import com.mitchellbdunn.chip8.system.Keyboard;
import com.mitchellbdunn.chip8.system.Memory;
import com.mitchellbdunn.chip8.system.Screen;
import com.mitchellbdunn.chip8.util.Chip8Constants;
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
        
        FileMenu fileMenu = new FileMenu(cpu, memory, keyboard, screen);
        menuBar.add(fileMenu);
        OptionsMenu optionsMenu = new OptionsMenu(screen, Chip8Constants.DEFAULT_SCREEN_MULTIPLIER);
        menuBar.add(optionsMenu);

        this.addKeyListener(keyboard);
        this.setFocusable(true);

        screen.setPreferredSize(new Dimension(Chip8Constants.SCREEN_WIDTH * optionsMenu.getScreenMultiplier(),
                Chip8Constants.SCREEN_HEIGHT * optionsMenu.getScreenMultiplier()));
        this.getContentPane().add(screen);
        this.pack();
    }
}
