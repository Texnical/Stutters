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
public class Flower extends Benefactor {
    final private int RESULT_AMMO_DEFAULT = 5;

    public Flower(int x, int y) {
        super(x, y);
        
        setResultAmmo(RESULT_AMMO_DEFAULT);
        
        File[] images = {new File("images/flower.png")};
        
        setUnitImages(images);
    }
    
}
