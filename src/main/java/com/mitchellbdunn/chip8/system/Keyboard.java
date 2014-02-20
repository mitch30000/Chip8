
package com.mitchellbdunn.chip8.system;

import com.mitchellbdunn.chip8.util.Chip8Constants;
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
     * Keyboard         Chip8
     * 1, 2, 3, 4       1, 2, 3, C
     * Q, W, E, R  -->  4, 5, 6, D
     * A, S, D, F       7, 8, 9, E
     * Z, X, C, V       A, 0, B, F
     */
    private Map<Integer, Integer> keyMap;
    private boolean[] keyState;
    private boolean waitingForKeyPress;
    private int keyPressed;
    
    public Keyboard() {
        initializeKeyboard();
    }
    
    public final void initializeKeyboard() {
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
        waitingForKeyPress = false;
    }
    
    public int waitForKeyPress() {
        waitingForKeyPress = true;
        while(waitingForKeyPress) {
            try {
                Thread.sleep(Chip8Constants.HERTZ);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return keyPressed;
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
        if(waitingForKeyPress) {
            keyPressed = keyMap.get(keyCode);
            waitingForKeyPress = false;
        }
    }
    
    public void keyRelease(int keyCode) {
        if(keyMap.containsKey(keyCode)) {
            keyState[keyMap.get(keyCode)] = false;
        }
        if(waitingForKeyPress) {
            keyPressed = keyMap.get(keyCode);
            waitingForKeyPress = false;
        }
    }
    
    public boolean isKeyPressed(int key) {
        return keyState[key];
    }
}
