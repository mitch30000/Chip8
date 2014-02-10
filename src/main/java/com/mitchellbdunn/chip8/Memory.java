package com.mitchellbdunn.chip8;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Mitch
 */
public class Memory {

    private byte[] memory;

    private static final int[] FONT = {
        0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
        0x20, 0x60, 0x20, 0x20, 0x70, // 1
        0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
        0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
        0x90, 0x90, 0xF0, 0x10, 0x10, // 4
        0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
        0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
        0xF0, 0x10, 0x20, 0x40, 0x40, // 7
        0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
        0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
        0xF0, 0x90, 0xF0, 0x90, 0x90, // A
        0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
        0xF0, 0x80, 0x80, 0x80, 0xF0, // C
        0xE0, 0x90, 0x90, 0x90, 0xE0, // D
        0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
        0xF0, 0x80, 0xF0, 0x80, 0x80  // F
    };

    public Memory() {
        initializeMemory();
        loadFont();
    }

    public final void initializeMemory() {
        memory = new byte[4096];
    }

    public final void loadFont() {
        for(int i=0;i<80;i++) {
            memory[i] = (byte)FONT[i];
        }
    }

    public int getOpcode(int programCounter) {
        return memory[programCounter] << 8 | memory[programCounter + 1];
    }

    public void loadRom(String romPath) throws Exception {
        File file = new File(romPath);
        // Make sure path given is real
        if (!file.exists()) {
            throw new Exception("'" + romPath + "' does not exist");
        }
        // Make sure path given points to a file rather than directory
        if (!file.isFile()) {
            throw new Exception("'" + romPath + "' is not a file");
        }
        // Make sure file is the right size
        if (file.length() > 3584) {
            throw new Exception("'" + romPath + "' is not a valid Chip-8 rom");
        }
        // Get the file put the bytes into a byte array
        Path path = Paths.get(romPath);
        byte[] rom = Files.readAllBytes(path);
        // Copy the bytes from rom into memory starting at 0x200
        System.arraycopy(rom, 0, memory, 0x200, rom.length);
        System.out.println();
    }

    public byte[] getMemory() {
        return memory;
    }

    public void setByte(int index, byte value) {
        memory[index] = value;
    }
    
    public byte getByte(int index) {
        return memory[index];
    }
}
