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
        // In UTF-8, decimal 84 refers to 'T'
        assertEquals(84, memory.getByte(0x200));
        // In UTF-8, decimal 104 refers to 'h'
        assertEquals(104, memory.getByte(0x201));
        // In UTF-8, decimal 105 refers to 'i'
        assertEquals(105, memory.getByte(0x202));
        // In UTF-8, decimal 115 refers to 's'
        assertEquals(115, memory.getByte(0x203));
        // In UTF-8, decimal 99 refers to 'c'
        assertEquals(99, memory.getByte(0xff9));
        // In UTF-8, decimal 104 refers to 'h'
        assertEquals(104, memory.getByte(0xffa));
        // In UTF-8, decimal 105 refers to 'i'
        assertEquals(105, memory.getByte(0xffb));
        // In UTF-8, decimal 112 refers to 'p'
        assertEquals(112, memory.getByte(0xffc));
        // In UTF-8, decimal 56 refers to '8'
        assertEquals(56, memory.getByte(0xffd));
        // In UTF-8, decimal 46 refers to '.'
        assertEquals(46, memory.getByte(0xffe));
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
