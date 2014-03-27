
package com.mitchellbdunn.chip8.listeners;

import com.mitchellbdunn.chip8.gui.FileMenu;
import com.mitchellbdunn.chip8.system.Cpu;
import com.mitchellbdunn.chip8.system.Keyboard;
import com.mitchellbdunn.chip8.system.Memory;
import com.mitchellbdunn.chip8.system.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Mitch
 */
public class LoadRomListener implements ActionListener {
    
    private final JFileChooser fileChooser;
    private final FileMenu fileMenu;
    private final Cpu cpu;
    private final Screen screen;
    private final Memory memory;
    private final Keyboard keyboard;
    
    public LoadRomListener(FileMenu fileMenu, Cpu cpu, Screen screen, Memory memory, Keyboard keyboard) {
        fileChooser = new JFileChooser();
        this.fileMenu = fileMenu;
        this.cpu = cpu;
        this.screen = screen;
        this.memory = memory;
        this.keyboard = keyboard;
    }
    
    public void actionPerformed(ActionEvent ae) {
        int retval = fileChooser.showOpenDialog(fileMenu);
            if (retval == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    cpu.initializeCpu();
                    memory.initializeMemory();
                    screen.initializeScreen();
                    keyboard.initializeKeyboard();
                    memory.loadRom(file.getAbsolutePath());
                    final SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            cpu.run();
                            return null;
                        }
                    };
                    fileMenu.getCloseRomMenuItem().setEnabled(true);
                    fileMenu.getLoadRomMenuItem().setEnabled(false);
                    worker.execute();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(fileMenu, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }
}
