package com.mitchellbdunn.chip8.listeners;

import com.mitchellbdunn.chip8.gui.FileMenu;
import com.mitchellbdunn.chip8.system.Cpu;
import com.mitchellbdunn.chip8.system.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Mitch
 */
public class CloseRomListener implements ActionListener {

    private final FileMenu fileMenu;
    private final Cpu cpu;
    private final Screen screen;
    
    public CloseRomListener(FileMenu fileMenu, Cpu cpu, Screen screen) {
        this.fileMenu = fileMenu;
        this.cpu = cpu;
        this.screen = screen;
    }
    
    public void actionPerformed(ActionEvent ae) {
        fileMenu.getCloseRomMenuItem().setEnabled(false);
        fileMenu.getLoadRomMenuItem().setEnabled(true);
        cpu.setRunning(false);
        screen.initializeScreen();
    }
}
