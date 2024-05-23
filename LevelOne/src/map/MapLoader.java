package map;
import java.io.BufferedReader;
import java.io.FileReader;

public class MapLoader {
	private String[][] map;
	
	public MapLoader(String filePath, int numCols, int numRows) {
		this.map = new String[numCols][numRows];
		System.out.println("MapLoader created");
		System.out.println("numCols: " + numCols);
		System.out.println("numRows: " + numRows);
		try {
			System.out.println("Loading map from " + filePath);
			loadMap(filePath);
		} catch (Exception e) {
			System.out.println("Error loading map");
			e.printStackTrace();
		}
	}
	
	private void loadMap(String filePath) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		String line;
		int i = 0;
		while ((line = br.readLine()) != null) {
			System.out.println("Reading line " + i + ": " + line);
			String[] split = line.split(",");
			for (int j = 0; j < split.length; j++) {
				map[i][j] = split[j];
				System.out.println("Setting map[" + i + "][" + j + "] to " + split[j]);
			}
			i++;
		}
		br.close();
	}
	
	public String[][] getMap() {
		return map;
	}
	
	public void setMap(String[][] map) {
		this.map = map;
	}
	
	public String getTile(int x, int y) {
		return map[x][y];
	}
	
	public void setTile(int x, int y, String value) {
		map[x][y] = value;
	}

}
