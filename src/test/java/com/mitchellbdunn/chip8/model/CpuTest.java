
package com.mitchellbdunn.chip8.model;

import java.awt.Color;
import java.util.Stack;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Mitch
 */
public class CpuTest {
    
    private Cpu cpu;
    
    @Test
    public void testOpcode00E0() {
        cpu = new Cpu();
        Screen screen = new Screen(Color.BLACK, Color.WHITE);
        cpu.setScreen(screen);
        for(int i=0;i<32;i++) {
            for(int j=0;j<64;j++) {
                assertFalse(screen.isPixelSet(j, i));
            }
        }
        for(int i=0;i<32;i++) {
            for(int j=0;j<64;j++) {
                screen.setPixel(j, i, true);
            }
        }
        for(int i=0;i<32;i++) {
            for(int j=0;j<64;j++) {
                assertTrue(screen.isPixelSet(j, i));
            }
        }
        cpu.runOpcode(0x00E0);
        for(int i=0;i<32;i++) {
            for(int j=0;j<64;j++) {
                assertFalse(screen.isPixelSet(j, i));
            }
        }
        
    }
    
    @Test
    public void testOpcode00EE() {
        cpu = new Cpu();
        cpu.getStack().push(0x121);
        cpu.getStack().push(0xCA9);
        cpu.getStack().push(0xFFD);
        cpu.runOpcode(0x00EE);
        assertEquals(0xFFF, cpu.getProgramCounter());
        cpu.runOpcode(0x00EE);
        assertEquals(0xCAB, cpu.getProgramCounter());
        cpu.runOpcode(0x00EE);
        assertEquals(0x123, cpu.getProgramCounter());
    }
    
    @Test
    public void testOpcode1NNN() {
        cpu = new Cpu();
        cpu.runOpcode(0x15B3);
        assertEquals(0x5B3, cpu.getProgramCounter());
        cpu.runOpcode(0x1F12);
        assertEquals(0xF12, cpu.getProgramCounter());
        cpu.runOpcode(0x1234);
        assertEquals(0x234, cpu.getProgramCounter());
    }
    
    @Test
    public void testOpcode2NNN() {
        cpu = new Cpu();
        cpu.setProgramCounter(0x111);
        cpu.runOpcode(0x25B3);
        assertEquals(0x5B3, cpu.getProgramCounter());
        cpu.runOpcode(0x2F12);
        assertEquals(0xF12, cpu.getProgramCounter());
        cpu.runOpcode(0x2234);
        assertEquals(0x234, cpu.getProgramCounter());
        Stack<Integer> stack = cpu.getStack();
        assertEquals(0xF12, (int)stack.pop());
        assertEquals(0x5B3, (int)stack.pop());
        assertEquals(0x111, (int)stack.pop());
    }
    
    @Test
    public void testOpcode3XNN() {
        cpu = new Cpu();
        cpu.setRegisterV(0x1, 0x9A);
        cpu.setRegisterV(0xC, 0xFF);
        cpu.setProgramCounter(0x12A);
        cpu.runOpcode(0x319B);
        assertEquals(0x12C, cpu.getProgramCounter());
        cpu.runOpcode(0x3CFF);
        assertEquals(0x130, cpu.getProgramCounter());
        
    }
    
    @Test
    public void testOpcode4XNN() {
        cpu = new Cpu();
        cpu.setRegisterV(0x1, 0x9A);
        cpu.setRegisterV(0xC, 0xFF);
        cpu.setProgramCounter(0x12A);
        cpu.runOpcode(0x419B);
        assertEquals(0x12E, cpu.getProgramCounter());
        cpu.runOpcode(0x4CFF);
        assertEquals(0x130, cpu.getProgramCounter());
    }
    
    @Test
    public void testOpcode5XY0() {
        cpu = new Cpu();
        cpu.setRegisterV(0x3, 0x5C);
        cpu.setRegisterV(0xE, 0x12);
        cpu.setRegisterV(0xA, 0xDB);
        cpu.setRegisterV(0x7, 0xDB);
        cpu.setProgramCounter(0xD00);
        cpu.runOpcode(0x53E0);
        assertEquals(0xD02, cpu.getProgramCounter());
        cpu.runOpcode(0x5A70);
        assertEquals(0xD06, cpu.getProgramCounter());
    }
    
    @Test
    public void testOpcode6XNN() {
        cpu = new Cpu();
        cpu.runOpcode(0x6789);
        cpu.runOpcode(0x6FFF);
        cpu.runOpcode(0x68B4);
        assertEquals(0x89, cpu.getRegisterV(0x7));
        assertEquals(0xFF, cpu.getRegisterV(0xF));
        assertEquals(0xB4, cpu.getRegisterV(0x8));
    }
    
    @Test
    public void testOpcode7XNN() {
        cpu = new Cpu();
        cpu.setRegisterV(0x5, 0xDE);
        cpu.setRegisterV(0xC, 0x17);
        cpu.setRegisterV(0x0, 0x45);
        cpu.runOpcode(0x7511);
        cpu.runOpcode(0x7CB5);
        cpu.runOpcode(0x7045);
        assertEquals(0xEF, cpu.getRegisterV(0x5));
        assertEquals(0xCC, cpu.getRegisterV(0xC));
        assertEquals(0x8A, cpu.getRegisterV(0x0));
    }
    
    @Test
    public void testOpcode8XY0() {
        cpu = new Cpu();
        cpu.setRegisterV(0x3, 0xDE);
        cpu.setRegisterV(0x4, 0x17);
        cpu.setRegisterV(0xC, 0x45);
        cpu.setRegisterV(0xD, 0x3B);
        cpu.runOpcode(0x8340);
        cpu.runOpcode(0x8CD0);
        assertEquals(0x17, cpu.getRegisterV(0x3));
        assertEquals(0x3B, cpu.getRegisterV(0xC));
    }
    
    @Test
    public void testOpcode8XY1() {
        cpu = new Cpu();
        cpu.setRegisterV(0x1, 0x00);
        cpu.setRegisterV(0x2, 0xFF);
        cpu.setRegisterV(0x3, 0x59);
        cpu.setRegisterV(0x4, 0x3A);
        cpu.setRegisterV(0x5, 0xB4);
        cpu.setRegisterV(0x6, 0x3C);
        cpu.runOpcode(0x8121);
        cpu.runOpcode(0x8341);
        cpu.runOpcode(0x8561);
        assertEquals(0xFF, cpu.getRegisterV(0x1));
        assertEquals(0x7B, cpu.getRegisterV(0x3));
        assertEquals(0xBC, cpu.getRegisterV(0x5));
    }
    
    @Test
    public void testOpcode8XY2() {
        cpu = new Cpu();
        cpu.setRegisterV(0x1, 0x00);
        cpu.setRegisterV(0x2, 0xFF);
        cpu.setRegisterV(0x3, 0x59);
        cpu.setRegisterV(0x4, 0x3A);
        cpu.setRegisterV(0x5, 0xB4);
        cpu.setRegisterV(0x6, 0x3C);
        cpu.runOpcode(0x8122);
        cpu.runOpcode(0x8342);
        cpu.runOpcode(0x8562);
        assertEquals(0x00, cpu.getRegisterV(0x1));
        assertEquals(0x18, cpu.getRegisterV(0x3));
        assertEquals(0x34, cpu.getRegisterV(0x5));
    }
    
    @Test
    public void testOpcode8XY3() {
        cpu = new Cpu();
        cpu.setRegisterV(0x1, 0x00);
        cpu.setRegisterV(0x2, 0xFF);
        cpu.setRegisterV(0x3, 0x59);
        cpu.setRegisterV(0x4, 0x3A);
        cpu.setRegisterV(0x5, 0xB4);
        cpu.setRegisterV(0x6, 0x3C);
        cpu.runOpcode(0x8123);
        cpu.runOpcode(0x8343);
        cpu.runOpcode(0x8563);
        assertEquals(0xFF, cpu.getRegisterV(0x1));
        assertEquals(0x63, cpu.getRegisterV(0x3));
        assertEquals(0x88, cpu.getRegisterV(0x5));
    }
    
    @Test
    public void testOpcode8XY4() {
        cpu = new Cpu();
        cpu.setRegisterV(0xA, 0x4D);
        cpu.setRegisterV(0xB, 0x99);
        cpu.setRegisterV(0xC, 0xFF);
        cpu.setRegisterV(0xD, 0x03);
        cpu.runOpcode(0x8AB4);
        assertEquals(0x0, cpu.getRegisterV(0xF));
        assertEquals(0xE6, cpu.getRegisterV(0xA));
        cpu.runOpcode(0x8CD4);
        assertEquals(0x1, cpu.getRegisterV(0xF));
        assertEquals(0x2, cpu.getRegisterV(0xC));
    }
    
    @Test
    public void testOpcode8XY5() {
        cpu = new Cpu();
        cpu.setRegisterV(0xA, 0x4D);
        cpu.setRegisterV(0xB, 0x99);
        cpu.setRegisterV(0xC, 0xFF);
        cpu.setRegisterV(0xD, 0x03);
        cpu.setRegisterV(0xE, 0x22);
        cpu.setRegisterV(0x0, 0x22);
        cpu.runOpcode(0x8AB5);
        assertEquals(0x0, cpu.getRegisterV(0xF));
        assertEquals(0xB4, cpu.getRegisterV(0xA));
        cpu.runOpcode(0x8CD5);
        assertEquals(0x1, cpu.getRegisterV(0xF));
        assertEquals(0xFC, cpu.getRegisterV(0xC));
        cpu.runOpcode(0x8E05);
        assertEquals(0x1, cpu.getRegisterV(0xF));
        assertEquals(0x00, cpu.getRegisterV(0xE));
    }
    
    @Test
    public void testOpcode8XY6() {
        cpu = new Cpu();
        cpu.setRegisterV(0x5, 0xA5);
        cpu.setRegisterV(0x0, 0x6C);
        cpu.runOpcode(0x8576);
        assertEquals(0x1, cpu.getRegisterV(0xF));
        assertEquals(0x52, cpu.getRegisterV(0x5));
        cpu.runOpcode(0x80F6);
        assertEquals(0x0, cpu.getRegisterV(0xF));
        assertEquals(0x36, cpu.getRegisterV(0x0));
    }
    
    @Test
    public void testOpcode8XY7() {
        cpu = new Cpu();
        cpu.setRegisterV(0xA, 0x4D);
        cpu.setRegisterV(0xB, 0x99);
        cpu.setRegisterV(0xC, 0xFF);
        cpu.setRegisterV(0xD, 0x03);
        cpu.setRegisterV(0xE, 0x22);
        cpu.setRegisterV(0x0, 0x22);
        cpu.runOpcode(0x8AB7);
        assertEquals(0x1, cpu.getRegisterV(0xF));
        assertEquals(0x4C, cpu.getRegisterV(0xA));
        cpu.runOpcode(0x8CD7);
        assertEquals(0x0, cpu.getRegisterV(0xF));
        assertEquals(0x04, cpu.getRegisterV(0xC));
        cpu.runOpcode(0x8E07);
        assertEquals(0x1, cpu.getRegisterV(0xF));
        assertEquals(0x00, cpu.getRegisterV(0xE));
    }
    
    @Test
    public void testOpcode8XYE() {
        cpu = new Cpu();
        cpu.setRegisterV(0x2, 0x7D);
        cpu.setRegisterV(0xD, 0xE3);
        cpu.runOpcode(0x820E);
        assertEquals(0x0, cpu.getRegisterV(0xF));
        assertEquals(0xFA, cpu.getRegisterV(0x2));
        cpu.runOpcode(0x8DBE);
        assertEquals(0x1, cpu.getRegisterV(0xF));
        assertEquals(0xC6, cpu.getRegisterV(0xD));
    }
    
    @Test
    public void testOpcode9XY0() {
        cpu = new Cpu();
        cpu.setRegisterV(0x3, 0x5C);
        cpu.setRegisterV(0xE, 0x12);
        cpu.setRegisterV(0xA, 0xDB);
        cpu.setRegisterV(0x7, 0xDB);
        cpu.setProgramCounter(0xD00);
        cpu.runOpcode(0x93E0);
        assertEquals(0xD04, cpu.getProgramCounter());
        cpu.runOpcode(0x9A70);
        assertEquals(0xD06, cpu.getProgramCounter());
    }
    
    @Test
    public void testOpcodeANNN() {
        cpu = new Cpu();
        cpu.runOpcode(0xA2E2);
        assertEquals(0x2E2, cpu.getRegisterI());
        cpu.runOpcode(0xA735);
        assertEquals(0x735, cpu.getRegisterI());
        cpu.runOpcode(0xAF0D);
        assertEquals(0xF0D, cpu.getRegisterI());
    }
    
    @Test
    public void testOpcodeBNNN() {
        cpu = new Cpu();
        cpu.setRegisterV(0x0, 0x1F);
        cpu.runOpcode(0xB934);
        assertEquals(0x953, cpu.getProgramCounter());
        cpu.setRegisterV(0x0, 0xA4);
        cpu.runOpcode(0xB3F5);
        assertEquals(0x499, cpu.getProgramCounter());
        
    }
    
    @Test
    public void testOpcodeCXNN() {
        cpu = new Cpu();
        cpu.runOpcode(0xC400);
        assertEquals(0x00, cpu.getRegisterV(0x4));
    }
    
    @Test
    public void testOpcodeDXYN() {
        // Set up everything
        cpu = new Cpu();
        Screen screen = new Screen(Color.BLACK, Color.WHITE);
        Memory memory = new Memory();
        cpu.setScreen(screen);
        cpu.setMemory(memory);
        cpu.setRegisterV(0x0, 0);
        cpu.setRegisterV(0x1, 1);
        cpu.setRegisterV(0x2, 63);
        cpu.setRegisterV(0x3, 31);
        // ********
        // *-*-*-*-
        // ********
        memory.setByte(0x200, (byte)0xFF);
        memory.setByte(0x201, (byte)0xAA);
        memory.setByte(0x202, (byte)0xFF);
        cpu.setRegisterI(0x200);
        // Test drawing one row
        cpu.runOpcode(0xD001);
        assertTrue(screen.isPixelSet(0, 0));
        assertTrue(screen.isPixelSet(1, 0));
        assertTrue(screen.isPixelSet(2, 0));
        assertTrue(screen.isPixelSet(3, 0));
        assertTrue(screen.isPixelSet(4, 0));
        assertTrue(screen.isPixelSet(5, 0));
        assertTrue(screen.isPixelSet(6, 0));
        assertTrue(screen.isPixelSet(7, 0));
        assertFalse(screen.isPixelSet(0, 1));
        assertFalse(screen.isPixelSet(1, 1));
        assertFalse(screen.isPixelSet(2, 1));
        assertFalse(screen.isPixelSet(3, 1));
        assertFalse(screen.isPixelSet(4, 1));
        assertFalse(screen.isPixelSet(5, 1));
        assertFalse(screen.isPixelSet(6, 1));
        assertFalse(screen.isPixelSet(7, 1));
        assertFalse(screen.isPixelSet(0, 2));
        assertFalse(screen.isPixelSet(1, 2));
        assertFalse(screen.isPixelSet(2, 2));
        assertFalse(screen.isPixelSet(3, 2));
        assertFalse(screen.isPixelSet(4, 2));
        assertFalse(screen.isPixelSet(5, 2));
        assertFalse(screen.isPixelSet(6, 2));
        assertFalse(screen.isPixelSet(7, 2));
        assertEquals(0x0, cpu.getRegisterV(0xF));
        // Test drawing three rows and if register 0xF gets set
        cpu.runOpcode(0xD003);
        assertFalse(screen.isPixelSet(0, 0));
        assertFalse(screen.isPixelSet(1, 0));
        assertFalse(screen.isPixelSet(2, 0));
        assertFalse(screen.isPixelSet(3, 0));
        assertFalse(screen.isPixelSet(4, 0));
        assertFalse(screen.isPixelSet(5, 0));
        assertFalse(screen.isPixelSet(6, 0));
        assertFalse(screen.isPixelSet(7, 0));
        assertTrue(screen.isPixelSet(0, 1));
        assertFalse(screen.isPixelSet(1, 1));
        assertTrue(screen.isPixelSet(2, 1));
        assertFalse(screen.isPixelSet(3, 1));
        assertTrue(screen.isPixelSet(4, 1));
        assertFalse(screen.isPixelSet(5, 1));
        assertTrue(screen.isPixelSet(6, 1));
        assertFalse(screen.isPixelSet(7, 1));
        assertTrue(screen.isPixelSet(0, 2));
        assertTrue(screen.isPixelSet(1, 2));
        assertTrue(screen.isPixelSet(2, 2));
        assertTrue(screen.isPixelSet(3, 2));
        assertTrue(screen.isPixelSet(4, 2));
        assertTrue(screen.isPixelSet(5, 2));
        assertTrue(screen.isPixelSet(6, 2));
        assertTrue(screen.isPixelSet(7, 2));
        assertEquals(0x1, cpu.getRegisterV(0xF));
        // Test drawing two rows that should turn everything back to off,
        // and that register 0xF gets set
        cpu.setRegisterI(0x201);
        cpu.runOpcode(0xD012);
        assertFalse(screen.isPixelSet(0, 0));
        assertFalse(screen.isPixelSet(1, 0));
        assertFalse(screen.isPixelSet(2, 0));
        assertFalse(screen.isPixelSet(3, 0));
        assertFalse(screen.isPixelSet(4, 0));
        assertFalse(screen.isPixelSet(5, 0));
        assertFalse(screen.isPixelSet(6, 0));
        assertFalse(screen.isPixelSet(7, 0));
        assertFalse(screen.isPixelSet(0, 1));
        assertFalse(screen.isPixelSet(1, 1));
        assertFalse(screen.isPixelSet(2, 1));
        assertFalse(screen.isPixelSet(3, 1));
        assertFalse(screen.isPixelSet(4, 1));
        assertFalse(screen.isPixelSet(5, 1));
        assertFalse(screen.isPixelSet(6, 1));
        assertFalse(screen.isPixelSet(7, 1));
        assertFalse(screen.isPixelSet(0, 2));
        assertFalse(screen.isPixelSet(1, 2));
        assertFalse(screen.isPixelSet(2, 2));
        assertFalse(screen.isPixelSet(3, 2));
        assertFalse(screen.isPixelSet(4, 2));
        assertFalse(screen.isPixelSet(5, 2));
        assertFalse(screen.isPixelSet(6, 2));
        assertFalse(screen.isPixelSet(7, 2));
        assertEquals(0x1, cpu.getRegisterV(0xF));
        // Test drawing off the screen and that it wraps back around
        cpu.setRegisterI(0x200);
        cpu.runOpcode(0xD233);
        assertTrue(screen.isPixelSet(63, 31));
        assertTrue(screen.isPixelSet(0, 31));
        assertTrue(screen.isPixelSet(1, 31));
        assertTrue(screen.isPixelSet(2, 31));
        assertTrue(screen.isPixelSet(3, 31));
        assertTrue(screen.isPixelSet(4, 31));
        assertTrue(screen.isPixelSet(5, 31));
        assertTrue(screen.isPixelSet(6, 31));
        assertTrue(screen.isPixelSet(63, 0));
        assertFalse(screen.isPixelSet(0, 0));
        assertTrue(screen.isPixelSet(1, 0));
        assertFalse(screen.isPixelSet(2, 0));
        assertTrue(screen.isPixelSet(3, 0));
        assertFalse(screen.isPixelSet(4, 0));
        assertTrue(screen.isPixelSet(5, 0));
        assertFalse(screen.isPixelSet(6, 0));
        assertTrue(screen.isPixelSet(63, 1));
        assertTrue(screen.isPixelSet(0, 1));
        assertTrue(screen.isPixelSet(1, 1));
        assertTrue(screen.isPixelSet(2, 1));
        assertTrue(screen.isPixelSet(3, 1));
        assertTrue(screen.isPixelSet(4, 1));
        assertTrue(screen.isPixelSet(5, 1));
        assertTrue(screen.isPixelSet(6, 1));
        assertEquals(0x0, cpu.getRegisterV(0xF));
        
    }
    
    @Test
    public void testOpcodeEX9E() {
        cpu = new Cpu();
        Keyboard keyboard = new Keyboard();
        cpu.setKeyboard(keyboard);
        cpu.setProgramCounter(0x200);
        cpu.setRegisterV(0x3, 0x1);
        cpu.runOpcode(0xE39E);
        assertEquals(0x202, cpu.getProgramCounter());
        keyboard.keyPress(0x1);
        cpu.runOpcode(0xE39E);
        assertEquals(0x206, cpu.getProgramCounter());
    }
    
    @Test
    public void testOpcodeEXA1() {
        cpu = new Cpu();
        Keyboard keyboard = new Keyboard();
        cpu.setKeyboard(keyboard);
        cpu.setProgramCounter(0x200);
        cpu.setRegisterV(0xE, 0x3);
        cpu.runOpcode(0xEEA1);
        assertEquals(0x204, cpu.getProgramCounter());
        keyboard.keyPress(0x3);
        cpu.runOpcode(0xEEA1);
        assertEquals(0x206, cpu.getProgramCounter());
    }
    
    @Test
    public void testOpcodeFX07() {
        cpu = new Cpu();
        cpu.setDelayTimer(0x13);
        cpu.runOpcode(0xF307);
        cpu.setDelayTimer(0xFE);
        cpu.runOpcode(0xFC07);
        assertEquals(0x13, cpu.getRegisterV(0x3));
        assertEquals(0xFE, cpu.getRegisterV(0xC));
    }
    
    @Test
    public void testOpcodeFX0A() {
        // TODO: Figure out how to test this
    }
    
    @Test
    public void testOpcodeFX15() {
        cpu = new Cpu();
        cpu.setRegisterV(0x3, 0x56);
        cpu.setRegisterV(0xA, 0xBC);
        cpu.runOpcode(0xF315);
        // Should be 55 since the timer will have decremented once
        // due to running the opcode
        assertEquals(0x55, cpu.getDelayTimer());
        cpu.runOpcode(0xFA15);
        // Should be BB since the timer will have decremented once
        // due to running the opcode
        assertEquals(0xBB, cpu.getDelayTimer());
    }
    
    @Test
    public void testOpcodeFX18() {
        cpu = new Cpu();
        cpu.setRegisterV(0x4, 0x43);
        cpu.setRegisterV(0xE, 0x9F);
        cpu.runOpcode(0xF418);
        // Should be 42 since the timer will have decremented once
        // due to running the opcode
        assertEquals(0x42, cpu.getSoundTimer());
        // Should be 9E since the timer will have decremented once
        // due to running the opcode
        cpu.runOpcode(0xFE18);
        assertEquals(0x9E, cpu.getSoundTimer());
    }
    
    @Test
    public void testOpcodeFX1E() {
        cpu = new Cpu();
        cpu.setRegisterV(0x8, 0x3F);
        cpu.setRegisterV(0xD, 0xA5);
        cpu.setRegisterI(0x01);
        cpu.runOpcode(0xF81E);
        assertEquals(0x40, cpu.getRegisterI());
        cpu.runOpcode(0xFD1E);
        assertEquals(0xE5, cpu.getRegisterI());
    }
    
    @Test
    public void testOpcodeFX29() {
        cpu = new Cpu();
        cpu.runOpcode(0xF029);
        assertEquals(0, cpu.getRegisterI());
        cpu.runOpcode(0xF129);
        assertEquals(5, cpu.getRegisterI());
        cpu.runOpcode(0xF229);
        assertEquals(10, cpu.getRegisterI());
        cpu.runOpcode(0xF329);
        assertEquals(15, cpu.getRegisterI());
        cpu.runOpcode(0xF429);
        assertEquals(20, cpu.getRegisterI());
        cpu.runOpcode(0xF529);
        assertEquals(25, cpu.getRegisterI());
        cpu.runOpcode(0xF629);
        assertEquals(30, cpu.getRegisterI());
        cpu.runOpcode(0xF729);
        assertEquals(35, cpu.getRegisterI());
        cpu.runOpcode(0xF829);
        assertEquals(40, cpu.getRegisterI());
        cpu.runOpcode(0xF929);
        assertEquals(45, cpu.getRegisterI());
        cpu.runOpcode(0xFA29);
        assertEquals(50, cpu.getRegisterI());
        cpu.runOpcode(0xFB29);
        assertEquals(55, cpu.getRegisterI());
        cpu.runOpcode(0xFC29);
        assertEquals(60, cpu.getRegisterI());
        cpu.runOpcode(0xFD29);
        assertEquals(65, cpu.getRegisterI());
        cpu.runOpcode(0xFE29);
        assertEquals(70, cpu.getRegisterI());
        cpu.runOpcode(0xFF29);
        assertEquals(75, cpu.getRegisterI());
        
    }
    
    @Test
    public void testOpcodeFX33() {
        cpu = new Cpu();
        Memory memory = new Memory();
        cpu.setMemory(memory);
        cpu.setRegisterI(0x3F2);
        cpu.setRegisterV(0x5, 148);
        cpu.runOpcode(0xF533);
        assertEquals(1, memory.getByte(0x3F2));
        assertEquals(4, memory.getByte(0x3F3));
        assertEquals(8, memory.getByte(0x3F4));
    }
    
    @Test
    public void testOpcodeFX55() {
        cpu = new Cpu();
        Memory memory = new Memory();
        cpu.setMemory(memory);
        for(int i=0x0;i<=0xF;i++) {
            cpu.setRegisterV(i, i);
        }
        cpu.setRegisterI(0x346);
        cpu.runOpcode(0xFF55);
        assertEquals(0x0, memory.getByte(0x346));
        assertEquals(0x1, memory.getByte(0x347));
        assertEquals(0x2, memory.getByte(0x348));
        assertEquals(0x3, memory.getByte(0x349));
        assertEquals(0x4, memory.getByte(0x34A));
        assertEquals(0x5, memory.getByte(0x34B));
        assertEquals(0x6, memory.getByte(0x34C));
        assertEquals(0x7, memory.getByte(0x34D));
        assertEquals(0x8, memory.getByte(0x34E));
        assertEquals(0x9, memory.getByte(0x34F));
        assertEquals(0xA, memory.getByte(0x350));
        assertEquals(0xB, memory.getByte(0x351));
        assertEquals(0xC, memory.getByte(0x352));
        assertEquals(0xD, memory.getByte(0x353));
        assertEquals(0xE, memory.getByte(0x354));
        assertEquals(0xF, memory.getByte(0x355));
    }
    
    @Test
    public void testOpcodeFX65() {
        cpu = new Cpu();
        Memory memory = new Memory();
        cpu.setMemory(memory);
        cpu.setRegisterI(0x406);
        memory.setByte(0x406, (byte)0x0);
        memory.setByte(0x407, (byte)0x1);
        memory.setByte(0x408, (byte)0x2);
        memory.setByte(0x409, (byte)0x3);
        memory.setByte(0x40A, (byte)0x4);
        memory.setByte(0x40B, (byte)0x5);
        memory.setByte(0x40C, (byte)0x6);
        memory.setByte(0x40D, (byte)0x7);
        memory.setByte(0x40E, (byte)0x8);
        memory.setByte(0x40F, (byte)0x9);
        memory.setByte(0x410, (byte)0xA);
        memory.setByte(0x411, (byte)0xB);
        memory.setByte(0x412, (byte)0xC);
        memory.setByte(0x413, (byte)0xD);
        memory.setByte(0x414, (byte)0xE);
        memory.setByte(0x415, (byte)0xF);
        cpu.runOpcode(0xFF65);
        for(int i=0x0;i<=0xF;i++) {
            assertEquals(i, cpu.getRegisterV(i));
        }
    }
    
}
