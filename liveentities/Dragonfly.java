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
public class Dragonfly extends Enemy {
    final private int SPEED_DEFAULT = 10;
    final private int LIFE_DEFAULT = 25;
    final private int DAMAGE_DEFAULT = 25;

    public Dragonfly(int x, int y) {
        super(x, y);
        
        setDamage(DAMAGE_DEFAULT);
        setLife(LIFE_DEFAULT);
        setSpeed(SPEED_DEFAULT);
        
        File images[] = {new File("images/dragonfly_up.png"), new File("images/dragonfly_down.png")};
        
        setUnitImages(images);
    }
}
