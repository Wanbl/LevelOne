package entity;

import items.ItemTemplate;

/**
 * The MonsterType enum represents different types of monsters in the game and their corresponding inventory items.
 */
public enum MonsterType {
    KNIFEBADGUY(new ItemTemplate[] {ItemTemplate.KNIFE}),  // A monster that has a knife in its inventory
    THING(new ItemTemplate[] {ItemTemplate.APPLE});        // A monster that has an apple in its inventory

    private ItemTemplate[] inventoryItems;

    /**
     * Constructs a MonsterType with the specified inventory items.
     * 
     * @param inventoryItems The array of inventory items for the monster type.
     */
    MonsterType(ItemTemplate[] inventoryItems) {
        this.inventoryItems = inventoryItems;
    }

    /**
     * Gets the inventory items for the monster type.
     * 
     * @return The array of inventory items for the monster type.
     */
    public ItemTemplate[] getInventoryItems() {
        return inventoryItems;
    }
}
