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
import entity.Monster;
import entity.NPC;

public class SceneManager {
	private Stage primaryStage;
	private Map<MapType, Scene> scenes;
	private Map<MapType, GridManager> gridManagers;	// GridManager manages the design of the grid
	private Map<MapType, EntityManager> entityManagers;	// EntityManager manages the entities in the grid and the interactions
	private Map<MapType, CoordinatesLoader> availableLocations; // CoordinatesLoader creates a table of coordinates
	private RandomMovementManager randomMovementManager; // RandomMovementManager manages the movement of other entities
	
	public SceneManager(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.scenes = new HashMap<>();
		this.gridManagers = new HashMap<>();
		this.entityManagers = new HashMap<>();
		this.availableLocations = new HashMap();
	}
	
	public void addScene(MapType mapType, Scene scene, GridManager gridManager, EntityManager entityManager, CoordinatesLoader coordinatesLoader) {
		scenes.put(mapType, scene);
		gridManagers.put(mapType, gridManager);
		entityManagers.put(mapType, entityManager);
		availableLocations.put(mapType, coordinatesLoader);
		
		randomMovementManager = new RandomMovementManager(entityManager);
		randomMovementManager.startRandomMovement();
	}
	
	public CoordinatesLoader createCoordinatesLoader(MapType mapType) {
		return new CoordinatesLoader(mapType.getNumCols(), mapType.getNumRows());
	}
	
	public void showScene(MapType mapType) {
		Scene scene = scenes.get(mapType);
		if (scene != null) {
			primaryStage.setScene(scene);
			System.out.println("Scene changed to " + mapType);
		}
		else {
			System.out.println("Scene does not exist");
		}
	}

	public void changeScene(MapType mapType) { 
		if (scenes.containsKey(mapType)) {
			showScene(mapType);
			System.out.println("Scene already exists");
		} else {
			System.out.println("Creating new scene");
			CoordinatesLoader coordinatesLoader = createCoordinatesLoader(mapType);
			EntityManager entityManager = new EntityManager(mapType.getNumCols(), mapType.getNumRows(), coordinatesLoader);
			
//			PLAYER
			Point2D playerLocation = coordinatesLoader.getCoordinates(0, 0);
			SpriteManager playerSpriteManager = new SpriteManager(EntitySprite.PLAYER);
            Player player = new Player(playerLocation, "player", entityManager, playerSpriteManager);
            entityManager.addEntity(player);
			if (entityManager.getEntity(playerLocation) != null) {
				System.out.println("Player added to entity manager by SceneManager");
			}
			
//			BADGUY
			Point2D badGuyLocation = coordinatesLoader.getCoordinates(1, 1);
			SpriteManager badGuySpriteManager = new SpriteManager(EntitySprite.BADGUY);
			Monster badGuy = new Monster(badGuyLocation, "badGuy", entityManager, badGuySpriteManager);
			entityManager.addEntity(badGuy);
            
//			BADGUY2
			Point2D badGuy2Location = coordinatesLoader.getCoordinates(4, 4);
			SpriteManager badGuy2SpriteManager = new SpriteManager(EntitySprite.BADGUY);
			Monster badGuy2 = new Monster(badGuy2Location, "badGuy2", entityManager, badGuy2SpriteManager);
			entityManager.addEntity(badGuy2);
			
//			BADGUY3
			Point2D badGuy3Location = coordinatesLoader.getCoordinates(6, 6);
			SpriteManager badGuy3SpriteManager = new SpriteManager(EntitySprite.BADGUY);
			Monster badGuy3 = new Monster(badGuy3Location, "badGuy3", entityManager, badGuy3SpriteManager);
			entityManager.addEntity(badGuy3);
			
//			FIRSTNPC
			Point2D firstNpcLocation = coordinatesLoader.getCoordinates(2, 2);
			SpriteManager firstNpcSpriteManager = new SpriteManager(EntitySprite.FIRSTNPC);
			NPC firstNpc = new NPC(firstNpcLocation, "firstNpc", entityManager, firstNpcSpriteManager);
			entityManager.addEntity(firstNpc);
			
            
			
			GridManager gridManager = new GridManager(mapType.getNumCols(), mapType.getNumRows(), mapType.getCellSize(), mapType.getFilePath(), entityManager, coordinatesLoader);
            
			entityManager.addObserver(gridManager);
			
			Scene scene = new Scene(gridManager.getGrid(), mapType.getNumCols() * mapType.getCellSize(), mapType.getNumRows() * mapType.getCellSize());
            
            System.out.println("EntityManager: " + entityManager);
            
//          PLAYER CONTROLS
            PlayerController playerController = new PlayerController(player, playerSpriteManager);
            scene.setOnKeyPressed(playerController.getMovementHandler());
            
            
            
            addScene(mapType, scene, gridManager, entityManager, coordinatesLoader);
            showScene(mapType);
		}
	}
	
}