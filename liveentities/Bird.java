/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hummingbird.liveentities;

import java.io.File;

/**
 *
 * @author mark
 */
public final class Bird extends Unit {

    // Constants
    final static private int SPEED_DEFAULT = 15;
    final static private int LIFE_DEFAULT = 100;
    final static private int DAMAGE_DEFAULT = 25;
    final static private int AMMO_DEFAULT = 25;
    
    final static public int SIZE_X = 13;
    final static public int SIZE_Y = 13;

    // Variables
    private static int ammo = AMMO_DEFAULT;

    public Bird(int x, int y) {
        super(x, y);

        setSpeed(SPEED_DEFAULT);
        setLife(LIFE_DEFAULT);
        setDamage(DAMAGE_DEFAULT);
        setAmmo(AMMO_DEFAULT);
        setWidth(SIZE_X);
        setHeight(SIZE_Y);
        
        File images[] = {new File("images/stutter_up.png"), new File("images/stutter_down.png")};
        
        setUnitImages(images);
    }

    public static int getAmmo() {
        return ammo;
    }

    public static void setAmmo(int ammo) {
        Bird.ammo = ammo;
    }
    
    public static int getDefaultAmmo() {
        return AMMO_DEFAULT;
    }
}
