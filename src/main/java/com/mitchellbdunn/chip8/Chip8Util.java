
package com.mitchellbdunn.chip8;

/**
 *
 * @author Mitch
 */
public final class Chip8Util {
    
    public static int getNNN(int opcode) {
        return (opcode & 0x0FFF);
    }
    
    public static int getNN(int opcode) {
        return (opcode & 0x00FF);
    }
    
    public static int getN(int opcode) {
        return (opcode & 0x000F);
    }
    
    public static int getX(int opcode) {
        return (opcode & 0x0F00) >> 8;
    }
    
    public static int getY(int opcode) {
        return (opcode & 0x00F0) >> 4;
    }
    
    private Chip8Util() {}
}
