package com.mitchellbdunn.chip8.gui;

import com.mitchellbdunn.chip8.system.Cpu;
import com.mitchellbdunn.chip8.system.Keyboard;
import com.mitchellbdunn.chip8.system.Memory;
import com.mitchellbdunn.chip8.system.Screen;
import com.mitchellbdunn.chip8.util.Chip8Constants;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
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
        JMenu fileMenu = new JMenu("File");
        final JMenuItem loadRomMenuItem = new JMenuItem("Load Rom");
        final JMenuItem closeRomMenuItem = new JMenuItem("Close Rom");
        final JMenuItem exitMenuItem = new JMenuItem("Exit");
        final JFileChooser fileChooser = new JFileChooser();

        fileMenu.add(loadRomMenuItem);
        fileMenu.add(closeRomMenuItem);
        closeRomMenuItem.setEnabled(false);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        loadRomMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int retval = fileChooser.showOpenDialog(Frame.this);
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
                        JOptionPane.showMessageDialog(Frame.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        closeRomMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeRomMenuItem.setEnabled(false);
                loadRomMenuItem.setEnabled(true);
                cpu.setRunning(false);
                screen.initializeScreen();
                screen.repaint();
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(Frame.this,
                        "Are you sure you want to exit?", "Exit?", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    Frame.this.dispose();
                    System.exit(0);
                }
            }
        });

        this.addKeyListener(keyboard);
        this.setFocusable(true);

        screen.setPreferredSize(new Dimension(Chip8Constants.SCREEN_WIDTH * Chip8Constants.SCREEN_MULTIPLIER,
                Chip8Constants.SCREEN_HEIGHT * Chip8Constants.SCREEN_MULTIPLIER));
        this.getContentPane().add(screen);
        this.pack();
    }
}
