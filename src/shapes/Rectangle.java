package shapes;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-04-28
 */
public class Rectangle {

    /**
     * Privates.
     * <p>A rectangle has an upper-left point, width and height.
     * The rectangle's lines are created according to its upper-left point.</p>
     */
    private Point upperLeft;
    private double width;
    private double height;
    private Line upper;
    private Line right;
    private Line bottom;
    private Line left;

    /**
     * Instantiates a new Shapes.Rectangle.
     *
     * @param upperLeft the a value to be set for upper left point.
     * @param width     the a value to be set for width.
     * @param height    the a value to be set for height.
     */
    public Rectangle(Point upperLeft, double width, double height) {

        // Shapes.Rectangle basic components.
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;

        // Shapes.Rectangle outline.
        this.upper = new Line(upperLeft, new Point(upperLeft.getX() + width, upperLeft.getY()));
        this.right = new Line(upper.end(), new Point(upper.end().getX(), upper.end().getY() + height));
        this.bottom = new Line(right.end(), new Point(right.end().getX() - width, right.end().getY()));
        this.left = new Line(bottom.end(), upperLeft);

    }

    /**
     * Create a new rectangle with location, width and height.
     *
     * @param upperLeftX a value to be set for upper-left x.
     * @param upperLeftY a value to be set for upper-left y.
     * @param width      a value to be set for width.
     * @param height     a value to be set for height.
     */

    public Rectangle(double upperLeftX, double upperLeftY, double width, double height) {
        this(new Point( upperLeftX, upperLeftY), width, height);
    }

    /**
     * Return a List of intersection points with the specified line.
     *
     * @param line the line to check if have intersections with this rectangle.
     * @return a (possibly empty) list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        // Initialize a list to store intersection points.
        List<Point> interPoints = new ArrayList<Point>();

        // Using Shapes.Line isIntersecting() method to check for intersection with every rectangle edge.
        if (line.isIntersecting(this.getUpper())) {
            interPoints.add(line.intersectionWith(this.getUpper()));
        }
        if (line.isIntersecting(this.getRight())) {
            interPoints.add(line.intersectionWith(this.getRight()));
        }
        if (line.isIntersecting(this.getBottom())) {
            interPoints.add(line.intersectionWith(this.getBottom()));
        }
        if (line.isIntersecting(this.getLeft())) {
            interPoints.add(line.intersectionWith(this.getLeft()));
        }

        // Return list.
        return interPoints;

    }

    /**
     * Returns the width of this rectangle.
     *
     * @return double width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of this rectangle.
     *
     * @return double height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left point of this rectangle.
     *
     * @return upper-left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Returns the upper edge of this rectangle.
     *
     * @return upper edge as a line.
     */
    public Line getUpper() {
        return this.upper;
    }

    /**
     * Returns the right edge of this rectangle.
     *
     * @return right edge as a line.
     */
    public Line getRight() {
        return this.right;
    }

    /**
     * Returns the bottom edge of this rectangle.
     *
     * @return bottom edge as a line.
     */
    public Line getBottom() {
        return this.bottom;
    }

    /**
     * Returns the left edge of this rectangle.
     *
     * @return left edge as a line.
     */
    public Line getLeft() {
        return this.left;
    }
}