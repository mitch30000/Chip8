
package com.mitchellbdunn.chip8.listeners;

import com.mitchellbdunn.chip8.Chip8;
import com.mitchellbdunn.chip8.gui.OptionsMenu;
import com.mitchellbdunn.chip8.gui.Panel;
import com.mitchellbdunn.chip8.system.Screen;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author Mitch
 */
public class ScreenSizeListener implements ActionListener {
    
    private final OptionsMenu optionsMenu;
    private final Screen screen;
    private final Panel panel;
    
    public ScreenSizeListener(OptionsMenu optionsMenu, Screen screen, Panel panel) {
        this.optionsMenu = optionsMenu;
        this.screen = screen;
        this.panel = panel;
    }
    
    public void actionPerformed(ActionEvent ae) {
            // Get the source of the action
            JRadioButtonMenuItem source = (JRadioButtonMenuItem) ae.getSource();
            // Get the text representing the name of the source
            String menuItemName = source.getText();
            // Set all radio buttons to unselected
            optionsMenu.getScreenSize4().setSelected(false);
            optionsMenu.getScreenSize5().setSelected(false);
            optionsMenu.getScreenSize6().setSelected(false);
            optionsMenu.getScreenSize7().setSelected(false);
            optionsMenu.getScreenSize8().setSelected(false);
            optionsMenu.getScreenSize9().setSelected(false);
            optionsMenu.getScreenSize10().setSelected(false);
            // Set the source radio button to selected
            source.setSelected(true);
            // Get the screen multiplier from the menu item name, this way we can
            // set one action listener for all 10 menu items, rather than one action
            // listeners for each menu item.  We take the first number and divide it
            // 64 (the default pixel width of the chip8) so we can get the multiplier.
            // For example, the first option is 256x128, so in this case it would come
            // out to be 4.
            int screenMultiplier = Integer.parseInt(menuItemName.split("x")[0]) / 64;
            optionsMenu.setScreenMultiplier(screenMultiplier);
            screen.setScreenMultipler(screenMultiplier);
            panel.setPreferredSize(new Dimension(Chip8.SCREEN_WIDTH * screenMultiplier,
                Chip8.SCREEN_HEIGHT * screenMultiplier));
            ((JFrame) optionsMenu.getTopLevelAncestor()).pack();
        }
}
