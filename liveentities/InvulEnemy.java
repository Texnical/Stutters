/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hummingbird.liveentities;

/**
 *
 * @author mark
 */
public class InvulEnemy extends Enemy {
    public InvulEnemy(int x, int y) {
        super(x, y);
        
        this.canBeHit = false;
    }
}
