/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hummingbird.liveentities;

import hummingbird.Benefactor;
import java.io.File;

/**
 *
 * @author mark
 */
public class Fly extends Benefactor {
    final private int RESULT_HEALTH_DEFAULT = 0;

    public Fly(int x, int y) {
        super(x, y);
        
        setResultHealth(RESULT_HEALTH_DEFAULT);
        
        File[] images = {new File("images/fly.png")};
        
        setUnitImages(images);
    }
}
