package com.mitchellbdunn.chip8.controller;

import com.mitchellbdunn.chip8.view.FileMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Mitch
 */
public class ExitListener implements ActionListener {

    private FileMenu fileMenu;

    public ExitListener(FileMenu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public void actionPerformed(ActionEvent ae) {
        int confirm = JOptionPane.showConfirmDialog(fileMenu,
                "Are you sure you want to exit?", "Exit?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            ((JFrame) fileMenu.getTopLevelAncestor()).dispose();
            System.exit(0);
        }
    }
}
