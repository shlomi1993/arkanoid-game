package gamepieces;

import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamemanagement.GameLevel;
import utilities.Velocity;
import java.awt.Color;

/**
 * The type Paddle.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-04-28
 */
public class Paddle implements Sprite, Collidable {

    /**
     * Privates.
     * <p>A line has start and end points.</p>
     */
    private Rectangle rect;
    private Color color;
    private KeyboardSensor keyboard;
    private int paddleSpeed;

    /**
     * Instantiates a new GamePieces.Paddle.
     *
     * @param gui       the GUI that the paddle will be on - required for keyboard sensors.
     * @param levelInfo The level of the paddle -- each level have different properties for paddle.
     */
    public Paddle(GUI gui, LevelInformation levelInfo) {

        int w = levelInfo.paddleWidth();
        int x = (int) (400 - (w / 2));

        this.rect = new Rectangle(x, 570, w, 10);
        this.color = Color.ORANGE;
        this.paddleSpeed = levelInfo.paddleSpeed();
        this.keyboard = gui.getKeyboardSensor();

    }

    /**
     * Moves the paddle 10 pixels left.
     */
    public void moveLeft() {
        double x = this.rect.getUpperLeft().getX();
        if (x > 20) {
            double y = this.rect.getUpperLeft().getY();
            Point next = new Point(x - this.paddleSpeed, y);
            double w = this.rect.getWidth();
            double h = this.rect.getHeight();
            this.rect = new Rectangle(next, w, h);
        }

    }

    /**
     * Moves the paddle 10 pixels right.
     */
    public void moveRight() {
        double x = this.rect.getUpperLeft().getX();
        if (x < 780 - this.rect.getWidth()) {
            double y = this.rect.getUpperLeft().getY();
            Point next = new Point(x + this.paddleSpeed, y);
            double w = this.rect.getWidth();
            double h = this.rect.getHeight();
            this.rect = new Rectangle(next, w, h);
        }

    }

    /**
     * Does a job when timePassed() called.
     * Calls moveRight() or moveLeft() methods according to the pressed keyboard key.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
    }

    /**
     * Draw the GamePieces.Paddle's Shapes.Rectangle on a given surface.
     *
     * @param d the surface to draw the rectangle on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        Block b = new Block(this.rect, this.color);
        b.drawOn(d);
    }

    /**
     * Returns the Shapes.Rectangle of this GamePieces.Paddle.
     *
     * @return the Shapes.Rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Notifies collided object with a given velocity and collisionPoint, and return a new velocity.
     *
     * @param collisionPoint    the collision point of the two objects.
     * @param currentVelocity   the current velocity of the hitting object.
     * @param hitter            the ball that hit this paddle.
     * @return a new velocity that is determined by the segment of the collision.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        // Calculating speed.
        double dx = currentVelocity.getX();
        double dy = currentVelocity.getY();
        double speed = Math.sqrt(dx * dx + dy * dy);

        // len is the length of each of the segments.
        double len = this.getCollisionRectangle().getUpper().length() / 5;

        // x and y are the values of the upper-left point of the paddles's rectangle.
        double x = this.getCollisionRectangle().getUpperLeft().getX();
        double y = this.getCollisionRectangle().getUpperLeft().getY();

        // Divides the upper edge of the paddle to 5 segments and store them in an array.
        Line[] segments = new Line[5];
        for (int i = 0; i < segments.length; i++) {
            double nextX = x + i * len;
            Point start = new Point(nextX, y);
            Point end = new Point(nextX + len, y);
            segments[i] = new Line(start, end);
        }

        // Checks which segment or edge was hit.
        boolean hitSegment1 = collisionPoint.onSegment(segments[0]);
        boolean hitSegment2 = collisionPoint.onSegment(segments[1]);
        boolean hitSegment3 = collisionPoint.onSegment(segments[2]);
        boolean hitSegment4 = collisionPoint.onSegment(segments[3]);
        boolean hitSegment5 = collisionPoint.onSegment(segments[4]);
        boolean hitRight = collisionPoint.onSegment(this.rect.getRight());
        boolean hitLeft = collisionPoint.onSegment(this.rect.getLeft());

        // Sets different velocities for different hit segments.
        if (hitSegment1) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (hitSegment2) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        if (hitSegment3) {
            dy = -dy;
        }
        if (hitSegment4) {
            return Velocity.fromAngleAndSpeed(30, speed);
        }
        if (hitSegment5) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }
        if (hitRight || hitLeft) {
            dx = -dx;
        }

        return new Velocity(dx, dy);

    }

    /**
     * Add this paddle to a given game's sprite collection.
     *
     * @param g the game to which the paddle should be added.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}