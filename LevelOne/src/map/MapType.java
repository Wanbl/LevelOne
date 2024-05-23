package map;

public enum MapType {
	DESERT("data/maps/tiles/desert.txt", 10, 10, 64),
	FOREST("data/maps/tiles/forest.txt", 10, 10, 64),
	FIRSTHOUSE("data/maps/tiles/desert.txt", 10, 10, 64);
	
	private String filePath;
	private int numCols;
	private int numRows;
	private double cellSize;
	
	private MapType(String filePath, int numCols, int numRows, double cellSize) {
		this.filePath = filePath;
		this.numCols = numCols;
		this.numRows = numRows;
		this.cellSize = cellSize;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public int getNumCols() {
		return numCols;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public double getCellSize() {
		return cellSize;
	}
}
