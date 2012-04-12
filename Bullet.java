/*
 */
package hummingbird;

import hummingbird.liveentities.Unit;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author davidquiring
 */
public class Bullet extends Unit{

    final static public int DIRECTION_UP = 0;
    final static public int DIRECTION_DOWN = 1;
    final static public int DIRECTION_LEFT = 2;
    final static public int DIRECTION_RIGHT = 3;
    final private int SPEED_DEFAULT = 0;
    private File[] unitImages = new File[2];
    private int speed;
    private int direction;
    private int x;
    private int y;
    private int height;
    private int width;

    public Bullet(int x, int y) {
        super(x, y);
    }

    public File[] getUnitImages() {
        return unitImages;
    }

    public void setUnitImages(File[] unitImages) {
        this.unitImages = unitImages;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    private int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public int[] getPosition() {
        return new int[]{getX(), getY()};
    }

    public void setPosition(int[] pos) {
        setX(pos[0]);
        setY(pos[1]);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean checkCollision(Unit units) {
        boolean hit = false;

        int distance = (int)Math.sqrt(
                Math.pow((y - this.height), 2)
                + Math.pow((x - this.width), 2)
                );
        int size = (int)Math.sqrt((Math.pow(width, 2) + Math.pow(height, 2)));
        
        if (distance <= (x + size)) {
            hit = true;
            System.err.println("hit");
        }

        return hit;
    }
}
