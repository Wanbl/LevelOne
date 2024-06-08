package entity;

import coordinates.Point2D;
import design.SpriteManager;

/**
 * The NonLivingEntity class represents a non-living entity in the game.
 * It extends the Entity class and inherits its properties.
 */
public class NonLivingEntity extends Entity {

    /**
     * Constructs a NonLivingEntity with the specified location, weight, type, and sprite manager.
     * 
     * @param location      The location of the non-living entity.
     * @param weight        The weight of the non-living entity.
     * @param type          The type of the non-living entity.
     * @param spriteManager The sprite manager for the non-living entity.
     */
    public NonLivingEntity(Point2D location, double weight, String type, SpriteManager spriteManager) {
        super(location, weight, type, spriteManager);
    }
}
