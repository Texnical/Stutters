package hummingbird;

import hummingbird.liveentities.Unit;
import hummingbird.liveentities.Dragonfly;
import hummingbird.liveentities.Bird;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {

    // Constants
    private static final int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;
    private static final int FPS = 30;
    private static final int MAX_OBJECTS = 100;
    private static final int SCROLL_SPEED = 2;
    private static final int X = 0;
    private static final int Y = 1;
    // Global variables
    private static Random rand; // Used later for generating random values
    private static JFrame game;
    private static JPanel view;
    private static InputListener input;
    private static byte frame;
    private static int backgroundOffset;
    // Statics
    public static ArrayList<Unit> units;
    public static ArrayList<Bullet> projectiles;
    private static Unit stutter;
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        game = new JFrame("Stutter");
        view = new GamePanel();

        units = new ArrayList<Unit>(MAX_OBJECTS);
        projectiles = new ArrayList<Bullet>(MAX_OBJECTS);
        frame = 0;

        //game.pack() will set the frame's size using the panel's preferred size
        //game.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        game.setLayout(new BorderLayout());
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        game.add(view, BorderLayout.CENTER);
        game.addKeyListener(input);

        game.pack();
        game.setResizable(false);
        game.setLocationRelativeTo(null);
        view.requestFocusInWindow();
        game.setVisible(true);

        run();
    }

    private static void run() {
        setup();

        long startTime;
        while (true) {
            startTime = System.currentTimeMillis();

            if (frame % 90 == 0) {
                spawn();
            }

            physics();

            game.repaint();

            clean(projectiles);

            limitFPS(startTime);

            frame++;
            frame %= 90; // 2^4, for modulo checks with animations
        }
    }

    static void setup() {
        // Start Stutter in the middle of the screen.
        stutter = new Bird((WINDOW_WIDTH / 2) - (Bird.SIZE_X * 3),
                (WINDOW_HEIGHT / 2) - (Bird.SIZE_Y * 3));
        rand = new Random();

        backgroundOffset = 0;
    }

    // Manage the input from the user and run the proper procedures.
    private static void handleInput() {
        // Variables
        int[] position = stutter.getPosition();

        // Conditions and Actions
        if (input.isKeyPressedLeftSolo()) { // Set left motion
            if (position[X] > 0) {
                position[X] -= stutter.getSpeed();
            }
        } else if (input.isKeyPressedRightSolo()) { // Set right motion
            if (position[X] < (WINDOW_WIDTH - (Bird.SIZE_X * 3))) {
                position[X] += stutter.getSpeed();
            }
        }

        if (input.isKeyPressedUpSolo()) { // Set up motion
            if (position[Y] > 0) {
                position[Y] -= stutter.getSpeed();
            }
        } else if (input.isKeyPressedDownSolo()) { // Set down motion
            if (position[Y] < (WINDOW_HEIGHT - (Bird.SIZE_Y * 3))) {
                position[Y] += stutter.getSpeed();
            }
        }

        if (input.isKeyPressedShoot()) { // Set bullet action
            int ammo = Bird.getAmmo();

            if (ammo > 0) {
                projectiles.add(new NectarSpit(position[X] + (Bird.SIZE_X * 3), position[Y] + (3 * 3),
                        Bullet.DIRECTION_RIGHT));
                Bird.setAmmo(ammo - 1);
            }

            input.setKeyPressedShoot(false);
        }

        if (input.isKeyPressedReload()) {
            Bird.setAmmo(Bird.getDefaultAmmo());
        }

        // Make sure Stutters doesn't go off the top or left of the screen.
        if (position[X] < 0) {
            position[X] = 0;
        } else if (position[X] > (WINDOW_WIDTH - (Bird.SIZE_X * 3))) {
            position[X] = WINDOW_WIDTH - (Bird.SIZE_X * 3);
        }

        if (position[Y] < 0) {
            position[Y] = 0;
        } else if (position[Y] > (WINDOW_HEIGHT - (Bird.SIZE_Y * 3))) {
            position[Y] = WINDOW_HEIGHT - (Bird.SIZE_Y * 3);
        }

        stutter.setPosition(position);
    }

    // Update the position of everything in-game for one frame.
    private static void physics() {
        handleInput();

        if (backgroundOffset < 1600) {
            backgroundOffset += SCROLL_SPEED;
        } else {
            backgroundOffset = 1600;
        }
        synchronized (units) {
            for (Unit unit : units) {
                if (unit == null) {
                    continue;
                }

                int[] unit_position;

                unit_position = unit.getPosition();

                if (!(unit instanceof Bird)) {
                    unit_position[X] -= SCROLL_SPEED + unit.getSpeed();
                    if (unit_position[X] == 0) { // hit left wall
                        units.remove(unit);
                        continue;
                    }

                }

                unit.setPosition(unit_position);
            }

            synchronized (projectiles) {
                for (Bullet proj : projectiles) {

                    int[] proj_position;

                    proj_position = proj.getPosition();

                    if (proj_position[X] <= 0 || proj_position[X] >= WINDOW_WIDTH // Left and right walls
                            || proj_position[Y] <= 0 || proj_position[Y] >= WINDOW_HEIGHT) { // top and bottom walls
                        projectiles.remove(proj);
                        continue;
                    }

                    synchronized(units) {
                        for (Unit unit : units) {
                            if (proj.checkCollision(unit)) {
                                units.remove(unit);
                            }
                        }
                    }

                    if (proj.getDirection() == Bullet.DIRECTION_LEFT) {
                        proj_position[X] -= proj.getSpeed();
                    } else if (proj.getDirection() == Bullet.DIRECTION_RIGHT) {
                        proj_position[X] += proj.getSpeed();
                    } else if (proj.getDirection() == Bullet.DIRECTION_UP) {
                        proj_position[Y] -= proj.getSpeed();
                    } else if (proj.getDirection() == Bullet.DIRECTION_DOWN) {
                        proj_position[Y] += proj.getSpeed();
                    }

                    proj.setPosition(proj_position);
                }
            }
        }
    }

    static void spawn() {
        // This is where we spawn enemies and other objects
        int spawnX = WINDOW_WIDTH;
        int spawnY = WINDOW_HEIGHT - 100;
        if (rand.nextInt(WINDOW_HEIGHT) >= WINDOW_HEIGHT - 40) {
            spawnY = rand.nextInt(WINDOW_HEIGHT) - 40;
        } else {
            spawnY = rand.nextInt(WINDOW_HEIGHT);
        }

        if (frame == 0) {
            units.add(new Dragonfly(spawnX, spawnY));
        }
    }

    // Function keeps game from running too quickly.
    static void limitFPS(long startTime) {
        try {
            long timePassedMillis = System.currentTimeMillis() - startTime;
            int millisPerFrame = 1000 / FPS;
            if (millisPerFrame - timePassedMillis > 0) {
                Thread.sleep(millisPerFrame - timePassedMillis);
            } // if framesPerMillis - timePassedMillis <= 0, game is lagging.
        } catch (InterruptedException ex) {
            System.err.println("Thread was interrupted. Exiting.");
            System.exit(1);
        }
    }

    // Function cleans up a given Bullet HashSet of null objects.
    static void clean(ArrayList<Bullet> Set) {
        for (Bullet single : Set) {
            if (single == null) {
                Set.remove(single);
                System.err.println("Removed bullet.");
            }
        }
    }

    // Manages the drawing of all things.
    static class GamePanel extends JPanel {

        final private boolean DOUBLE_BUFFERED = true;
        private BufferedImage backgroundImage;

        public GamePanel() {
            setDoubleBuffered(DOUBLE_BUFFERED);

            input = new InputListener();
            addKeyListener(input);

            try {
                backgroundImage = ImageIO.read(new File("images/level1.png"));
            } catch (IOException ex) {
                System.err.println("Couldn't load background image.");
                System.exit(1);
            }
        }

        @Override
        protected void paintComponent(Graphics grphcs) {
            Graphics2D g2d = (Graphics2D) grphcs;

            if (backgroundImage != null) {
                g2d.drawImage(backgroundImage, -backgroundOffset, 0, null);
            }


            try {
                int anim = 0;

                if ((frame % 2) == 1) {
                    anim = 1;
                }

                BufferedImage img = ImageIO.read(stutter.getUnitImages()[anim]);

                if (img != null) {
                    g2d.drawImage(img, stutter.getPosition()[X], stutter.getPosition()[Y],
                            (Bird.SIZE_X * 3), (Bird.SIZE_Y * 3), null);
                }
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }

            synchronized (units) {
                for (Unit unit : units) {
                    BufferedImage img = null;
                    try {
                        int anim = 0;

                        if ((frame % 2) == 1) {
                            anim = 1;
                        }

                        img = ImageIO.read(unit.getUnitImages()[anim]);
                    } catch (Exception ex) {
                        System.err.println("Something went wrong!");
                        System.err.println("Error: " + ex.getMessage());
                    }

                    if (img != null) {
                        g2d.drawImage(img, unit.getPosition()[X], unit.getPosition()[Y], null);
                    }
                }
            }

            synchronized (projectiles) {
                for (Bullet proj : projectiles) {
                    if (proj == null) {
                        continue;
                    }

                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(proj.getUnitImages()[0]);
                    } catch (Exception ex) {
                        System.err.println("Something went wrong!");
                        System.err.println("Error: " + ex.getMessage());
                    }

                    if (img != null) {
                        g2d.drawImage(img, proj.getPosition()[X], proj.getPosition()[Y], null);
                    }
                }
            }
        }
    }
}
