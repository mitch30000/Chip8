package com.mitchellbdunn.chip8.system;

import com.mitchellbdunn.chip8.util.Chip8Constants;
import com.mitchellbdunn.chip8.util.Chip8Util;
import java.awt.Toolkit;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author Mitch
 */
public class Cpu {

    // Chip8 units
    private Keyboard keyboard;
    private Screen screen;
    private Memory memory;

    // Cpu variables
    private int[] registerV;
    private int registerI;
    private Stack<Integer> stack;
    private int delayTimer;
    private int soundTimer;
    private int programCounter;
    private final Random rng;
    private boolean running;

    public Cpu() {
        initializeCpu();
        rng = new Random();
    }

    public final void initializeCpu() {
        running = false;
        registerV = new int[16];
        for (int i = 0; i < 0xF; i++) {
            registerV[i] = 0;
        }
        registerI = 0;
        stack = new Stack<Integer>();
        delayTimer = 0;
        soundTimer = 0;
        programCounter = 0x200;
    }

    public void run() {
        running = true;
        while (running) {
            int opcode = memory.getOpcode(getProgramCounter());
            runOpcode(opcode);
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void runOpcode(int opcode) {
        // Get the possible variables needed from the opcode
        int nnn = Chip8Util.getNNN(opcode);
        int nn = Chip8Util.getNN(opcode);
        int n = Chip8Util.getN(opcode);
        int x = Chip8Util.getX(opcode);
        int y = Chip8Util.getY(opcode);

        // Increase the program counter; there are some opcodes where
        // this won't matter since we set the program counter there
        incrementProgramCounter();

        // Run the appropriate opcode
        switch (opcode & 0xF000) {
            case 0x0000:
                switch (opcode & 0x000F) {
                    case 0x0000:
                        opcode00E0();
                        break;
                    case 0x000E:
                        opcode00EE();
                        break;
                }
                break;
            case 0x1000:
                opcode1NNN(nnn);
                break;
            case 0x2000:
                opcode2NNN(nnn);
                break;
            case 0x3000:
                opcode3XNN(x, nn);
                break;
            case 0x4000:
                opcode4XNN(x, nn);
                break;
            case 0x5000:
                opcode5XY0(x, y);
                break;
            case 0x6000:
                opcode6XNN(x, nn);
                break;
            case 0x7000:
                opcode7XNN(x, nn);
                break;
            case 0x8000:
                switch (opcode & 0x000F) {
                    case 0x0000:
                        opcode8XY0(x, y);
                        break;
                    case 0x0001:
                        opcode8XY1(x, y);
                        break;
                    case 0x0002:
                        opcode8XY2(x, y);
                        break;
                    case 0x0003:
                        opcode8XY3(x, y);
                        break;
                    case 0x0004:
                        opcode8XY4(x, y);
                        break;
                    case 0x0005:
                        opcode8XY5(x, y);
                        break;
                    case 0x0006:
                        opcode8XY6(x, y);
                        break;
                    case 0x0007:
                        opcode8XY7(x, y);
                        break;
                    case 0x000E:
                        opcode8XYE(x, y);
                        break;
                }
                break;
            case 0x9000:
                opcode9XY0(x, y);
                break;
            case 0xA000:
                opcodeANNN(nnn);
                break;
            case 0xB000:
                opcodeBNNN(nnn);
                break;
            case 0xC000:
                opcodeCXNN(x, nn);
                break;
            case 0xD000:
                opcodeDXYN(x, y, n);
                break;
            case 0xE000:
                switch (opcode & 0x000F) {
                    case 0x000E:
                        opcodeEX9E(x);
                        break;
                    case 0x0001:
                        opcodeEXA1(x);
                        break;
                }
                break;
            case 0xF000:
                switch (opcode & 0x00FF) {
                    case 0x0007:
                        opcodeFX07(x);
                        break;
                    case 0x000A:
                        opcodeFX0A(x);
                        break;
                    case 0x0015:
                        opcodeFX15(x);
                        break;
                    case 0x0018:
                        opcodeFX18(x);
                        break;
                    case 0x001E:
                        opcodeFX1E(x);
                        break;
                    case 0x0029:
                        opcodeFX29(x);
                        break;
                    case 0x0033:
                        opcodeFX33(x);
                        break;
                    case 0x0055:
                        opcodeFX55(x);
                        break;
                    case 0x0065:
                        opcodeFX65(x);
                        break;
                }
                break;
            default:
                System.out.println("Unknown opcode: " + Integer.toHexString(opcode));
        }

        // Decrement delay and sound timers, if sound timer is
        // greater than zero, then play a sound
        if (delayTimer > 0) {
            delayTimer--;
        }
        if (soundTimer > 0) {
            Toolkit.getDefaultToolkit().beep();
            soundTimer--;
        }
    }

    /**
     * Clears the screen.
     */
    private void opcode00E0() {
        screen.initializeScreen();
    }

    /**
     * Returns from a subroutine.
     */
    private void opcode00EE() {
        programCounter = stack.pop();
        incrementProgramCounter();
    }

    /**
     * Jumps to address NNN.
     *
     * @param nnn address to set program counter to.
     */
    private void opcode1NNN(int nnn) {
        programCounter = nnn;
    }

    /**
     * Calls subroutine at NNN.
     *
     * @param nnn address of start of subroutine.
     */
    private void opcode2NNN(int nnn) {
        stack.push(programCounter - 2);
        programCounter = nnn;
    }

    /**
     * Skips the next instruction if VX equals NN.
     *
     * @param x register to check for equality.
     * @param nn value to check if equal to register VX.
     */
    private void opcode3XNN(int x, int nn) {
        if (registerV[x] == nn) {
            incrementProgramCounter();
        }
    }

    /**
     * Skips the next instruction if VX doesn't equal NN.
     *
     * @param x register to check for equality.
     * @param nn value to check if not equal to register VX.
     */
    private void opcode4XNN(int x, int nn) {
        if (registerV[x] != nn) {
            incrementProgramCounter();
        }
    }

    /**
     * Skips the next instruction if VX equals VY.
     *
     * @param x register to check for equality.
     * @param y register to check for equality.
     */
    private void opcode5XY0(int x, int y) {
        if (registerV[x] == registerV[y]) {
            incrementProgramCounter();
        }
    }

    /**
     * Sets VX to NN.
     *
     * @param x register to set value.
     * @param nn value to set for register VX.
     */
    private void opcode6XNN(int x, int nn) {
        registerV[x] = nn;
    }

    /**
     * Adds NN to VX.
     *
     * @param x register to add value to.
     * @param nn value to add to register VX.
     */
    private void opcode7XNN(int x, int nn) {
        registerV[x] += nn;
    }

    /**
     * Sets VX to the value of VY.
     *
     * @param x register to set value.
     * @param y register to get the value to set in register VX.
     */
    private void opcode8XY0(int x, int y) {
        registerV[x] = registerV[y];
    }

    /**
     * Sets VX to VX or VY.
     *
     * @param x register to set value.
     * @param y register to perform bitwise OR with register VX.
     */
    private void opcode8XY1(int x, int y) {
        registerV[x] = registerV[x] | registerV[y];
    }

    /**
     * Sets VX to VX and VY.
     *
     * @param x register to set value.
     * @param y register to perform bitwise AND with register VX.
     */
    private void opcode8XY2(int x, int y) {
        registerV[x] = registerV[x] & registerV[y];
    }

    /**
     * Sets VX to VX xor VY.
     *
     * @param x register to set value.
     * @param y register to perform bitwise XOR with register VX.
     */
    private void opcode8XY3(int x, int y) {
        registerV[x] = registerV[x] ^ registerV[y];
    }

    /**
     * Adds VY to VX. VF is set to 1 when there's a carry, and to 0 when there
     * isn't.
     *
     * @param x register to add value to.
     * @param y register to get value and to add to register VX.
     */
    private void opcode8XY4(int x, int y) {
        registerV[x] += registerV[y];
        registerV[0xF] = Chip8Util.getBit(registerV[x], 8) ? 1 : 0;
        registerV[x] &= 0xFF;
    }

    /**
     * VY is subtracted from VX. VF is set to 0 when there's a borrow, and 1
     * when there isn't.
     *
     * @param x register to subtract value from.
     * @param y register to get value to subtract from register VX.
     */
    private void opcode8XY5(int x, int y) {
        if (registerV[x] >= registerV[y]) {
            registerV[0xF] = 1;
            registerV[x] -= registerV[y];
        } else {
            registerV[0xF] = 0;
            registerV[x] = (int) (0xFF + (registerV[x] - registerV[y] + 1));
        }
    }

    /**
     * Shifts VX right by one. VF is set to the value of the least significant
     * bit of VX before the shift.
     *
     * @param x register to shift bits right by one.
     * @param y not used.
     */
    private void opcode8XY6(int x, int y) {
        registerV[0xF] = Chip8Util.getBit(registerV[x], 0) ? 1 : 0;
        registerV[x] >>>= 1;
    }

    /**
     * Sets VX to VY minus VX. VF is set to 0 when there's a borrow, and 1 when
     * there isn't.
     *
     * @param x register to set value of subtraction to as well as get value of
     * subtrahend.
     * @param y register to get value of minuend for subtraction.
     */
    private void opcode8XY7(int x, int y) {
        if (registerV[y] >= registerV[x]) {
            registerV[0xF] = 1;
            registerV[x] = registerV[y] - registerV[x];
        } else {
            registerV[0xF] = 0;
            registerV[x] = (int) (0xFF + (registerV[y] - registerV[x] + 1));
        }
    }

    /**
     * Shifts VX left by one. VF is set to the value of the most significant bit
     * of VX before the shift.
     *
     * @param x register to shift bits left by one.
     * @param y not used.
     */
    private void opcode8XYE(int x, int y) {
        registerV[0xF] = Chip8Util.getBit(registerV[x], 7) ? 1 : 0;
        registerV[x] <<= 1;
        registerV[x] &= 0xFF;
    }

    /**
     * Skips the next instruction if VX doesn't equal VY.
     *
     * @param x register to check for equality.
     * @param y register to check for equality.
     */
    private void opcode9XY0(int x, int y) {
        if (registerV[x] != registerV[y]) {
            incrementProgramCounter();
        }
    }

    /**
     * Sets I to the address NNN.
     *
     * @param nnn value to set for I.
     */
    private void opcodeANNN(int nnn) {
        registerI = nnn;
    }

    /**
     * Jumps to the address NNN plus V0.
     *
     * @param nnn value to jump to plus value of register V0.
     */
    private void opcodeBNNN(int nnn) {
        programCounter = nnn + registerV[0];
    }

    /**
     * Sets VX to a random number and NN.
     *
     * @param x register to set value.
     * @param nn value to get bitwise AND with a random number for register VX.
     */
    private void opcodeCXNN(int x, int nn) {
        registerV[x] = rng.nextInt() & nn;
    }

    /**
     * Draws a sprite at coordinate (VX, VY) that has a width of 8 pixels and a
     * height of N pixels. Each row of 8 pixels is read as bit-coded (with the
     * most significant bit of each byte displayed on the left) starting from
     * memory location I; I value doesn't change after the execution of this
     * instruction. As described above, VF is set to 1 if any screen pixels are
     * flipped from set to unset when the sprite is drawn, and to 0 if that
     * doesn't happen.
     *
     * @param x register that holds X location to draw sprite.
     * @param y register that holds Y location to draw sprite.
     * @param n height of the sprite
     */
    private void opcodeDXYN(int x, int y, int n) {
        // Reset register VF
        registerV[0xF] = 0x0;
        // Loop through each row (represents Y coordinate)
        for (int i = 0; i < n; i++) {
            byte row = memory.getByte(registerI + i);
            // Loop through each bit to know how to draw it to
            // the screen (represents X coordinate)
            for (int j = 0; j < 8; j++) {
                // Boolean representing if the bit was set or not, we need
                // to read from MSB to LSB, so we invert the j index to
                // get the position we need.
                boolean drawPixel = Chip8Util.getBit(row, 7 - j);
                if (drawPixel) {
                    // Get the coordinates to draw to
                    int drawX = registerV[x] + j;
                    int drawY = registerV[y] + i;
                    // If a pixel will be drawn off the screen it
                    // instead wraps around the screen
                    while (drawX >= Chip8Constants.SCREEN_WIDTH) {
                        drawX -= Chip8Constants.SCREEN_WIDTH;
                    }
                    while (drawY >= Chip8Constants.SCREEN_HEIGHT) {
                        drawY -= Chip8Constants.SCREEN_HEIGHT;
                    }
                    // If the pixel is set, then it will get unset and
                    // VF should be set to 1
                    if (screen.isPixelSet(drawX, drawY)) {
                        registerV[0xF] = 0x1;
                    }
                    // Draw the pixel.  Pixels  are XOR into the 
                    // existing screen.
                    screen.drawPixel(drawX, drawY);
                }
            }
        }
        screen.repaint();
    }

    /**
     * Skips the next instruction if the key stored in VX is pressed.
     *
     * @param x register that holds a value that will map to a key
     */
    private void opcodeEX9E(int x) {
        if (keyboard.isKeyPressed(registerV[x])) {
            incrementProgramCounter();
        }
    }

    /**
     * Skips the next instruction if the key stored in VX isn't pressed.
     *
     * @param x register that holds a value that will map to a key
     */
    private void opcodeEXA1(int x) {
        if (!keyboard.isKeyPressed(registerV[x])) {
            incrementProgramCounter();
        }
    }

    /**
     * Sets VX to the value of the delay timer.
     *
     * @param x register to set to value of delay timer.
     */
    private void opcodeFX07(int x) {
        registerV[x] = delayTimer;
    }

    /**
     * A key press is awaited, and then stored in VX.
     *
     * @param x register to store which key is pressed.
     */
    private void opcodeFX0A(int x) {
        registerV[x] = keyboard.waitForKeyPress();
    }

    /**
     * Sets the delay timer to VX.
     *
     * @param x register to set with the value of the delay timer.
     */
    private void opcodeFX15(int x) {
        delayTimer = registerV[x];
    }

    /**
     * Sets the sound timer to VX.
     *
     * @param x register to set with the value of the sound timer.
     */
    private void opcodeFX18(int x) {
        soundTimer = registerV[x];
    }

    /**
     * Adds VX to I.
     *
     * @param x register to add to I.
     */
    private void opcodeFX1E(int x) {
        registerI += registerV[x];
    }

    /**
     * Sets I to the location of the sprite for the character in VX. Characters
     * 0-F (in hexadecimal) are represented by a 4x5 font.
     *
     * @param x register to get value of the sprite for the character.
     */
    private void opcodeFX29(int x) {
        registerI = x * 5;
    }

    /**
     * Stores the Binary-coded decimal representation of VX, with the most
     * significant of three digits at the address in I, the middle digit at I
     * plus 1, and the least significant digit at I plus 2. (In other words,
     * take the decimal representation of VX, place the hundreds digit in memory
     * at location in I, the tens digit at location I+1, and the ones digit at
     * location I+2.)
     *
     * @param x register to get decimal value and store the binary-coded
     * representation.
     */
    private void opcodeFX33(int x) {
        int number = registerV[x];
        memory.setByte(registerI, (byte) (number / 100));
        memory.setByte(registerI + 1, (byte) ((number % 100) / 10));
        memory.setByte(registerI + 2, (byte) (number % 10));
    }

    /**
     * Stores V0 to VX in memory starting at address I.
     *
     * @param x last register to store in memory.
     */
    private void opcodeFX55(int x) {
        for (int i = 0x0; i <= x; i++) {
            memory.setByte(registerI + i, (byte) registerV[i]);
        }
    }

    /**
     * Fills V0 to VX with values from memory starting at address I.
     *
     * @param x last register to store from memory.
     */
    private void opcodeFX65(int x) {
        for (int i = 0x0; i <= x; i++) {
            registerV[i] = memory.getByte(registerI + i);
        }
    }

    private void incrementProgramCounter() {
        programCounter = programCounter + 2;
    }

    public int getRegisterV(int x) {
        return registerV[x];
    }

    public void setRegisterV(int x, int value) {
        this.registerV[x] = value;
    }

    public int getRegisterI() {
        return registerI;
    }

    public void setRegisterI(int registerI) {
        this.registerI = registerI;
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    public void setStack(Stack<Integer> stack) {
        this.stack = stack;
    }

    public int getDelayTimer() {
        return delayTimer;
    }

    public void setDelayTimer(int delayTimer) {
        this.delayTimer = delayTimer;
    }

    public int getSoundTimer() {
        return soundTimer;
    }

    public void setSoundTimer(int soundTimer) {
        this.soundTimer = soundTimer;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(Keyboard keyboard) {
        this.keyboard = keyboard;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
