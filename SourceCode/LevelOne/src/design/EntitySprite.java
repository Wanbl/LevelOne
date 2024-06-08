package design;

import entity.Direction;

/**
 * Enum representing different entity sprites for various directions.
 */
public enum EntitySprite {
    // Living Entities
    PLAYER("data/templates/sprites/player/up.png", "data/templates/sprites/player/down.png", "data/templates/sprites/player/left.png", "data/templates/sprites/player/right.png"),
    ZOMBIE("data/templates/sprites/zombie/up.png", "data/templates/sprites/zombie/down.png", "data/templates/sprites/zombie/left.png", "data/templates/sprites/zombie/right.png"),
    BADGUY("data/templates/sprites/badguy/up.png", "data/templates/sprites/badguy/down.png", "data/templates/sprites/badguy/left.png", "data/templates/sprites/badguy/right.png"),
    FIRSTNPC("data/templates/sprites/firstnpc/up.png", "data/templates/sprites/firstnpc/down.png", "data/templates/sprites/firstnpc/left.png", "data/templates/sprites/firstnpc/right.png"),
    ENDNPC("data/templates/sprites/endnpc/up.png", "data/templates/sprites/endnpc/down.png", "data/templates/sprites/endnpc/left.png", "data/templates/sprites/endnpc/right.png"),
    THING("data/templates/sprites/thing/up.png", "data/templates/sprites/thing/down.png", "data/templates/sprites/thing/left.png", "data/templates/sprites/thing/right.png"),
    
    // Items
    APPLE("data/templates/items/apple.png", "data/templates/items/apple.png", "data/templates/items/apple.png", "data/templates/items/apple.png"),
    KNIFE("data/templates/items/knife.png", "data/templates/items/knife.png", "data/templates/items/knife.png", "data/templates/items/knife.png"),
    ITEM1("data/templates/items/item1.png", "data/templates/items/item1.png", "data/templates/items/item1.png", "data/templates/items/item1.png"),
    ITEM2("data/templates/items/item2.png", "data/templates/items/item2.png", "data/templates/items/item2.png", "data/templates/items/item2.png"),
    FORESTKEY("data/templates/items/forestkey.png", "data/templates/items/forestkey.png", "data/templates/items/forestkey.png", "data/templates/items/forestkey.png"),
    
    // Decor
    TREE("data/templates/decor/tree.png", "data/templates/decor/tree.png", "data/templates/decor/tree.png", "data/templates/decor/tree.png"),
    ROCK("data/templates/decor/rock.png", "data/templates/decor/rock.png", "data/templates/decor/rock.png", "data/templates/decor/rock.png"),
    FORESTDOOR("data/templates/decor/forestdoor.png", "data/templates/decor/forestdoor.png", "data/templates/decor/forestdoor.png", "data/templates/decor/forestdoor.png"),
    DESERTDOOR("data/templates/decor/desertdoor.png", "data/templates/decor/desertdoor.png", "data/templates/decor/desertdoor.png", "data/templates/decor/desertdoor.png"),
    
    // Default
    DEFAULT("data/templates/default.png", "data/templates/default.png", "data/templates/default.png", "data/templates/default.png");

    private String upSpritePath;
    private String downSpritePath;
    private String leftSpritePath;
    private String rightSpritePath;

    /**
     * Constructor to initialize sprite paths for different directions.
     * 
     * @param upSpritePath Path to the sprite image for the UP direction.
     * @param downSpritePath Path to the sprite image for the DOWN direction.
     * @param leftSpritePath Path to the sprite image for the LEFT direction.
     * @param rightSpritePath Path to the sprite image for the RIGHT direction.
     */
    private EntitySprite(String upSpritePath, String downSpritePath, String leftSpritePath, String rightSpritePath) {
        this.upSpritePath = upSpritePath;
        this.downSpritePath = downSpritePath;
        this.leftSpritePath = leftSpritePath;
        this.rightSpritePath = rightSpritePath;
    }

    /**
     * Returns the sprite path for a given direction.
     * 
     * @param direction The direction for which to get the sprite path.
     * @return The sprite path corresponding to the direction.
     */
    public String getSpritePath(Direction direction) {
        switch (direction) {
            case UP:
                return upSpritePath;
            case DOWN:
                return downSpritePath;
            case LEFT:
                return leftSpritePath;
            case RIGHT:
                return rightSpritePath;
            default:
                return downSpritePath;
        }
    }
    
    /**
     * Returns the default sprite path.
     * 
     * @return The default sprite path.
     */
    public String getSpritePath() {
        return getSpritePath(null);
    }
}
