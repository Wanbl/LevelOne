package entity;

import items.Item;
import design.EntitySprite;
import coordinates.Point2D;
import design.SpriteManager;
import items.ItemTemplate;

/**
 * The ItemEntity class represents a non-living entity that contains an item.
 */
public class ItemEntity extends NonLivingEntity {
    private Item item;

    /**
     * Constructs an ItemEntity with the specified location, weight, type, sprite manager, and item.
     * 
     * @param location      The location of the item entity.
     * @param weight        The weight of the item entity.
     * @param type          The type of the item entity.
     * @param spriteManager The sprite manager for the item entity.
     * @param item          The item contained in the item entity.
     */
    public ItemEntity(Point2D location, double weight, String type, SpriteManager spriteManager, Item item) {
        super(location, weight, type, spriteManager);
        this.item = item;
    }

    /**
     * Constructs an ItemEntity with the specified location and item.
     * 
     * @param location The location of the item entity.
     * @param item     The item contained in the item entity.
     */
    public ItemEntity(Point2D location, Item item) {
        super(location, (double) item.getDurability(), item.getName(), getSpriteManagerForItem(item));
        this.item = item;
    }

    /**
     * Constructs an ItemEntity with the specified location, item template, and durability.
     * 
     * @param location      The location of the item entity.
     * @param itemTemplate  The item template.
     * @param durability    The durability of the item.
     */
    public ItemEntity(Point2D location, ItemTemplate itemTemplate, int durability) {
        super(location, (double) durability, itemTemplate.getName(), new SpriteManager(itemTemplate.getSpriteName()));
        this.item = new Item(itemTemplate.getName(), durability, itemTemplate.getMaxValue(), itemTemplate);
    }

    /**
     * Returns a SpriteManager for the specified item.
     * 
     * @param item The item for which to get the sprite manager.
     * @return The sprite manager for the item.
     */
    private static SpriteManager getSpriteManagerForItem(Item item) {
        String itemNameUpperCase = item.getName().toUpperCase();
        EntitySprite entitySprite;
        try {
            entitySprite = EntitySprite.valueOf(itemNameUpperCase);
        } catch (IllegalArgumentException e) {
            // Fallback to a default sprite if no match is found
            entitySprite = EntitySprite.DEFAULT;
        }
        return new SpriteManager(entitySprite);
    }

    /**
     * Gets the item contained in the item entity.
     * 
     * @return The item contained in the item entity.
     */
    public Item getItem() {
        return item;
    }
}
