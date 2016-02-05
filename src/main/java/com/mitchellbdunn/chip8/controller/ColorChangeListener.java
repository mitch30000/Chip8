
package com.mitchellbdunn.chip8.controller;

import com.mitchellbdunn.chip8.view.OptionsMenu;
import com.mitchellbdunn.chip8.model.Screen;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 *
 * @author Mitch
 */
public class ColorChangeListener implements ActionListener {
    
    private final OptionsMenu optionsMenu;
    private final Screen screen;
    
    public ColorChangeListener(OptionsMenu optionsMenu, Screen screen) {
        this.optionsMenu = optionsMenu;
        this.screen = screen;
    }
    
    public void actionPerformed(ActionEvent ae) {
            // Get the source of the action
            JMenuItem source = (JMenuItem) ae.getSource();
            // Get the text representing the name of the source
            String menuItemName = source.getText();
            // Create a color chooser
            JColorChooser colorChooser = new JColorChooser();
            // Make a color chooser with only swatches
            AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
            colorChooser.removeChooserPanel(panels[1]);
            colorChooser.removeChooserPanel(panels[2]);
            colorChooser.removeChooserPanel(panels[3]);
            colorChooser.removeChooserPanel(panels[4]);
            // Remove preview panel
            colorChooser.setPreviewPanel(new JPanel());
            JDialog dialog = JColorChooser.createDialog(optionsMenu, "Choose Color", true, colorChooser, null, null);
            dialog.setVisible(true);
            Color color = colorChooser.getColor();
            if(menuItemName.contains("Background")) {
                screen.setBackgroundColor(color);
            } else if(menuItemName.contains("Foreground")) {
                screen.setForegroundColor(color);
            }
        }
}
