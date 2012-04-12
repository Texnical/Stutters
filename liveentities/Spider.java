
package hummingbird.liveentities;

import java.io.File;

/**
 *
 * @author Lancellot
 */
public class Spider extends Enemy {
    final private int LIFE_DEFAULT = 500;
    final private int DAMAGE_DEFAULT = 50;
    
    
    public Spider(int x, int y) {
        super(x, y);

        setLife(LIFE_DEFAULT);
        setDamage(DAMAGE_DEFAULT);
        
        File images[] = {new File("images/spider_up.png"), new File("images/spider_down.png")};
        
        setUnitImages(images);
    }
    
}
