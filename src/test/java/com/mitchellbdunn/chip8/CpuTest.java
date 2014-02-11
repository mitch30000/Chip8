
package com.mitchellbdunn.chip8;

import java.awt.event.KeyEvent;
import java.util.Stack;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Mitch
 */
public class CpuTest {
    
    private Cpu cpu;
    
    @Test
    public void testOpcode00E0() {
        
    }
    
    @Test
    public void testOpcode00EE() {
        cpu = new Cpu();
        cpu.getStack().push(0x123);
        cpu.getStack().push(0xCAB);
        cpu.getStack().push(0xFFF);
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
        
    }
    
    @Test
    public void testOpcode8XY5() {
        
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
        
    }
    
    @Test
    public void testOpcodeDXYN() {
        
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
        keyboard.keyPress(KeyEvent.VK_1);
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
        keyboard.keyPress(KeyEvent.VK_3);
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
        assertEquals(0x56, cpu.getDelayTimer());
        cpu.runOpcode(0xFA15);
        assertEquals(0xBC, cpu.getDelayTimer());
    }
    
    @Test
    public void testOpcodeFX18() {
        cpu = new Cpu();
        cpu.setRegisterV(0x4, 0x43);
        cpu.setRegisterV(0xE, 0x9F);
        cpu.runOpcode(0xF415);
        assertEquals(0x43, cpu.getDelayTimer());
        cpu.runOpcode(0xFE15);
        assertEquals(0x9F, cpu.getDelayTimer());
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
        
    }
    
    @Test
    public void testOpcodeFX33() {
        
    }
    
    @Test
    public void testOpcodeFX55() {
        
    }
    
    @Test
    public void testOpcodeFX65() {
        
    }
    
}
