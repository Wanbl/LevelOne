package manager;
import map.MapLoader;
import design.TileDesignManager;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import coordinates.Point2D;
import coordinates.CoordinatesLoader;
import observer.Observer;
import entity.LivingEntity;
import java.util.List;

public class GridManager implements Observer {
	private int numCols;
	private int numRows;
	private double cellSize;
	private MapLoader mapLoader;
	private TileDesignManager tileDesignManager;
	private EntityManager entityManager;
	private CoordinatesLoader coordinatesLoader;
	private GridPane grid;
	private StackPane[][] cellNodes;
	
	
	public GridManager(int numCols, int numRows, double cellSize, String mapFilePath, EntityManager entityManager, CoordinatesLoader coordinatesLoader) {
		this.numCols = numCols;
		this.numRows = numRows;
		this.cellSize = cellSize;
		this.mapLoader = new MapLoader(mapFilePath, numCols, numRows);
		this.entityManager = entityManager;
		this.tileDesignManager = new TileDesignManager(entityManager);
		this.coordinatesLoader = coordinatesLoader;
		this.grid = new GridPane();
		this.cellNodes = new StackPane[numCols][numRows];
		initializeGrid();
	}
	
	public void initializeGrid() {
		for (int i = 0; i < numCols; i++) {
			grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
		}
		
		for (int i = 0; i < numRows; i++) {
			grid.getRowConstraints().add(new RowConstraints(cellSize));
		}
		
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				String tileType = mapLoader.getTile(i, j);
				Point2D location = coordinatesLoader.getCoordinates(i, j);
				StackPane tile = tileDesignManager.createTile(tileType, location);
				grid.add(tile, i, j);
				cellNodes[i][j] = tile;
				System.out.println("Tile added at " + i + ", " + j);
			}
		}
	}
	
	private void updateGrid() {
		System.out.println("UPDATEGRID() : Updating grid");
		List<LivingEntity> movedEntities = entityManager.getMovedEntities();
		System.out.println("UPDATEGRID() : Number of moved entities : " + movedEntities.size());
		for (LivingEntity entity : movedEntities) {
			Point2D oldLocation = entity.getOldLocation();
			Point2D newLocation = entity.getLocation();
			System.out.println("UPDATEGRID() : Entity moved from " + oldLocation + " to " + newLocation);
			
			if (oldLocation != null) {
				int oldX = (int) oldLocation.getX();
				int oldY = (int) oldLocation.getY();
				String oldTileType = mapLoader.getTile(oldX, oldY);
				StackPane oldTile = tileDesignManager.createTile(oldTileType, oldLocation);
				grid.add(oldTile, oldX, oldY);
			}
			
			int newX = (int) newLocation.getX();
			int newY = (int) newLocation.getY();
			String newTileType = mapLoader.getTile(newX, newY);
			StackPane newTile = tileDesignManager.createTile(newTileType, newLocation);
			grid.getChildren().remove(cellNodes[newX][newY]);
			grid.add(newTile, newX, newY);
			cellNodes[newX][newY] = newTile;
		}
	}
	
	public GridPane getGrid() {
		return grid;
	}
	
	@Override
	public void update() {
		updateGrid();
		System.out.println("GridManager notified");
	}
	
}
