package com.mitchellbdunn.chip8.view;

import com.mitchellbdunn.chip8.controller.ColorChangeListener;
import com.mitchellbdunn.chip8.controller.ScreenSizeListener;
import com.mitchellbdunn.chip8.model.Screen;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

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
        ActionListener screenSizeListener = new ScreenSizeListener(this, screen, panel);
        ActionListener colorChangeListener = new ColorChangeListener(this, screen);
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

    public JMenuItem getScreenSize4() {
        return screenSize4;
    }

    public JMenuItem getScreenSize5() {
        return screenSize5;
    }

    public JMenuItem getScreenSize6() {
        return screenSize6;
    }

    public JMenuItem getScreenSize7() {
        return screenSize7;
    }

    public JMenuItem getScreenSize8() {
        return screenSize8;
    }

    public JMenuItem getScreenSize9() {
        return screenSize9;
    }

    public JMenuItem getScreenSize10() {
        return screenSize10;
    }

    public JMenuItem getBackgroundColor() {
        return backgroundColor;
    }

    public JMenuItem getForegroundColor() {
        return foregroundColor;
    }
    
    public int getScreenMultiplier() {
        return screenMultiplier;
    }
    
    public void setScreenMultiplier(int screenMultiplier) {
        this.screenMultiplier = screenMultiplier;
    }
}
