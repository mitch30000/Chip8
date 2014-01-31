package com.mitchellbdunn.chip8;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Mitch
 */
public class MemoryTest {

    private final static String TEST_RESOURCES = "src/test/resources/com/mitchellbdunn/chip8/";

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testLoadRom() throws Exception {
        Memory memory = new Memory();
        memory.loadRom(TEST_RESOURCES + "testRom");
        for (int i = 0; i < 0x200; i++) {
            assertEquals(0, memory.getByte(i));
        }
        // In UTF-8, decimal 84 refers to 'T'
        assertEquals(84, memory.getByte(0x200));
        // In UTF-8, decimal 13 refers to CR, or carriage return
        assertEquals(13, memory.getByte(0xffe));
        // In UTF-8, decimal 10 refers to LF, or line ending 
        assertEquals(10, memory.getByte(0xfff));
    }

    @Test()
    public void testLoadRom_DoesNotExist() throws Exception {
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("'src/test/resources/com/mitchellbdunn/chip8/badpath' does not exist");
        Memory memory = new Memory();
        memory.loadRom(TEST_RESOURCES + "badpath");
    }

    @Test()
    public void testLoadRom_IsNotFile() throws Exception {
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("'src/test/resources/com/mitchellbdunn/chip8/directory' is not a file");
        Memory memory = new Memory();
        memory.loadRom(TEST_RESOURCES + "directory");
    }

    @Test()
    public void testLoadRom_RomTooBig() throws Exception {
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("'src/test/resources/com/mitchellbdunn/chip8/bigRom' is not a valid Chip-8 rom");
        Memory memory = new Memory();
        memory.loadRom(TEST_RESOURCES + "bigRom");
    }
}
