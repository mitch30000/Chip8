package com.mitchellbdunn.chip8.system;

import com.mitchellbdunn.chip8.Chip8;

/**
 *
 * @author Mitch
 */
public class Keyboard {

    private boolean[] keyState;
    private boolean waitingForKeyPress;
    private int keyPressed;

    public Keyboard() {
        initializeKeyboard();
    }

    public final void initializeKeyboard() {
        keyState = new boolean[16];
        waitingForKeyPress = false;
    }

    public int waitForKeyPress() {
        waitingForKeyPress = true;
        while (waitingForKeyPress) {
            try {
                Thread.sleep(Chip8.HERTZ);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return keyPressed;
    }

    public void keyPress(int key) {
        keyState[key] = true;
        if (waitingForKeyPress) {
            keyPressed = key;
            waitingForKeyPress = false;
        }
    }

    public void keyRelease(int key) {
            keyState[key] = false;
        if (waitingForKeyPress) {
            keyPressed = key;
            waitingForKeyPress = false;
        }
    }

    public boolean isKeyPressed(int key) {
        return keyState[key];
    }
}
