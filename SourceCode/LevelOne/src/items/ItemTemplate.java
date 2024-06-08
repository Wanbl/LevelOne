package items;

/**
 * The ItemTemplate enum represents different types of items in the game.
 * Each item has a name, type, weight, and maximum value.
 */
public enum ItemTemplate {
    APPLE("Apple", ItemType.FOOD, 1, 64),
    ORANGE("Orange", ItemType.FOOD, 1, 64),
    PEAR("Pear", ItemType.FOOD, 1, 64),
    SWORD("Sword", ItemType.WEAPON, 50, 100),
    KNIFE("Knife", ItemType.WEAPON, 5, 50),
    HELMET("Helmet", ItemType.ARMOR, 50, 100),
    FORESTKEY("ForestKey", ItemType.FORESTKEY, 1, 1),
    WIN("Win", ItemType.WIN, 1, 1),
    ITEM3("Item3", ItemType.ITEM3, 1, 1),
    ITEM4("Item4", ItemType.ITEM4, 1, 1),
    ITEM1("Item1", ItemType.ITEM1, 1, 1),
    ITEM2("Item2", ItemType.ITEM2, 1, 1),
    DEFAULT("Default", ItemType.DEFAULT, 1, 1);

    private String name;
    private ItemType type;
    private int weight;
    private int maxValue;

    /**
     * Constructs an ItemTemplate with the specified attributes.
     * 
     * @param name     The name of the item.
     * @param type     The type of the item.
     * @param weight   The weight of the item.
     * @param maxValue The maximum value of the item.
     */
    private ItemTemplate(String name, ItemType type, int weight, int maxValue) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.maxValue = maxValue;
    }

    /**
     * Gets the name of the item.
     * 
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the type of the item.
     * 
     * @return The type of the item.
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Gets the weight of the item.
     * 
     * @return The weight of the item.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets the maximum value of the item.
     * 
     * @return The maximum value of the item.
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Gets the sprite name of the item.
     * 
     * @return The sprite name of the item.
     */
    public String getSpriteName() {
        return "data/templates/items/" + name.toLowerCase() + ".png";
    }
}
