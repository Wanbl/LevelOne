package design;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import coordinates.Point2D;
import entity.Entity;
import manager.EntityManager;

public class TileDesignManager {
	public EntityManager entityManager;
	
	public TileDesignManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	public StackPane createTile(String tileType, Point2D location) {
		System.out.println("");
		System.out.println("Creating tile at " + location);
		StackPane tile = new StackPane();
		ImageView tileImage = new ImageView();
		try {
			tileImage.setImage(new Image("file:data/templates/tiles/" + tileType + ".png"));
			System.out.println("Setting tile image to " + tileImage.getImage().getUrl());
		} catch (IllegalArgumentException e) {
			System.out.println("Tile does not exist");
			tileImage.setImage(new Image("file:data/templates/tiles/empty.png"));
		}
		tile.getChildren().add(tileImage);
		
		Entity entity = entityManager.getEntity(location);
		System.out.println("Checking for entity at " + location);
		System.out.println("EntityManager for the tile: " + entityManager);
		if (entity != null) {
//			SpriteManager spriteManager = new SpriteManager(EntitySprite.valueOf(entity.getType().toUpperCase()));
			tile.getChildren().add(entity.getSpriteManager().getSprite());
			System.out.println("Added entity sprite to tile : " + entity.getType() + " at " + location);
		}
		else {
			System.out.println("No entity to display at " + location);
		}
		
		return tile;
	}
	
}
