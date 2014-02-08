
package com.mitchellbdunn.chip8;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mitch
 */
public class Keyboard implements KeyListener {

    /**
     * Default key positioning is as follows:
     * 1, 2, 3, C
     * 4, 5, 6, D
     * 7, 8, 9, E
     * A, 0, B, F
     */
    private final Map<Integer, Integer> keyMap;
    private final boolean[] keyState;
    
    public Keyboard() {
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
        keyState = new boolean[16];
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        keyPress(ke.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        keyRelease(ke.getKeyCode());
    }
    
    public void keyPress(int keyCode) {
        if(keyMap.containsKey(keyCode)) {
            keyState[keyMap.get(keyCode)] = true;
        }
    }
    
    public void keyRelease(int keyCode) {
        if(keyMap.containsKey(keyCode)) {
            keyState[keyMap.get(keyCode)] = false;
        }
    }
    
    public boolean isKeyPressed(int key) {
        return keyState[key];
    }
}
