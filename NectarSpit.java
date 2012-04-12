/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hummingbird;

import java.io.File;

/**
 *
 * @author mark
 */
public class NectarSpit extends Bullet {
    final private int SPEED_DEFAULT = 25;
    
    public NectarSpit(int x, int y, int dir) {
        super(x, y);
        setDirection(dir);
        
        setSpeed(SPEED_DEFAULT);
        

        File[] image = {new File("images/nectar_bullet.png")};

        setUnitImages(image);
        
        //jo
    }
}
