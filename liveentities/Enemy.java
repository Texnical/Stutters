/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hummingbird.liveentities;

/**
 *
 * @author mark
 */
public class Enemy extends Unit {
    protected boolean canBeHit;

    public Enemy(int x, int y) {
        super(x, y);

        this.canBeHit = true;
    }

    public boolean canBeHit() {
        return canBeHit;
    }
}
