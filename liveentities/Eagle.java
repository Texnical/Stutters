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
public class Eagle extends InvulEnemy {
    final private int DAMAGE_DEFAULT = 100;
    final private int SPEED_DEFAULT = 1;

    public Eagle(int x, int y) {
        super(x, y);
        
        setDamage(DAMAGE_DEFAULT);
        setSpeed(SPEED_DEFAULT);

        File images[] = {new File("images/talon_open.png"), new File("images/talon_closed.png")};
        
        setUnitImages(images);

    }
}
