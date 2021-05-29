package gamepieces;

import interfaces.Sprite;
import shapes.Line;
import shapes.Point;
import biuoop.DrawSurface;
import utilities.CollisionInfo;
import gamemanagement.GameLevel;
import gamemanagement.GameEnvironment;
import utilities.Velocity;
import java.awt.Color;

/**
 * The type Ball.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 2.1
 * @since 2020-05-24
 */
public class Ball implements Sprite {

    /**
     * Privates.
     * <p>Every ball has a central point, a radius, a color and velocity.
     * In addition, a ball should know it's game environment.</p>
     */
    private int radius;
    private Point center;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment game;

    /**
     * Constructors - set 1.
     * <p>Initialize a ball with given center, radius and color.
     * Also sets default values for velocity and game environment.</p>
     *
     * @param center a value to be set for center.
     * @param r      a value to be set for radius.
     * @param color  a value to be set for color.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.game = null;
    }

    /**
     * Constructors - set 2.
     * <p>Initialize a ball with given central point's values, radius and color.
     * Also sets default values for velocity and game environment.</p>
     *
     * @param x     a value to be set for the X value of the center.
     * @param y     a value to be set for the Y value of the center.
     * @param r     a value to be set for radius.
     * @param color a value to be set for color.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Gives access to the X value of the center of this ball.
     *
     * @return X value of the center of this ball as an int.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gives access to the Y value of the center of this ball.
     *
     * @return Y value of the center of this ball as an int.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gives access to the size (radius) of the ball.
     *
     * @return radius of this ball as an int.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gives access to the color of the ball.
     *
     * @return color of this ball as a java.awt.Color type.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on a given surface.
     *
     * @param surface the surface to draw the ball on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), radius);
        surface.setColor(Color.BLACK);
        surface.fillCircle((int) center.getX(), (int) center.getY(), 1);
        surface.drawCircle((int) center.getX(), (int) center.getY(), radius);    }

    /**
     * Does a job when timePassed() called.
     *
     * <p>Calls moveOneStep() to move the ball.</p>
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add this ball to a given game's sprite collection.
     *
     * @param g the game to which the ball should be added.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove this ball from a given game's sprite collection.
     *
     * @param g the game from which the ball should be removed.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * Sets velocity by given velocity type.
     *
     * @param v given velocity to be set for ball velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity by given velocity's delta-X and delta-Y.
     *
     * @param dx delta-X -- the change in X-axis.
     * @param dy delta-Y -- the change in Y-axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gives access to the current velocity of the ball.
     *
     * @return velocity of this ball as a gameUtility.Velocity type.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Gives access to the ball's game environment.
     *
     * @return the game environment.
     */
    public GameEnvironment getGame() {
        return this.game;
    }

    /**
     * Sets game environment by given gameUtility.GameEnvironment type.
     *
     * @param g the game environment to be set for this ball.
     */
    public void setGame(GameEnvironment g) {
        this.game = g;
    }

    /**
     * This method changes the location of the ball by one step, according to it's velocity.
     */
    public void moveOneStep() {

        // Checks the balls next location.
        Point next = this.getVelocity().applyToPoint(this.center);

        // Compute ball trajectory - from it's current location to the next.
        Line trajectory = new Line(this.center, next);

        // Checks if moving on this trajectory will hit anything.
        CollisionInfo cInf = game.getClosestCollision(trajectory);

        // If no, then moves the ball to the end of the trajectory.
        if (cInf == null) {
            this.center = next;
        } else {
            // Otherwise, sets X and Y values for a new location - a radius before it hits the object.
            double dx = this.getVelocity().getX();
            double dy = this.getVelocity().getY();
            double newX, newY;
            if (dx > 0) {
                newX = cInf.collisionPoint().getX() - radius;
            } else if (dx < 0) {
                newX = cInf.collisionPoint().getX() + radius;
            } else {
                newX = cInf.collisionPoint().getX();
            }
            // Special case to prevent the ball from relocating when colliding the bottom of a moving paddle.
            if (dy > 0 && this.center.getY() > 570) {
                newY = 586;
            } else if (dy > 0) {
                newY = cInf.collisionPoint().getY() - radius;
            } else if (dy < 0) {
                newY = cInf.collisionPoint().getY() + radius;
            } else {
                newY = cInf.collisionPoint().getY();
            }
            // Sets new location, notifying hit object and sets new velocity.
            this.center = new Point(newX, newY);
            Velocity v = cInf.collisionObject().hit(this, cInf.collisionPoint(), this.getVelocity());
            setVelocity(v);

        }

    }

}