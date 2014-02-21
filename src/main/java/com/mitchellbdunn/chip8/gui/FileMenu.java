package com.mitchellbdunn.chip8.gui;

import com.mitchellbdunn.chip8.system.Cpu;
import com.mitchellbdunn.chip8.system.Keyboard;
import com.mitchellbdunn.chip8.system.Memory;
import com.mitchellbdunn.chip8.system.Screen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Mitch
 */
public class FileMenu extends JMenu {

    private final Cpu cpu;
    private final Memory memory;
    private final Keyboard keyboard;
    private final Screen screen;
    private final JMenuItem loadRomMenuItem;
    private final JMenuItem closeRomMenuItem;
    private final JMenuItem exitMenuItem;
    private final JFileChooser fileChooser;

    public FileMenu(Cpu cpu, Memory memory, Keyboard keyboard, Screen screen) {
        super("File");
        this.cpu = cpu;
        this.memory = memory;
        this.keyboard = keyboard;
        this.screen = screen;
        loadRomMenuItem = new JMenuItem("Load Rom");
        closeRomMenuItem = new JMenuItem("Close Rom");
        exitMenuItem = new JMenuItem("Exit");
        fileChooser = new JFileChooser();

        this.add(loadRomMenuItem);
        this.add(closeRomMenuItem);
        closeRomMenuItem.setEnabled(false);
        this.addSeparator();
        this.add(exitMenuItem);

        loadRomMenuItem.addActionListener(new LoadRomListener());
        closeRomMenuItem.addActionListener(new CloseRomListener());
        exitMenuItem.addActionListener(new ExitListener());
    }

    private class LoadRomListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            int retval = fileChooser.showOpenDialog(FileMenu.this);
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
                    closeRomMenuItem.setEnabled(true);
                    loadRomMenuItem.setEnabled(false);
                    worker.execute();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FileMenu.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private class CloseRomListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            closeRomMenuItem.setEnabled(false);
            loadRomMenuItem.setEnabled(true);
            cpu.setRunning(false);
            screen.initializeScreen();
            screen.repaint();
        }
    }

    private class ExitListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            int confirm = JOptionPane.showConfirmDialog(FileMenu.this,
                    "Are you sure you want to exit?", "Exit?",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                ((JFrame) FileMenu.this.getTopLevelAncestor()).dispose();
                System.exit(0);
            }
        }
    }
}
