package com.mitchellbdunn.chip8.gui;

import com.mitchellbdunn.chip8.system.Screen;
import com.mitchellbdunn.chip8.Chip8;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.colorchooser.AbstractColorChooserPanel;

/**
 *
 * @author Mitch
 */
public class OptionsMenu extends JMenu {

    private int screenMultiplier;
    private final Screen screen;
    private final Panel panel;
    private final JMenu screenSizeMenu;
    private final JMenuItem screenSize4;
    private final JMenuItem screenSize5;
    private final JMenuItem screenSize6;
    private final JMenuItem screenSize7;
    private final JMenuItem screenSize8;
    private final JMenuItem screenSize9;
    private final JMenuItem screenSize10;
    private final JMenuItem backgroundColor;
    private final JMenuItem foregroundColor;
    
    public OptionsMenu(Screen screen, Panel panel, int defaultScreenMultiplier) {
        super("Options");
        this.screen = screen;
        this.panel = panel;
        screenMultiplier = defaultScreenMultiplier;
        screen.setScreenMultipler(screenMultiplier);
        
        // Create the menu and menu items
        screenSizeMenu = new JMenu("Screen Size");
        screenSize4 = new JRadioButtonMenuItem("256x128");
        screenSize5 = new JRadioButtonMenuItem("320x160");
        screenSize6 = new JRadioButtonMenuItem("384x192");
        screenSize7 = new JRadioButtonMenuItem("448x224");
        screenSize8 = new JRadioButtonMenuItem("512x256");
        screenSize9 = new JRadioButtonMenuItem("576x288");
        screenSize10 = new JRadioButtonMenuItem("640x320");
        backgroundColor = new JMenuItem("Change Background Color");
        foregroundColor = new JMenuItem("Change Foreground Color");
        
        // Set the default screen size as selected
        if(screenMultiplier == 4) {
            screenSize4.setSelected(true);
        } else if(screenMultiplier == 5) {
            screenSize5.setSelected(true);
        } else if(screenMultiplier == 6) {
            screenSize6.setSelected(true);
        } else if(screenMultiplier == 7) {
            screenSize7.setSelected(true);
        } else if(screenMultiplier == 8) {
            screenSize8.setSelected(true);
        } else if(screenMultiplier == 9) {
            screenSize9.setSelected(true);
        } else if(screenMultiplier == 10) {
            screenSize10.setSelected(true);
        }
        
        // Set up action listeners as well as add the menu items
        // to the menu
        ActionListener screenSizeListener = new ScreenSizeListener();
        ActionListener colorChangeListener = new ColorChangeListener();
        screenSize4.addActionListener(screenSizeListener);
        screenSizeMenu.add(screenSize4);
        screenSize5.addActionListener(screenSizeListener);
        screenSizeMenu.add(screenSize5);
        screenSize6.addActionListener(screenSizeListener);
        screenSizeMenu.add(screenSize6);
        screenSize7.addActionListener(screenSizeListener);
        screenSizeMenu.add(screenSize7);
        screenSize8.addActionListener(screenSizeListener);
        screenSizeMenu.add(screenSize8);
        screenSize9.addActionListener(screenSizeListener);
        screenSizeMenu.add(screenSize9);
        screenSize10.addActionListener(screenSizeListener);
        screenSizeMenu.add(screenSize10);
        backgroundColor.addActionListener(colorChangeListener);
        foregroundColor.addActionListener(colorChangeListener);
        this.add(screenSizeMenu);
        this.addSeparator();
        this.add(backgroundColor);
        this.add(foregroundColor);
    }
    
    public int getScreenMultiplier() {
        return screenMultiplier;
    }
    
    private class ScreenSizeListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            // Get the source of the action
            JRadioButtonMenuItem source = (JRadioButtonMenuItem) ae.getSource();
            // Get the text representing the name of the source
            String menuItemName = source.getText();
            // Set all radio buttons to unselected
            screenSize4.setSelected(false);
            screenSize5.setSelected(false);
            screenSize6.setSelected(false);
            screenSize7.setSelected(false);
            screenSize8.setSelected(false);
            screenSize9.setSelected(false);
            screenSize10.setSelected(false);
            // Set the source radio button to selected
            source.setSelected(true);
            // Get the screen multiplier from the menu item name, this way we can
            // set one action listener for all 10 menu items, rather than one action
            // listeners for each menu item.  We take the first number and divide it
            // 64 (the default pixel width of the chip8) so we can get the multiplier.
            // For example, the first option is 256x128, so in this case it would come
            // out to be 4.
            screenMultiplier = Integer.parseInt(menuItemName.split("x")[0]) / 64;
            screen.setScreenMultipler(screenMultiplier);
            panel.setPreferredSize(new Dimension(Chip8.SCREEN_WIDTH * screenMultiplier,
                Chip8.SCREEN_HEIGHT * screenMultiplier));
            ((JFrame) OptionsMenu.this.getTopLevelAncestor()).pack();
        }
    }
    
    private class ColorChangeListener implements ActionListener {
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
            JDialog dialog = JColorChooser.createDialog(OptionsMenu.this, "Choose Color", true, colorChooser, null, null);
            dialog.setVisible(true);
            Color color = colorChooser.getColor();
            if(menuItemName.contains("Background")) {
                screen.setBackgroundColor(color);
            } else if(menuItemName.contains("Foreground")) {
                screen.setForegroundColor(color);
            }
            panel.repaint();
        }
    }
}
