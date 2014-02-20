
package com.mitchellbdunn.chip8.system;

import com.mitchellbdunn.chip8.system.Screen;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Mitch
 */
public class ScreenTest {
    
    private Screen screen;
    
    @Test
    public void testIsPixelSet() {
        screen = new Screen();
        assertFalse(screen.isPixelSet(20, 14));
        screen.setPixel(20, 14, true);
        assertTrue(screen.isPixelSet(20, 14));
    }
}
