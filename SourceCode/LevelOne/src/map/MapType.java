package map;

/**
 * The MapType enum defines different types of maps in the game.
 * Each enum constant corresponds to a specific map with its file paths and dimensions.
 */
public enum MapType {
    DESERT("data/maps/tiles/desert.txt", "data/maps/entities/desert.txt", 10, 10, 64),
    FOREST("data/maps/tiles/forest.txt", "data/maps/entities/forest.txt", 10, 10, 64),
    FIRSTHOUSE("data/maps/tiles/firsthouse.txt", "data/maps/entities/firsthouse.txt", 10, 10, 64),
    GAMEOVER("data/maps/tiles/gameover.txt", "data/maps/entities/gameover.txt", 10, 10, 64),
    WIN("data/maps/tiles/win.txt", "data/maps/entities/win.txt", 10, 10, 64);

    private String filePath;
    private String entitiesFilePath;
    private int numRows;
    private int numCols;
    private double cellSize;

    /**
     * Constructs a MapType with the specified file paths, number of rows, number of columns, and cell size.
     * 
     * @param filePath         The path to the map file.
     * @param entitiesFilePath The path to the entities file.
     * @param numRows          The number of rows in the map.
     * @param numCols          The number of columns in the map.
     * @param cellSize         The size of each cell in the map.
     */
    private MapType(String filePath, String entitiesFilePath, int numRows, int numCols, double cellSize) {
        this.filePath = filePath;
        this.numRows = numRows;
        this.numCols = numCols;
        this.cellSize = cellSize;
        this.entitiesFilePath = entitiesFilePath;
    }

    /**
     * Returns the file path of the map.
     * 
     * @return The file path of the map.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Returns the file path of the entities.
     * 
     * @return The file path of the entities.
     */
    public String getEntitiesFilePath() {
        return entitiesFilePath;
    }

    /**
     * Returns the number of rows in the map.
     * 
     * @return The number of rows in the map.
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in the map.
     * 
     * @return The number of columns in the map.
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Returns the size of each cell in the map.
     * 
     * @return The size of each cell in the map.
     */
    public double getCellSize() {
        return cellSize;
    }
}
