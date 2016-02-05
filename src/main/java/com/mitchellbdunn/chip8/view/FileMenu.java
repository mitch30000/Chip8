package com.mitchellbdunn.chip8.view;

import com.mitchellbdunn.chip8.controller.CloseRomListener;
import com.mitchellbdunn.chip8.controller.ExitListener;
import com.mitchellbdunn.chip8.controller.LoadRomListener;
import com.mitchellbdunn.chip8.model.Cpu;
import com.mitchellbdunn.chip8.model.Keyboard;
import com.mitchellbdunn.chip8.model.Memory;
import com.mitchellbdunn.chip8.model.Screen;
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
