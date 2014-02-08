
package com.mitchellbdunn.chip8;

import java.awt.event.KeyEvent;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Mitch
 */
public class KeyboardTest {
    
    private Keyboard keyboard;
    
    @Test
    public void testKeyPressed() {
        keyboard = new Keyboard();
        
        assertFalse(keyboard.isKeyPressed(0x0));
        keyboard.keyPress(KeyEvent.VK_X);
        assertTrue(keyboard.isKeyPressed(0x0));
        
        assertFalse(keyboard.isKeyPressed(0x1));
        keyboard.keyPress(KeyEvent.VK_1);
        assertTrue(keyboard.isKeyPressed(0x1));
        
        assertFalse(keyboard.isKeyPressed(0x2));
        keyboard.keyPress(KeyEvent.VK_2);
        assertTrue(keyboard.isKeyPressed(0x2));
        
        assertFalse(keyboard.isKeyPressed(0x3));
        keyboard.keyPress(KeyEvent.VK_3);
        assertTrue(keyboard.isKeyPressed(0x3));
        
        assertFalse(keyboard.isKeyPressed(0x4));
        keyboard.keyPress(KeyEvent.VK_Q);
        assertTrue(keyboard.isKeyPressed(0x4));
        
        assertFalse(keyboard.isKeyPressed(0x5));
        keyboard.keyPress(KeyEvent.VK_W);
        assertTrue(keyboard.isKeyPressed(0x5));
        
        assertFalse(keyboard.isKeyPressed(0x6));
        keyboard.keyPress(KeyEvent.VK_E);
        assertTrue(keyboard.isKeyPressed(0x6));
        
        assertFalse(keyboard.isKeyPressed(0x7));
        keyboard.keyPress(KeyEvent.VK_A);
        assertTrue(keyboard.isKeyPressed(0x7));
        
        assertFalse(keyboard.isKeyPressed(0x8));
        keyboard.keyPress(KeyEvent.VK_S);
        assertTrue(keyboard.isKeyPressed(0x8));
        
        assertFalse(keyboard.isKeyPressed(0x9));
        keyboard.keyPress(KeyEvent.VK_D);
        assertTrue(keyboard.isKeyPressed(0x9));
        
        assertFalse(keyboard.isKeyPressed(0xA));
        keyboard.keyPress(KeyEvent.VK_Z);
        assertTrue(keyboard.isKeyPressed(0xA));
        
        assertFalse(keyboard.isKeyPressed(0xB));
        keyboard.keyPress(KeyEvent.VK_C);
        assertTrue(keyboard.isKeyPressed(0xB));
        
        assertFalse(keyboard.isKeyPressed(0xC));
        keyboard.keyPress(KeyEvent.VK_4);
        assertTrue(keyboard.isKeyPressed(0xC));
        
        assertFalse(keyboard.isKeyPressed(0xD));
        keyboard.keyPress(KeyEvent.VK_R);
        assertTrue(keyboard.isKeyPressed(0xD));
        
        assertFalse(keyboard.isKeyPressed(0xE));
        keyboard.keyPress(KeyEvent.VK_F);
        assertTrue(keyboard.isKeyPressed(0xE));
        
        assertFalse(keyboard.isKeyPressed(0xF));
        keyboard.keyPress(KeyEvent.VK_V);
        assertTrue(keyboard.isKeyPressed(0xF));
    }
    
    @Test
    public void testKeyRelease() {
        keyboard = new Keyboard();
        
        // Set all keys to pressed so we can test the release
        keyboard.keyPress(KeyEvent.VK_1);
        keyboard.keyPress(KeyEvent.VK_2);
        keyboard.keyPress(KeyEvent.VK_3);
        keyboard.keyPress(KeyEvent.VK_4);
        keyboard.keyPress(KeyEvent.VK_Q);
        keyboard.keyPress(KeyEvent.VK_W);
        keyboard.keyPress(KeyEvent.VK_E);
        keyboard.keyPress(KeyEvent.VK_R);
        keyboard.keyPress(KeyEvent.VK_A);
        keyboard.keyPress(KeyEvent.VK_S);
        keyboard.keyPress(KeyEvent.VK_D);
        keyboard.keyPress(KeyEvent.VK_F);
        keyboard.keyPress(KeyEvent.VK_Z);
        keyboard.keyPress(KeyEvent.VK_X);
        keyboard.keyPress(KeyEvent.VK_C);
        keyboard.keyPress(KeyEvent.VK_V);
        
        assertTrue(keyboard.isKeyPressed(0x0));
        keyboard.keyRelease(KeyEvent.VK_X);
        assertFalse(keyboard.isKeyPressed(0x0));
        
        assertTrue(keyboard.isKeyPressed(0x1));
        keyboard.keyRelease(KeyEvent.VK_1);
        assertFalse(keyboard.isKeyPressed(0x1));
        
        assertTrue(keyboard.isKeyPressed(0x2));
        keyboard.keyRelease(KeyEvent.VK_2);
        assertFalse(keyboard.isKeyPressed(0x2));
        
        assertTrue(keyboard.isKeyPressed(0x3));
        keyboard.keyRelease(KeyEvent.VK_3);
        assertFalse(keyboard.isKeyPressed(0x3));
        
        assertTrue(keyboard.isKeyPressed(0x4));
        keyboard.keyRelease(KeyEvent.VK_Q);
        assertFalse(keyboard.isKeyPressed(0x4));
        
        assertTrue(keyboard.isKeyPressed(0x5));
        keyboard.keyRelease(KeyEvent.VK_W);
        assertFalse(keyboard.isKeyPressed(0x5));
        
        assertTrue(keyboard.isKeyPressed(0x6));
        keyboard.keyRelease(KeyEvent.VK_E);
        assertFalse(keyboard.isKeyPressed(0x6));
        
        assertTrue(keyboard.isKeyPressed(0x7));
        keyboard.keyRelease(KeyEvent.VK_A);
        assertFalse(keyboard.isKeyPressed(0x7));
        
        assertTrue(keyboard.isKeyPressed(0x8));
        keyboard.keyRelease(KeyEvent.VK_S);
        assertFalse(keyboard.isKeyPressed(0x8));
        
        assertTrue(keyboard.isKeyPressed(0x9));
        keyboard.keyRelease(KeyEvent.VK_D);
        assertFalse(keyboard.isKeyPressed(0x9));
        
        assertTrue(keyboard.isKeyPressed(0xA));
        keyboard.keyRelease(KeyEvent.VK_Z);
        assertFalse(keyboard.isKeyPressed(0xA));
        
        assertTrue(keyboard.isKeyPressed(0xB));
        keyboard.keyRelease(KeyEvent.VK_C);
        assertFalse(keyboard.isKeyPressed(0xB));
        
        assertTrue(keyboard.isKeyPressed(0xC));
        keyboard.keyRelease(KeyEvent.VK_4);
        assertFalse(keyboard.isKeyPressed(0xC));
        
        assertTrue(keyboard.isKeyPressed(0xD));
        keyboard.keyRelease(KeyEvent.VK_R);
        assertFalse(keyboard.isKeyPressed(0xD));
        
        assertTrue(keyboard.isKeyPressed(0xE));
        keyboard.keyRelease(KeyEvent.VK_F);
        assertFalse(keyboard.isKeyPressed(0xE));
        
        assertTrue(keyboard.isKeyPressed(0xF));
        keyboard.keyRelease(KeyEvent.VK_V);
        assertFalse(keyboard.isKeyPressed(0xF));
    }
}
