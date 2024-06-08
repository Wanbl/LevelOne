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

/**
 * The GridManager class is responsible for managing the grid layout and updating the visual representation
 * of the game world. It observes changes in the EntityManager and updates the grid accordingly.
 */
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

    /**
     * Constructs a GridManager with the specified parameters.
     * 
     * @param numRows           The number of rows in the grid.
     * @param numCols           The number of columns in the grid.
     * @param cellSize          The size of each cell in the grid.
     * @param mapFilePath       The file path of the map.
     * @param entityManager     The entity manager for managing entities.
     * @param coordinatesLoader The coordinates loader for translating locations.
     */
    public GridManager(int numRows, int numCols, double cellSize, String mapFilePath, EntityManager entityManager, CoordinatesLoader coordinatesLoader) {
        this.numCols = numCols;
        this.numRows = numRows;
        this.cellSize = cellSize;
        this.mapLoader = new MapLoader(mapFilePath, numRows, numCols);
        this.entityManager = entityManager;
        this.tileDesignManager = new TileDesignManager(entityManager);
        this.coordinatesLoader = coordinatesLoader;
        this.grid = new GridPane();
        this.cellNodes = new StackPane[numRows][numCols];
        initializeGrid();
    }

    /**
     * Initializes the grid by setting column and row constraints and populating it with tiles.
     */
    public void initializeGrid() {
        for (int i = 0; i < numCols; i++) {
            grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        }

        for (int i = 0; i < numRows; i++) {
            grid.getRowConstraints().add(new RowConstraints(cellSize));
        }

        for (int y = 0; y < numRows; y++) {
            for (int x = 0; x < numCols; x++) {
                String tileType = mapLoader.getTile(y, x); // Use y for rows and x for columns
                Point2D location = coordinatesLoader.getCoordinates(x, y);
                StackPane tile = tileDesignManager.createTile(tileType, location);
                grid.add(tile, x, y);
                cellNodes[y][x] = tile;
                System.out.println("GridManager : initializeGrid() : Tile added at " + y + ", " + x);
            }
        }
    }

    /**
     * Updates the grid based on the moved entities, refreshing their positions.
     */
    private void updateGrid() {
        System.out.println("GridManager : updateGrid() : Updating grid");
        List<LivingEntity> movedEntities = entityManager.getMovedEntities();
        System.out.println("GridManager : updateGrid() : Number of moved entities : " + movedEntities.size());
        for (LivingEntity entity : movedEntities) {
            Point2D oldLocation = entity.getOldLocation();
            Point2D newLocation = entity.getLocation();
            System.out.println("GridManager : updateGrid() : Entity moved from " + oldLocation + " to " + newLocation);

            if (oldLocation != null) {
                int oldX = (int) oldLocation.getX();
                int oldY = (int) oldLocation.getY();
                String oldTileType = mapLoader.getTile(oldY, oldX); // Use oldY and oldX in this order
                StackPane oldTile = tileDesignManager.createTile(oldTileType, oldLocation);
                grid.add(oldTile, oldX, oldY);
            }

            int newX = (int) newLocation.getX();
            int newY = (int) newLocation.getY();
            String newTileType = mapLoader.getTile(newY, newX); // Use newY and newX in this order
            StackPane newTile = tileDesignManager.createTile(newTileType, newLocation);
            grid.getChildren().remove(cellNodes[newY][newX]);
            grid.add(newTile, newX, newY);
            cellNodes[newY][newX] = newTile;
        }
    }

    /**
     * Gets the grid pane managed by this GridManager.
     * 
     * @return The grid pane.
     */
    public GridPane getGrid() {
        return grid;
    }

    /**
     * Updates the grid when notified by the observed EntityManager.
     */
    @Override
    public void update() {
        updateGrid();
        System.out.println("GridManager : update() : GridManager notified");
    }
}
