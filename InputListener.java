/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hummingbird;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputListener implements KeyListener {
    final int KEY_MAX = KeyEvent.KEY_LAST;
    final int KEY_UP = 0;
    final int KEY_DOWN = 1;
    final int KEY_LEFT = 2;
    final int KEY_RIGHT = 3;
    final int KEY_SHOOT = 4;
    final int KEY_GATHER = 5;
    final int KEY_RELOAD = 6;

    public boolean[] isKeyPressed = new boolean[KEY_MAX];

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) { // Key == left
            isKeyPressed[KEY_LEFT] = true;
        } else if (key == KeyEvent.VK_UP) { // Key == up
            isKeyPressed[KEY_UP] = true;
        } else if (key == KeyEvent.VK_RIGHT) { // Key == right
            isKeyPressed[KEY_RIGHT] = true;
        } else if (key == KeyEvent.VK_DOWN) { // Key == down
            isKeyPressed[KEY_DOWN] = true;
        } else if (key == KeyEvent.VK_Z) { // Key == shoot
            isKeyPressed[KEY_SHOOT] = true;
        } else if (key == KeyEvent.VK_X) { // Key == gather
            isKeyPressed[KEY_GATHER] = true;
        } else if (key == KeyEvent.VK_R) {
            isKeyPressed[KEY_RELOAD] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int key = ke.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            isKeyPressed[KEY_LEFT] = false;
        } else if (key == KeyEvent.VK_UP) {
            isKeyPressed[KEY_UP] = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            isKeyPressed[KEY_RIGHT] = false;
        } else if (key == KeyEvent.VK_DOWN) {
            isKeyPressed[KEY_DOWN] = false;
        } else if (key == KeyEvent.VK_Z) {
            isKeyPressed[KEY_SHOOT] = false;
        } else if (key == KeyEvent.VK_X) {
            isKeyPressed[KEY_GATHER] = false;
        } else if (key == KeyEvent.VK_R) {
            isKeyPressed[KEY_RELOAD] = false;
        }
    }

    public boolean isKeyPressedLeft() {
        return isKeyPressed[KEY_LEFT];
    }

    public boolean isKeyPressedRight() {
        return isKeyPressed[KEY_RIGHT];
    }

    public boolean isKeyPressedUp() {
        return isKeyPressed[KEY_UP];
    }

    public boolean isKeyPressedDown() {
        return isKeyPressed[KEY_DOWN];
    }
    
    public boolean isKeyPressedShoot() {
        return isKeyPressed[KEY_SHOOT];
    }

    public boolean isKeyPressedGather() {
        return isKeyPressed[KEY_GATHER];
    }
    
    public boolean isKeyPressedReload() {
        return isKeyPressed[KEY_RELOAD];
    }
    
    public boolean isKeyPressedLeftSolo() {
        return (isKeyPressedLeft() && !isKeyPressedRight());
    }

    public boolean isKeyPressedRightSolo() {
        return (isKeyPressedRight() && !isKeyPressedLeft());
    }

    public boolean isKeyPressedUpSolo() {
        return (isKeyPressedUp() && !isKeyPressedDown());
    }

    public boolean isKeyPressedDownSolo() {
        return (isKeyPressedDown() && !isKeyPressedUp());
    }
    
    public void setKeyPressedShoot(boolean status) {
        isKeyPressed[KEY_SHOOT] = status;
        return;
    }
}