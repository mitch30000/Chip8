
package com.mitchellbdunn.chip8.controller;

import com.mitchellbdunn.chip8.model.Keyboard;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mitch
 */
public class KeyboardListener implements KeyListener {
    
    /**
     * Default key positioning is as follows:
     * Keyboard         Chip8
     * 1, 2, 3, 4       1, 2, 3, C
     * Q, W, E, R  -->  4, 5, 6, D
     * A, S, D, F       7, 8, 9, E
     * Z, X, C, V       A, 0, B, F
     */
    private final Keyboard keyboard;
    private Map<Integer, Integer> keyMap;
    
    public KeyboardListener(Keyboard keyboard) {
        this.keyboard = keyboard;
        keyMap = new HashMap<Integer, Integer>();
        keyMap.put(KeyEvent.VK_1, 0x1);
        keyMap.put(KeyEvent.VK_2, 0x2);
        keyMap.put(KeyEvent.VK_3, 0x3);
        keyMap.put(KeyEvent.VK_4, 0xC);
        keyMap.put(KeyEvent.VK_Q, 0x4);
        keyMap.put(KeyEvent.VK_W, 0x5);
        keyMap.put(KeyEvent.VK_E, 0x6);
        keyMap.put(KeyEvent.VK_R, 0xD);
        keyMap.put(KeyEvent.VK_A, 0x7);
        keyMap.put(KeyEvent.VK_S, 0x8);
        keyMap.put(KeyEvent.VK_D, 0x9);
        keyMap.put(KeyEvent.VK_F, 0xE);
        keyMap.put(KeyEvent.VK_Z, 0xA);
        keyMap.put(KeyEvent.VK_X, 0x0);
        keyMap.put(KeyEvent.VK_C, 0xB);
        keyMap.put(KeyEvent.VK_V, 0xF);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(keyMap.containsKey(ke.getKeyCode())) {
            keyboard.keyPress(ke.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if(keyMap.containsKey(ke.getKeyCode())) {
            keyboard.keyRelease(ke.getKeyCode());
        }
    }
}
