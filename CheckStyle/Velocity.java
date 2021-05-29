package utilities;

import shapes.Point;

/**
 * The type Velocity.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 2.0
 * @since 2020 -04-28
 */
public class Velocity {

    /**
     * Private.
     * <p>A velocity has a delta-X and delta-Y values.</p>
     */
    private double dx, dy;

    /**
     * Constructors - set 1.
     * <p>Sets dx and dy by given values.</p>
     *
     * @param dx a value to be set for dx.
     * @param dy a value to be set for dy.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * <p>Returns a velocity calculated by given angle and speed values.</p>
     *
     * @param angle an angle to calculate dx and dy (together with speed).
     * @param speed a speed to calculate dx and dy (together with angle).
     * @return new velocity calculated by angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        // Calculates dx and dy with trigonometry.
        double dx = speed * Math.cos(Math.PI * (angle / 180 - 0.5));
        double dy = speed * Math.sin(Math.PI * (angle / 180 - 0.5));

        // Return new velocity with the new dx and dy.
        return new Velocity(dx, dy);
    }

    /**
     * Applies velocity to a point.
     * <p>Takes a point (x, y) and return a point (x+dx, y+dy).</p>
     *
     * @param p given point to set new velocity.
     * @return new point with values of the previous point plus velocity's values.
     */
    public Point applyToPoint(Point p) {
        double targetX = p.getX() + this.dx;
        double targetY = p.getY() + this.dy;
        return new Point(targetX, targetY);
    }

    /**
     * Returns velocity's X value.
     *
     * @return X value as an double.
     */
    public double getX() {
        return dx;
    }

    /**
     * Returns velocity's Y value.
     *
     * @return Y value as an double.
     */
    public double getY() {
        return dy;
    }
}