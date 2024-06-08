package coordinates;

/**
 * The Point2D class represents a point in a 2D coordinate system.
 */
public class Point2D {
    private int x;
    private int y;

    /**
     * Gets the x-coordinate of the point.
     * 
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the point.
     * 
     * @param x The x-coordinate to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the point.
     * 
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the point.
     * 
     * @param y The y-coordinate to set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Constructs a Point2D with the specified coordinates.
     * 
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     */
    public Point2D(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Returns a string representation of the point.
     * 
     * @return A string representing the point's coordinates.
     */
    public String toString() {
        return "POINT2D : TOSTRING() : Point coordinates: " + this.getX() + "," + this.getY();
    }

    /**
     * Checks if the specified object is equal to this point.
     * 
     * @param o The object to compare.
     * @return True if the object is a Point2D with the same coordinates, false otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof Point2D) {
            Point2D t = (Point2D) o;
            if (t.getX() == this.getX() && t.getY() == this.getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Translates the point by the specified x amount.
     * 
     * @param x The amount to translate the x-coordinate.
     */
    public void translateX(int x) {
        this.setX(this.getX() + x);
    }

    /**
     * Translates the point by the specified y amount.
     * 
     * @param y The amount to translate the y-coordinate.
     */
    public void translateY(int y) {
        this.setY(this.getY() + y);
    }
}
