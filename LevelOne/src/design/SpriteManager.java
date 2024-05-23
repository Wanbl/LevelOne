package design;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import entity.Direction;

public class SpriteManager {
	private ImageView sprite;
	private EntitySprite entitySprite;
	
	public SpriteManager(EntitySprite entitySprite) {
		this.entitySprite = entitySprite;
		this.sprite = new ImageView(new Image("file:" + entitySprite.getSpritePath(Direction.DOWN)));
		System.out.println("Creating sprite");
	}
	
	public ImageView getSprite() {
		System.out.println("Getting sprite");
		return sprite;
	}
	
	public void updateSprite(Direction direction) {
		String spritePath = entitySprite.getSpritePath(direction);
		sprite.setImage(new Image("file:" + spritePath));
		System.out.println("Setting sprite to " + spritePath);
	}
}
