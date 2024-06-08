package manager;

import java.util.Map;
import java.util.HashMap;
import javafx.stage.Stage;
import javafx.scene.Scene;
import map.MapType;
import coordinates.Point2D;
import entity.Player;
import design.EntitySprite;
import design.SpriteManager;
import controls.PlayerController;
import coordinates.CoordinatesLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import design.DisplayInventory;
import items.ItemTemplate;
import javafx.scene.input.ScrollEvent;
import chat.GlobalChat;
import design.DisplayChat;
import entity.Entity;
import entity.EntityLoader;

/**
 * The SceneManager class is responsible for managing different scenes in the game.
 * It handles scene creation, switching, and maintains player position across scenes.
 */
public class SceneManager {
    private Stage primaryStage;
    private Map<MapType, Scene> scenes;
    private Map<MapType, GridManager> gridManagers;
    private Map<MapType, EntityManager> entityManagers;
    private Map<MapType, CoordinatesLoader> availableLocations;
    private RandomMovementManager randomMovementManager;
    private Player player;
    private DisplayInventory displayInventory;
    private MapType currentMapType;
    private Map<MapType, Point2D> playerPositions;

    /**
     * Constructs a SceneManager with the specified primary stage.
     * 
     * @param primaryStage The primary stage for the application.
     */
    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.scenes = new HashMap<>();
        this.gridManagers = new HashMap<>();
        this.entityManagers = new HashMap<>();
        this.availableLocations = new HashMap<>();
        this.currentMapType = null;
        this.playerPositions = new HashMap<>();
        
        initPlayer();
    }

    /**
     * Initializes the player with default settings and inventory items.
     */
    private void initPlayer() {
        SpriteManager playerSpriteManager = new SpriteManager(EntitySprite.PLAYER);
        this.player = new Player(new Point2D(0, 0), "player", null, playerSpriteManager);
        player.getInventory().addItem(ItemTemplate.SWORD, true);
        for (int i = 0; i < 2; i++) {
            player.getInventory().addItem(ItemTemplate.APPLE);
        }
    }

    /**
     * Creates and returns a BorderPane with chat and inventory panels.
     * 
     * @return A configured BorderPane.
     */
    private BorderPane createBorderPane() {
        BorderPane borderPane = new BorderPane();
        
        DisplayChat displayChat = new DisplayChat();
        GlobalChat.addObserver(displayChat);
        ScrollPane chatScrollPane = displayChat.getChatScrollPane();
        borderPane.setBottom(chatScrollPane);

        this.displayInventory = new DisplayInventory(player.getPlayerInventory());
        ScrollPane inventoryScrollPane = new ScrollPane(displayInventory.getInventoryGrid());
        borderPane.setRight(inventoryScrollPane);

        player.getInventory().addObserver(displayInventory);
        
        return borderPane;
    }

    /**
     * Adds a scene to the manager along with its associated grid manager, entity manager, and coordinates loader.
     * 
     * @param mapType           The type of the map.
     * @param scene             The scene to be added.
     * @param gridManager       The grid manager for the scene.
     * @param entityManager     The entity manager for the scene.
     * @param coordinatesLoader The coordinates loader for the scene.
     */
    public void addScene(MapType mapType, Scene scene, GridManager gridManager, EntityManager entityManager, CoordinatesLoader coordinatesLoader) {
        scenes.put(mapType, scene);
        gridManagers.put(mapType, gridManager);
        entityManagers.put(mapType, entityManager);
        availableLocations.put(mapType, coordinatesLoader);

        randomMovementManager = new RandomMovementManager(entityManager);
        randomMovementManager.startRandomMovement();
    }

    /**
     * Creates and returns a new CoordinatesLoader for the specified map type.
     * 
     * @param mapType The type of the map.
     * @return A new CoordinatesLoader.
     */
    public CoordinatesLoader createCoordinatesLoader(MapType mapType) {
        return new CoordinatesLoader(mapType.getNumRows(), mapType.getNumCols());
    }

    /**
     * Shows the scene for the specified map type.
     * 
     * @param mapType The type of the map.
     */
    public void showScene(MapType mapType) {
        if (scenes.containsKey(mapType)) {
            Scene scene = scenes.get(mapType);

            // Update the GridPane and ScrollPane in the center and right of the BorderPane
            GridManager gridManager = gridManagers.get(mapType);
            if (gridManager != null) {
                GridPane gridPane = gridManager.getGrid();
                BorderPane borderPane = (BorderPane) scene.getRoot();
                borderPane.setCenter(gridPane);
                updateInventoryScrollPane(borderPane);
            }

            primaryStage.setScene(scene);
            primaryStage.show();
            System.out.println("SceneManager : showScene() : Scene changed to " + mapType);
        } else {
            System.out.println("SceneManager : showScene() : Scene does not exist");
        }
    }

    /**
     * Updates the inventory scroll pane in the specified border pane.
     * 
     * @param borderPane The BorderPane to update.
     */
    private void updateInventoryScrollPane(BorderPane borderPane) {
        ScrollPane inventoryScrollPane = new ScrollPane(displayInventory.getInventoryGrid());
        borderPane.setRight(inventoryScrollPane);
    }

    /**
     * Changes the current scene to the specified map type, saving the player's position if necessary.
     * 
     * @param mapType The type of the map to switch to.
     */
    public void changeScene(MapType mapType) {
        if (currentMapType != null) {
            // Save the player's current position
            playerPositions.put(currentMapType, player.getLocation());
        }

        if (currentMapType != mapType) {
            if (scenes.containsKey(mapType)) {
                updateExistingScene(mapType);
            } else {
                createAndShowNewScene(mapType);
            }
            currentMapType = mapType;
        }
    }

    /**
     * Updates an existing scene with the player's position and entity manager.
     * 
     * @param mapType The type of the map to update.
     */
    private void updateExistingScene(MapType mapType) {
        System.out.println("SceneManager : updateExistingScene() : Updating existing scene");

        EntityManager entityManager = entityManagers.get(mapType);
        CoordinatesLoader coordinatesLoader = availableLocations.get(mapType);

        // Remove the old player instance from the current EntityManager
        entityManager.removeEntity(player);

        // Restore the player's position for the new scene
        Point2D newLocation = playerPositions.getOrDefault(mapType, coordinatesLoader.getCoordinates(0, 0));
        player.setLocation(newLocation);

        // Add the player to the new EntityManager
        player.setEntityManager(entityManager);
        entityManager.addEntity(player);

        showScene(mapType);
        System.out.println("SceneManager : updateExistingScene() : Scene already exists");
    }

    /**
     * Creates and shows a new scene for the specified map type.
     * 
     * @param mapType The type of the map to create.
     */
    private void createAndShowNewScene(MapType mapType) {
        System.out.println("SceneManager : createAndShowNewScene() : Creating new scene");
        CoordinatesLoader coordinatesLoader = createCoordinatesLoader(mapType);
        EntityManager entityManager = new EntityManager(mapType.getNumCols(), mapType.getNumRows(), coordinatesLoader, this);

        // Restore the player's position or set to default if not saved
        Point2D playerLocation = playerPositions.getOrDefault(mapType, coordinatesLoader.getCoordinates(0, 0));
        player.setLocation(playerLocation);
        player.setEntityManager(entityManager);
        entityManager.addEntity(player);

        EntityLoader entityLoader = new EntityLoader();
        entityLoader.loadEntities(mapType.getEntitiesFilePath(), coordinatesLoader, entityManager);

        GridManager gridManager = new GridManager(mapType.getNumRows(), mapType.getNumCols(), mapType.getCellSize(), mapType.getFilePath(), entityManager, coordinatesLoader);
        entityManager.addObserver(gridManager);

        GridPane gridPane = gridManager.getGrid();
        
        BorderPane borderPane = createBorderPane();
        borderPane.setCenter(gridPane);

        double sceneWidth = 1920;
        double sceneHeight = 1080;

        Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);

        scene.addEventFilter(ScrollEvent.SCROLL, event -> {
            if (event.getDeltaY() > 0) {
                displayInventory.selectPreviousItem();
            } else if (event.getDeltaY() < 0) {
                displayInventory.selectNextItem();
            }
            event.consume();
        });

        System.out.println("SceneManager : createAndShowNewScene() : EntityManager: " + entityManager);

        PlayerController playerController = new PlayerController(player, player.getSpriteManager());
        scene.setOnKeyPressed(playerController.getMovementHandler());

        scene.getStylesheets().add("style.css");

        addScene(mapType, scene, gridManager, entityManager, coordinatesLoader);
        showScene(mapType);
    }
}
