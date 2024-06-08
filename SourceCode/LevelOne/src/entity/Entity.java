package entity;

import coordinates.Point2D;
import design.SpriteManager;

/**
 * The Entity class represents a generic entity in the game with a location, weight, direction, type, and sprite manager.
 */
public class Entity {
    private Point2D location;
    private double weight;
    private Direction direction;
    private String type;
    private SpriteManager spriteManager;

    /**
     * Constructs an Entity with the specified location, weight, type, and sprite manager.
     * 
     * @param location      The location of the entity.
     * @param weight        The weight of the entity.
     * @param type          The type of the entity.
     * @param spriteManager The sprite manager for the entity.
     */
    public Entity(Point2D location, double weight, String type, SpriteManager spriteManager) {
        this.setLocation(location);
        this.setWeight(weight);
        this.type = type;
        this.spriteManager = spriteManager;
    }

    /**
     * Gets the location of the entity.
     * 
     * @return The location of the entity.
     */
    public Point2D getLocation() {
        return location;
    }

    /**
     * Sets the location of the entity.
     * 
     * @param location The new location of the entity.
     */
    public void setLocation(Point2D location) {
        this.location = location;
    }

    /**
     * Gets the weight of the entity.
     * 
     * @return The weight of the entity.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the entity.
     * 
     * @param weight The new weight of the entity.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets the direction of the entity.
     * 
     * @return The direction of the entity.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the entity.
     * 
     * @param direction The new direction of the entity.
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Moves the entity by the specified amounts in the x and y directions.
     * 
     * @param x The amount to move in the x direction.
     * @param y The amount to move in the y direction.
     */
    public void move(int x, int y) {
        this.location.translateX(x);
        this.location.translateY(y);
    }

    /**
     * Gets the type of the entity.
     * 
     * @return The type of the entity.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the sprite manager for the entity.
     * 
     * @return The sprite manager for the entity.
     */
    public SpriteManager getSpriteManager() {
        return spriteManager;
    }
}
