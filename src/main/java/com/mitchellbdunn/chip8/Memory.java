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

    public Memory() {
        initializeMemory();
    }

    public final void initializeMemory() {
        memory = new byte[4096];
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
    
    public byte getByte(int index) {
        return memory[index];
    }
}
