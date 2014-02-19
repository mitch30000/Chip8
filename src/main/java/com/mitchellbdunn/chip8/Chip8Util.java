
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
    
    /**
     * Returns a boolean representing the bit value
     * @param number The number to check the bit of
     * @param position The bit position to check from within
     * the number.  The LSB is at position 0.
     * @return 
     */
    public static boolean getBit(int number, int position) {
        return (((number >> position) & 1) == 1);
    }
    
    private Chip8Util() {}
}
