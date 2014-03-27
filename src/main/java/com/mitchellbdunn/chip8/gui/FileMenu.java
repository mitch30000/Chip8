package com.mitchellbdunn.chip8.gui;

import com.mitchellbdunn.chip8.listeners.CloseRomListener;
import com.mitchellbdunn.chip8.listeners.ExitListener;
import com.mitchellbdunn.chip8.listeners.LoadRomListener;
import com.mitchellbdunn.chip8.system.Cpu;
import com.mitchellbdunn.chip8.system.Keyboard;
import com.mitchellbdunn.chip8.system.Memory;
import com.mitchellbdunn.chip8.system.Screen;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Mitch
 */
public class FileMenu extends JMenu {

    private final JMenuItem loadRomMenuItem;
    private final JMenuItem closeRomMenuItem;
    private final JMenuItem exitMenuItem;

    public FileMenu(Cpu cpu, Memory memory, Keyboard keyboard, Screen screen) {
        super("File");
        loadRomMenuItem = new JMenuItem("Load Rom");
        closeRomMenuItem = new JMenuItem("Close Rom");
        exitMenuItem = new JMenuItem("Exit");

        this.add(loadRomMenuItem);
        this.add(closeRomMenuItem);
        closeRomMenuItem.setEnabled(false);
        this.addSeparator();
        this.add(exitMenuItem);

        loadRomMenuItem.addActionListener(new LoadRomListener(this, cpu, screen, memory, keyboard));
        closeRomMenuItem.addActionListener(new CloseRomListener(this, cpu, screen));
        exitMenuItem.addActionListener(new ExitListener(this));
    }
    
    public JMenuItem getLoadRomMenuItem() {
        return loadRomMenuItem;
    }
    
    public JMenuItem getCloseRomMenuItem() {
        return closeRomMenuItem;
    }
}
