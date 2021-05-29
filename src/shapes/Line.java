package shapes;

import java.util.List;

/**
 * The type Line.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 2.0
 * @since 2020-04-28
 */
public class Line {

    /**
     * Privates.
     * <p>A line has start and end points.</p>
     */
    private Point start, end;

    /**
     * Constructors - set 1.
     * <p>Assign start and end values.</p>
     *
     * @param start a value to be set for start.
     * @param end   a value to be set for end.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructors - set 2.
     * <p>Assign start and end values - by getting X and Y values.</p>
     *
     * @param x1 X value to be set for start point.
     * @param y1 Y value to be set for start point.
     * @param x2 X value to be set for end point.
     * @param y2 Y value to be set for end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * This method uses Shapes.Point's distance method to return the length of the line.
     *
     * @return distance between start and end points as a double.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle of the line segment.
     * <p>It does it by calculating the middle between two points.</p>
     *
     * @return middle point as a Shapes.Point type.
     */
    public Point middle() {

        // Calculate middle-X and middle-Y values.
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;

        // Return new point with those new middle X and Y values.
        return new Point(middleX, middleY);
    }

    /**
     * Returns start point of the line.
     *
     * @return start point as a Shapes.Point type.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns end point of the line.
     *
     * @return end point as a Shapes.Point type.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Calculates and returns the slope of this line by Slope formula.
     *
     * @return slope of the line as a double.
     */
    public double slope() {

        // Calculate delta-X and delta-Y.
        double deltaX = this.start.getX() - this.end.getX();
        double deltaY = this.start.getY() - this.end.getY();

        // Edge-case: there is no real difference between 0.0 and -0.0.
        if (Math.abs(deltaY) < Double.MIN_VALUE || deltaY == 0) {
            return 0;
        }

        // Return delta-X / delta-Y which is the slope by definition.
        return deltaY / deltaX;
    }

    /**
     * This method returns the free variable of a straight line equation.
     * <p>As known, the free variable represent the Y value of the intersection Shapes.Point
     * between an infinite line and Y-axis.</p>
     *
     * @return free variable of straight line equation as a double.
     */
    public double yCut() {
        return this.start.getY() - this.slope() * this.start.getX();
    }

    /**
     * This method returns the intersection point between two lines.
     * <p>To find it, the method uses simple Algebra to find X and than Y.</p>
     * <p>If the lines are parallel or coalescing -- return null</p>
     *
     * @param other the line for intersection check.
     * @return new intersection point as a Shapes.Point type.
     */
    public Point intersectionPoint(Line other) {

        // Define straight line equation variables.
        double m1 = this.slope();
        double m2 = other.slope();
        double b1 = this.yCut();
        double b2 = other.yCut();

        // Dealing with undefined slope.
        double pInf = Double.POSITIVE_INFINITY;
        double nInf = Double.NEGATIVE_INFINITY;
        if (m1 == pInf || m1 == nInf) {
            double x = this.start.getX();
            double y = m2 * x + b2;
            return new Point(x, y);
        }
        if (m2 == pInf || m2 == nInf) {
            double x = other.start.getX();
            double y = m1 * x + b1;
            return new Point(x, y);
        }

        // Edge-case: if slopes are equal -- return null.
        if (Math.abs(m1 - m2) < Double.MIN_VALUE) {
            return null;
        }

        // Find X and than Y.
        double x = (b1 - b2) / (m2 - m1);
        double y = m1 * x + b1;

        // Return new point with new X and Y values.
        return new Point(x, y);

    }

    /**
     * This method checks if this line intersect with other given line.
     * <p>If the other line is not defined or there is no intersection point or there is an intersection
     * point but it is not on the segment -- return false.</p>
     * <p>If the method finds an intersection point which is on the segment -- return true.</p>
     *
     * @param other the line for intersection check.
     * @return true for intersecting and false otherwise.
     */
    public boolean isIntersecting(Line other) {

        // Edge-cases.
        if (this.equals(other) || this.intersectionPoint(other) == null) {
            return false;
        }

        // Check if the intersection point located on the segment and if so, return true, otherwise return false.
        Point inter = intersectionPoint(other);
        return inter.onSegment(this) && inter.onSegment(other);

    }

    /**
     * This method returns the intersection point if the lines intersect.
     *
     * @param other the line for intersection check.
     * @return intersection point as Shapes.Point type.
     */
    public Point intersectionWith(Line other) {

        // Return the intersection point if exist.
        if (this.isIntersecting(other)) {
            return intersectionPoint(other);
        }
        // Otherwise return null.
        return null;
    }

    /**
     * This method checks if two line are equals.
     * <p>Equals lines defined as lines with the same start and end points.</p>
     *
     * @param other the line for equality check.
     * @return true if the lines are equals and false otherwise.
     */
    public boolean equals(Line other) {
        return this.start().equals(other.start()) && this.end().equals(other.end());
    }

    /**
     * This method Checks if a line is intersecting a rectangle.
     * <p>If so, returns the closest intersection point to the start of this line.
     * id not, returns null.</p>
     *
     * @param rect the rectangle to check if the line intersect it.
     * @return the closest intersection point to the start of this line or null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        // Initialize a list to hold the intersection points of the line with the given rectangle.
        List<Point> interPoints = rect.intersectionPoints(this);

        // If the list is empty, there is no chance for intersection so return null.
        if (interPoints.isEmpty()) {
            return null;
        }

        // This algorithm finds the closest point from the list to the start of this line.
        Point closest = interPoints.get(0);
        double curDist;
        double minDist = interPoints.get(0).distance(this.start);
        for (Point point : interPoints) {
            curDist = point.distance(this.start);
            if (curDist < minDist) {
                minDist = curDist;
                closest = point;
            }
        }
        return closest;
    }

}