
package com.mitchellbdunn.chip8.model;

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
        keyboard.keyPress(0x0);
        assertTrue(keyboard.isKeyPressed(0x0));
        
        assertFalse(keyboard.isKeyPressed(0x1));
        keyboard.keyPress(0x1);
        assertTrue(keyboard.isKeyPressed(0x1));
        
        assertFalse(keyboard.isKeyPressed(0x2));
        keyboard.keyPress(0x2);
        assertTrue(keyboard.isKeyPressed(0x2));
        
        assertFalse(keyboard.isKeyPressed(0x3));
        keyboard.keyPress(0x3);
        assertTrue(keyboard.isKeyPressed(0x3));
        
        assertFalse(keyboard.isKeyPressed(0x4));
        keyboard.keyPress(0x4);
        assertTrue(keyboard.isKeyPressed(0x4));
        
        assertFalse(keyboard.isKeyPressed(0x5));
        keyboard.keyPress(0x5);
        assertTrue(keyboard.isKeyPressed(0x5));
        
        assertFalse(keyboard.isKeyPressed(0x6));
        keyboard.keyPress(0x6);
        assertTrue(keyboard.isKeyPressed(0x6));
        
        assertFalse(keyboard.isKeyPressed(0x7));
        keyboard.keyPress(0x7);
        assertTrue(keyboard.isKeyPressed(0x7));
        
        assertFalse(keyboard.isKeyPressed(0x8));
        keyboard.keyPress(0x8);
        assertTrue(keyboard.isKeyPressed(0x8));
        
        assertFalse(keyboard.isKeyPressed(0x9));
        keyboard.keyPress(0x9);
        assertTrue(keyboard.isKeyPressed(0x9));
        
        assertFalse(keyboard.isKeyPressed(0xA));
        keyboard.keyPress(0xA);
        assertTrue(keyboard.isKeyPressed(0xA));
        
        assertFalse(keyboard.isKeyPressed(0xB));
        keyboard.keyPress(0xB);
        assertTrue(keyboard.isKeyPressed(0xB));
        
        assertFalse(keyboard.isKeyPressed(0xC));
        keyboard.keyPress(0xC);
        assertTrue(keyboard.isKeyPressed(0xC));
        
        assertFalse(keyboard.isKeyPressed(0xD));
        keyboard.keyPress(0xD);
        assertTrue(keyboard.isKeyPressed(0xD));
        
        assertFalse(keyboard.isKeyPressed(0xE));
        keyboard.keyPress(0xE);
        assertTrue(keyboard.isKeyPressed(0xE));
        
        assertFalse(keyboard.isKeyPressed(0xF));
        keyboard.keyPress(0xF);
        assertTrue(keyboard.isKeyPressed(0xF));
    }
    
    @Test
    public void testKeyRelease() {
        keyboard = new Keyboard();
        
        // Set all keys to pressed so we can test the release
        keyboard.keyPress(0x0);
        keyboard.keyPress(0x1);
        keyboard.keyPress(0x2);
        keyboard.keyPress(0x3);
        keyboard.keyPress(0x4);
        keyboard.keyPress(0x5);
        keyboard.keyPress(0x6);
        keyboard.keyPress(0x7);
        keyboard.keyPress(0x8);
        keyboard.keyPress(0x9);
        keyboard.keyPress(0xA);
        keyboard.keyPress(0xB);
        keyboard.keyPress(0xC);
        keyboard.keyPress(0xD);
        keyboard.keyPress(0xE);
        keyboard.keyPress(0xF);
        
        assertTrue(keyboard.isKeyPressed(0x0));
        keyboard.keyRelease(0x0);
        assertFalse(keyboard.isKeyPressed(0x0));
        
        assertTrue(keyboard.isKeyPressed(0x1));
        keyboard.keyRelease(0x1);
        assertFalse(keyboard.isKeyPressed(0x1));
        
        assertTrue(keyboard.isKeyPressed(0x2));
        keyboard.keyRelease(0x2);
        assertFalse(keyboard.isKeyPressed(0x2));
        
        assertTrue(keyboard.isKeyPressed(0x3));
        keyboard.keyRelease(0x3);
        assertFalse(keyboard.isKeyPressed(0x3));
        
        assertTrue(keyboard.isKeyPressed(0x4));
        keyboard.keyRelease(0x4);
        assertFalse(keyboard.isKeyPressed(0x4));
        
        assertTrue(keyboard.isKeyPressed(0x5));
        keyboard.keyRelease(0x5);
        assertFalse(keyboard.isKeyPressed(0x5));
        
        assertTrue(keyboard.isKeyPressed(0x6));
        keyboard.keyRelease(0x6);
        assertFalse(keyboard.isKeyPressed(0x6));
        
        assertTrue(keyboard.isKeyPressed(0x7));
        keyboard.keyRelease(0x7);
        assertFalse(keyboard.isKeyPressed(0x7));
        
        assertTrue(keyboard.isKeyPressed(0x8));
        keyboard.keyRelease(0x8);
        assertFalse(keyboard.isKeyPressed(0x8));
        
        assertTrue(keyboard.isKeyPressed(0x9));
        keyboard.keyRelease(0x9);
        assertFalse(keyboard.isKeyPressed(0x9));
        
        assertTrue(keyboard.isKeyPressed(0xA));
        keyboard.keyRelease(0xA);
        assertFalse(keyboard.isKeyPressed(0xA));
        
        assertTrue(keyboard.isKeyPressed(0xB));
        keyboard.keyRelease(0xB);
        assertFalse(keyboard.isKeyPressed(0xB));
        
        assertTrue(keyboard.isKeyPressed(0xC));
        keyboard.keyRelease(0xC);
        assertFalse(keyboard.isKeyPressed(0xC));
        
        assertTrue(keyboard.isKeyPressed(0xD));
        keyboard.keyRelease(0xD);
        assertFalse(keyboard.isKeyPressed(0xD));
        
        assertTrue(keyboard.isKeyPressed(0xE));
        keyboard.keyRelease(0xE);
        assertFalse(keyboard.isKeyPressed(0xE));
        
        assertTrue(keyboard.isKeyPressed(0xF));
        keyboard.keyRelease(0xF);
        assertFalse(keyboard.isKeyPressed(0xF));
    }
}
