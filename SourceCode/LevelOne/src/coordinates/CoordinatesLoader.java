package coordinates;

/**
 * The CoordinatesLoader class is responsible for loading and managing 2D coordinates.
 */
public class CoordinatesLoader {
    private Point2D[][] coordinates;

    /**
     * Constructs a CoordinatesLoader with the specified number of rows and columns.
     * 
     * @param numRows The number of rows.
     * @param numCols The number of columns.
     */
    public CoordinatesLoader(int numRows, int numCols) {
        this.coordinates = new Point2D[numRows][numCols];
        this.fillCoordinates(numRows, numCols);
    }

    /**
     * Returns the Point2D object at the specified coordinates.
     * 
     * @param x The x-coordinate.
     * @param y The y-coordinate.
     * @return The Point2D object at the specified coordinates.
     */
    public Point2D getCoordinates(int x, int y) {
        return coordinates[y][x]; // Use y for rows and x for columns
    }

    /**
     * Translates the given point by the specified delta values.
     * 
     * @param point The original point.
     * @param dx    The change in x-coordinate.
     * @param dy    The change in y-coordinate.
     * @return The new Point2D object after translation, or null if out of bounds.
     */
    public Point2D translateCoordinates(Point2D point, int dx, int dy) {
        int x = (int) point.getX();
        int y = (int) point.getY();
        int newX = x + dx;
        int newY = y + dy;
        if (newY < 0 || newY >= coordinates.length || newX < 0 || newX >= coordinates[0].length) {
            return null;
        }
        return coordinates[newY][newX]; // Use newY and newX in this order
    }

    /**
     * Fills the coordinates array with Point2D objects.
     * 
     * @param numRows The number of rows.
     * @param numCols The number of columns.
     */
    private void fillCoordinates(int numRows, int numCols) {
        for (int y = 0; y < numRows; y++) { // Loop over rows (y)
            for (int x = 0; x < numCols; x++) { // Loop over columns (x)
                coordinates[y][x] = new Point2D(x, y);
            }
        }
    }
}
