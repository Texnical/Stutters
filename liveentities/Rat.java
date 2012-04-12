
package hummingbird.liveentities;

import java.io.File;

/**
 *
 * @author Lancellot
 */
public class Rat extends InvulEnemy{
    final private int DAMAGE_DEFAULT = 40;
    final private int SPEED_DEFAULT = 1;
    
    public Rat(int x, int y) {
        super(x, y);
        
        setDamage(DAMAGE_DEFAULT);
        setSpeed(SPEED_DEFAULT);

        File images[] = {new File("images/rat_open.png"), new File("images/rat_closed.png")};
        
        setUnitImages(images);
    
    }
}
