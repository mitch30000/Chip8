package com.mitchellbdunn.chip8;

import java.util.Stack;

/**
 *
 * @author Mitch
 */
public class Cpu {

    private int[] registerV;
    private int registerI;
    private Stack<Integer> stack;
    private int delayTimer;
    private int soundTimer;
    private int programCounter;

    public Cpu() {
        initializeCpu();
    }

    public final void initializeCpu() {
        registerV = new int[16];
        for (int i = 0; i < 0xF; i++) {
            registerV[i] = 0;
        }
        registerI = 0;
        stack = new Stack<Integer>();
        delayTimer = 0;
        soundTimer = 0;
        programCounter = 0;
    }

    public void runOpcode(int opcode) {
        int nnn = Chip8Util.getNNN(opcode);
        int nn = Chip8Util.getNN(opcode);
        int n = Chip8Util.getN(opcode);
        int x = Chip8Util.getX(opcode);
        int y = Chip8Util.getY(opcode);
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
        }
    }

    private void opcode00E0() {

    }

    private void opcode00EE() {

    }

    private void opcode1NNN(int nnn) {

    }

    private void opcode2NNN(int nnn) {

    }

    private void opcode3XNN(int x, int nn) {

    }

    private void opcode4XNN(int x, int nn) {

    }

    private void opcode5XY0(int x, int y) {

    }

    private void opcode6XNN(int x, int nn) {

    }

    private void opcode7XNN(int x, int nn) {

    }

    private void opcode8XY0(int x, int y) {

    }

    private void opcode8XY1(int x, int y) {

    }

    private void opcode8XY2(int x, int y) {

    }

    private void opcode8XY3(int x, int y) {

    }

    private void opcode8XY4(int x, int y) {

    }

    private void opcode8XY5(int x, int y) {

    }

    private void opcode8XY6(int x, int y) {

    }

    private void opcode8XY7(int x, int y) {

    }

    private void opcode8XYE(int x, int y) {

    }

    private void opcode9XY0(int x, int y) {

    }

    private void opcodeANNN(int nnn) {

    }

    private void opcodeBNNN(int nnn) {

    }

    private void opcodeCXNN(int x, int nn) {

    }

    private void opcodeDXYN(int x, int y, int n) {

    }

    private void opcodeEX9E(int x) {

    }

    private void opcodeEXA1(int x) {

    }

    private void opcodeFX07(int x) {

    }

    private void opcodeFX0A(int x) {

    }

    private void opcodeFX15(int x) {

    }

    private void opcodeFX18(int x) {

    }

    private void opcodeFX1E(int x) {

    }

    private void opcodeFX29(int x) {

    }

    private void opcodeFX33(int x) {

    }

    private void opcodeFX55(int x) {

    }

    private void opcodeFX65(int x) {

    }

}
