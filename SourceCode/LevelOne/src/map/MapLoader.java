package map;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * The MapLoader class is responsible for loading a map from a file.
 * It stores the map as a 2D array of strings and provides methods to access and modify the map.
 */
public class MapLoader {
    private String[][] map;

    /**
     * Constructs a MapLoader with the specified file path, number of rows, and number of columns.
     * Loads the map from the specified file.
     * 
     * @param filePath The path to the map file.
     * @param numRows  The number of rows in the map.
     * @param numCols  The number of columns in the map.
     */
    public MapLoader(String filePath, int numRows, int numCols) {
        this.map = new String[numRows][numCols];
        System.out.println("MapLoader : MapLoader() : MapLoader created");
        System.out.println("MapLoader : MapLoader() : numCols: " + numCols);
        System.out.println("MapLoader : MapLoader() : numRows: " + numRows);
        try {
            System.out.println("MapLoader : MapLoader() : Loading map from " + filePath);
            loadMap(filePath);
        } catch (Exception e) {
            System.out.println("MapLoader : MapLoader() : Error loading map");
            e.printStackTrace();
        }
    }

    /**
     * Loads the map from the specified file.
     * 
     * @param filePath The path to the map file.
     * @throws Exception if an error occurs while reading the file.
     */
    private void loadMap(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            System.out.println("MapLoader : loadMap() : Reading line " + i + ": " + line);
            String[] split = line.split(",");
            for (int j = 0; j < split.length; j++) {
                map[i][j] = split[j];
                System.out.println("MapLoader : loadMap() : Setting map[" + i + "][" + j + "] to " + split[j]);
            }
            i++;
        }
        br.close();
    }

    /**
     * Returns the map as a 2D array of strings.
     * 
     * @return The map.
     */
    public String[][] getMap() {
        return map;
    }

    /**
     * Sets the map with the specified 2D array of strings.
     * 
     * @param map The map to set.
     */
    public void setMap(String[][] map) {
        this.map = map;
    }

    /**
     * Returns the tile value at the specified coordinates.
     * 
     * @param x The x-coordinate (row).
     * @param y The y-coordinate (column).
     * @return The tile value.
     */
    public String getTile(int x, int y) {
        return map[x][y];
    }

    /**
     * Sets the tile value at the specified coordinates.
     * 
     * @param x     The x-coordinate (row).
     * @param y     The y-coordinate (column).
     * @param value The tile value to set.
     */
    public void setTile(int x, int y, String value) {
        map[x][y] = value;
    }
}
