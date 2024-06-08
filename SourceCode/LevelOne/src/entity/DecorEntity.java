package entity;

import coordinates.Point2D;
import design.SpriteManager;

/**
 * The DecorEntity class represents a non-living decorative entity in the game.
 */
public class DecorEntity extends NonLivingEntity {
    private String type;
    private DecorType decorType;

    /**
     * Constructs a DecorEntity with the specified location, weight, type, sprite manager, and decor type.
     * 
     * @param location      The location of the decorative entity.
     * @param weight        The weight of the decorative entity.
     * @param type          The type of the decorative entity.
     * @param spriteManager The sprite manager for the decorative entity.
     * @param decorType     The decor type of the decorative entity.
     */
    public DecorEntity(Point2D location, double weight, String type, SpriteManager spriteManager, DecorType decorType) {
        super(location, weight, type, spriteManager);
        this.type = type;
        this.decorType = decorType;
    }

    /**
     * Gets the type of the decorative entity.
     * 
     * @return The type of the decorative entity.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the decor type of the decorative entity.
     * 
     * @return The decor type of the decorative entity.
     */
    public DecorType getDecorType() {
        return decorType;
    }
}
