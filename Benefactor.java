/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hummingbird;

import hummingbird.liveentities.Unit;
import java.io.File;

/**
 *
 * @author mark
 */
public abstract class Benefactor extends Unit {
    private int resultHealth;
    private int resultAmmo;

    public Benefactor(int x, int y) {
        super(x, y);
    }

    public int getResultAmmo() {
        return resultAmmo;
    }

    public void setResultAmmo(int resultAmmo) {
        this.resultAmmo = resultAmmo;
    }

    public int getResultHealth() {
        return resultHealth;
    }

    public void setResultHealth(int resultHealth) {
        this.resultHealth = resultHealth;
    }
}
