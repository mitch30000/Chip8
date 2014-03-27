
package com.mitchellbdunn.chip8.view;

import com.mitchellbdunn.chip8.Chip8;
import com.mitchellbdunn.chip8.model.Screen;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author Mitch
 */
public class Panel extends JPanel implements Observer {
    
    private final Screen screen;
    
    public Panel(Screen screen) {
        this.screen = screen;
    }

    public void update(Observable o, Object arg) {
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        for (int x = 0; x < Chip8.SCREEN_WIDTH; x++) {
            for (int y = 0; y < Chip8.SCREEN_HEIGHT; y++) {
                if (screen.isPixelSet(x, y)) {
                    g.setColor(screen.getForegroundColor());
                } else {
                    g.setColor(screen.getBackgroundColor());
                }
                g.fillRect(x * screen.getScreenMultiplier(), y * screen.getScreenMultiplier(),
                        screen.getScreenMultiplier(), screen.getScreenMultiplier());
            }
        }
    }
}
