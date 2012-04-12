/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hummingbird.liveentities;

import hummingbird.SquareHitBox;
import java.awt.Image;
import java.io.File;

/**
 *
 * @author mark
 */
public abstract class Unit {

    final public int NUMBER_IMAGES = 2;
    private int speed;
    private int life;
    private int damage;
    private File[] unitImages = new File[NUMBER_IMAGES];
    private int x;
    private int y;
    private int height;
    private int width;
    private int size_x;
    private int size_y;

    public Unit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    private int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[] getPosition() {
        return new int[]{getX(), getY()};
    }

    public void setPosition(int[] pos) {
        setX(pos[0]);
        setY(pos[1]);
    }

    public File[] getUnitImages() {
        return unitImages;
    }

    public void setUnitImages(File[] unitImage) {
        this.unitImages = unitImage;
    }

    public boolean checkCollision(Unit collidedWith) {
        boolean isHit = false;

        if (((this.x + this.width <= collidedWith.x)
                || (collidedWith.x + collidedWith.width <= this.x))
                &&
                ((this.y + this.height <= collidedWith.y)
                || (collidedWith.y + collidedWith.height <= this.y))) {
            isHit = true;
        }

        System.err.println("Collided!");

        return isHit;
    }
}
