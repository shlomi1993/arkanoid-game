package shapes;

/**
 * The type Point.
 *
 * @author Shlomi Ben-Shushan id: 311408264 <shlomiggl @ gmail.com>
 * @version 2.0
 * @since 2020-04-10
 */
public class Point {

    /**
     * Privates.
     * <p>a point has X and Y values.</p>
     */
    private double x, y;

    /**
     * Constructors - assign X and Y values.
     *
     * @param x a value to be set for x.
     * @param y a value to be set for y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Measures the distance between this point to another and return it.
     * <p>The distance is calculated by distance formula.</p>
     *
     * @param other the point this method calculate the distance to.
     * @return distance as a double type variable.
     */
    public double distance(Point other) {

        // Define delta-X and delta-Y.
        double deltaX = this.x - other.x;
        double deltaY = this.y - other.y;

        // Calculate distance and return.
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Checks if a given point is equal to this point.
     * <p>Two points are equal if they have the same X and Y values.</p>
     *
     * @param other the point for comparision.
     * @return True if the points are equal, False otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Returns the X value of this point.
     *
     * @return X value as a double.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns the Y value of this point.
     *
     * @return Y value as a double.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Checks if this point is located on a given segment.
     * <p>This method allows calculation deviation to deal with doubles calculation.</p>
     *
     * @param line the line that represent a segment.
     * @return the true if the point is on the segment, false otherwise.
     */
    public boolean onSegment(Line line) {

        // Define minimum and maximum for X and Y values.
        double maxX = Math.max(line.start().getX(), line.end().getX());
        double maxY = Math.max(line.start().getY(), line.end().getY());
        double minX = Math.min(line.start().getX(), line.end().getX());
        double minY = Math.min(line.start().getY(), line.end().getY());

        // Calculation deviation.
        double d = 0.0000000001;

        // Check if this point is in between minimum and maximum and return result.
        return minX - d <= this.x && this.x <= maxX + d && minY - d <= this.y && this.y <= maxY + d;

    }

}

