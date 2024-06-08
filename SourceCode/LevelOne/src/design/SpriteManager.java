package design;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import entity.Direction;

/**
 * The SpriteManager class is responsible for managing and updating the sprite images for entities.
 */
public class SpriteManager {
    private ImageView sprite;
    private EntitySprite entitySprite;

    /**
     * Constructs a SpriteManager with the specified EntitySprite.
     * 
     * @param entitySprite The EntitySprite containing the paths for the sprite images.
     */
    public SpriteManager(EntitySprite entitySprite) {
        this.entitySprite = entitySprite;
        this.sprite = new ImageView(new Image("file:" + entitySprite.getSpritePath(Direction.DOWN)));
        System.out.println("SPRITEMANAGER : CONSTRUCTOR() : Creating sprite");
    }

    /**
     * Constructs a SpriteManager with the specified sprite path.
     * 
     * @param spritePath The path to the sprite image.
     */
    public SpriteManager(String spritePath) {
        this.sprite = new ImageView(new Image("file:" + spritePath));
        System.out.println("SPRITEMANAGER : CONSTRUCTOR() : Creating sprite");
    }

    /**
     * Returns the ImageView containing the sprite image.
     * 
     * @return The ImageView with the sprite image.
     */
    public ImageView getSprite() {
        System.out.println("SPRITEMANAGER : GETSPRITE() : Getting sprite");
        return sprite;
    }

    /**
     * Updates the sprite image based on the specified direction.
     * 
     * @param direction The direction for which to update the sprite image.
     */
    public void updateSprite(Direction direction) {
        String spritePath = entitySprite.getSpritePath(direction);
        sprite.setImage(new Image("file:" + spritePath));
        System.out.println("SPRITEMANAGER : UPDATESPRITE() : Setting sprite to " + spritePath);
    }
}
