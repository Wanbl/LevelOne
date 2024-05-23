package coordinates;

public class CoordinatesLoader {
	private Point2D[][] coordinates;
	
	public CoordinatesLoader(int numCols, int numRows) {
		this.coordinates = new Point2D[numCols][numRows];
		this.fillCoordinates(numCols, numRows);
	}
	
	public Point2D getCoordinates(int x, int y) {
		return coordinates[x][y];
	}
	
	public Point2D translateCoordinates(Point2D point, int dx, int dy) {
		int x = (int) point.getX();
		int y = (int) point.getY();
		int newX = x + dx;
		int newY = y + dy;
		if (newX < 0 || newX >= coordinates.length || newY < 0 || newY >= coordinates[0].length) {
			return null;
		}
		return coordinates[newX][newY];
	}
	
	public void fillCoordinates(int numCols, int numRows) {
		for (int i = 0; i < numCols; i++) {
            for (int j = 0; j < numRows; j++) {
                coordinates[i][j] = new Point2D(i, j);
            }
        }
	}
}
