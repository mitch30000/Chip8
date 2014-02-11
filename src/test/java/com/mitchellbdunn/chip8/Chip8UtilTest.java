
package com.mitchellbdunn.chip8;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Mitch
 */
public class Chip8UtilTest {
    
    @Test
    public void testGetNNN() {
        assertEquals(0x234, Chip8Util.getNNN(0x7234));
        assertEquals(0xCAB, Chip8Util.getNNN(0xFCAB));
        assertEquals(0x7B9, Chip8Util.getNNN(0xE7B9));
        assertEquals(0xFED, Chip8Util.getNNN(0X4FED));
        assertEquals(0x000, Chip8Util.getNNN(0X0000));
        assertEquals(0xFFF, Chip8Util.getNNN(0XFFFF));
        assertEquals(0xEA9, Chip8Util.getNNN(0X1EA9));
        assertEquals(0xED0, Chip8Util.getNNN(0X3ED0));
        assertEquals(0x876, Chip8Util.getNNN(0X9876));
        assertEquals(0x567, Chip8Util.getNNN(0X4567));
    }
    
    @Test
    public void testGetNN() {
        assertEquals(0x34, Chip8Util.getNN(0x7234));
        assertEquals(0xAB, Chip8Util.getNN(0xFCAB));
        assertEquals(0xB9, Chip8Util.getNN(0xE7B9));
        assertEquals(0xED, Chip8Util.getNN(0X4FED));
        assertEquals(0x00, Chip8Util.getNN(0X0000));
        assertEquals(0xFF, Chip8Util.getNN(0XFFFF));
        assertEquals(0xA9, Chip8Util.getNN(0X1EA9));
        assertEquals(0xD0, Chip8Util.getNN(0X3ED0));
        assertEquals(0x76, Chip8Util.getNN(0X9876));
        assertEquals(0x67, Chip8Util.getNN(0X4567));
    }
    
    @Test
    public void testGetN() {
        assertEquals(0x4, Chip8Util.getN(0x7234));
        assertEquals(0xB, Chip8Util.getN(0xFCAB));
        assertEquals(0x9, Chip8Util.getN(0xE7B9));
        assertEquals(0xD, Chip8Util.getN(0X4FED));
        assertEquals(0x0, Chip8Util.getN(0X0000));
        assertEquals(0xF, Chip8Util.getN(0XFFFF));
        assertEquals(0x9, Chip8Util.getN(0X1EA9));
        assertEquals(0x0, Chip8Util.getN(0X3ED0));
        assertEquals(0x6, Chip8Util.getN(0X9876));
        assertEquals(0x7, Chip8Util.getN(0X4567));
    }
    
    @Test
    public void testGetX() {
        assertEquals(0x2, Chip8Util.getX(0x7234));
        assertEquals(0xC, Chip8Util.getX(0xFCAB));
        assertEquals(0x7, Chip8Util.getX(0xE7B9));
        assertEquals(0xF, Chip8Util.getX(0X4FED));
        assertEquals(0x0, Chip8Util.getX(0X0000));
        assertEquals(0xF, Chip8Util.getX(0XFFFF));
        assertEquals(0xE, Chip8Util.getX(0X1EA9));
        assertEquals(0xE, Chip8Util.getX(0X3ED0));
        assertEquals(0x8, Chip8Util.getX(0X9876));
        assertEquals(0x5, Chip8Util.getX(0X4567));
    }
    
    @Test
    public void testGetY() {
        assertEquals(0x3, Chip8Util.getY(0x7234));
        assertEquals(0xA, Chip8Util.getY(0xFCAB));
        assertEquals(0xB, Chip8Util.getY(0xE7B9));
        assertEquals(0xE, Chip8Util.getY(0X4FED));
        assertEquals(0x0, Chip8Util.getY(0X0000));
        assertEquals(0xF, Chip8Util.getY(0XFFFF));
        assertEquals(0xA, Chip8Util.getY(0X1EA9));
        assertEquals(0xD, Chip8Util.getY(0X3ED0));
        assertEquals(0x7, Chip8Util.getY(0X9876));
        assertEquals(0x6, Chip8Util.getY(0X4567));
    }
    
    @Test
    public void testGetBit() {
        // 0xB3 = 10110011
        assertTrue(Chip8Util.getBit(0xB3, 0));
        assertTrue(Chip8Util.getBit(0xB3, 1));
        assertFalse(Chip8Util.getBit(0xB3, 2));
        assertFalse(Chip8Util.getBit(0xB3, 3));
        assertTrue(Chip8Util.getBit(0xB3, 4));
        assertTrue(Chip8Util.getBit(0xB3, 5));
        assertFalse(Chip8Util.getBit(0xB3, 6));
        assertTrue(Chip8Util.getBit(0xB3, 7));
    }
}
