/*
 * Handles the colitions detection
 */
package hummingbird;

/**
 *
 * @author davidquiring
 */
public class SquareHitBox {

    private int height;
    private int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    /*public SquareHitBox(int height, int width) {
        this.height = height;
        this.width = width;
    }*/

    public int size() {
        int size;

        size = (int)Math.sqrt((Math.pow(width, 2) + Math.pow(height, 2)));

        return size;

    }

    public boolean isHit(SquareHitBox Box) {
        boolean hit = false;

        double distance;

        distance = Math.sqrt(Math.pow((Box.height - this.height), 2) + Math.pow((Box.width - this.width), 2));

        if (distance <= (Box.size() + this.size())) {
            hit = true;
            System.out.println("hit");
        }

        return hit;
    }
    
    public boolean isHit(int x, int y) {
        boolean hit = false;

        double distance;

        distance = Math.sqrt(Math.pow((y - this.height), 2) + Math.pow((x - this.width), 2));

        if (distance <= (x + this.size())) {
            hit = true;
            System.out.println("hit");
        }

        return hit;
    }
}
