package items;

import entity.Entity;

/**
 * The Item class represents an item in the game with attributes such as durability, maximum durability, name, and template.
 */
public class Item {
    private int durability;
    private int maxDurability;
    private String name;
    private ItemTemplate template;

    /**
     * Constructs an Item with the specified name, durability, maximum durability, and template.
     * 
     * @param name          The name of the item.
     * @param durability    The durability of the item.
     * @param maxDurability The maximum durability of the item.
     * @param template      The template of the item.
     */
    public Item(String name, int durability, int maxDurability, ItemTemplate template) {
        this.name = name;
        this.durability = durability;
        this.maxDurability = maxDurability;
        this.template = template;
    }

    /**
     * Constructs an Item with the specified template and uniqueness.
     * 
     * @param template The template of the item.
     * @param unique   Whether the item is unique.
     */
    public Item(ItemTemplate template, boolean unique) {
        if (unique) {
            this.name = template.getName();
            this.durability = template.getMaxValue();
            this.maxDurability = template.getMaxValue();
            this.template = template;
        } else {
            this.name = template.getName();
            this.durability = 1;
            this.maxDurability = template.getMaxValue();
            this.template = template;
        }
    }

    /**
     * Gets the durability of the item.
     * 
     * @return The durability of the item.
     */
    public int getDurability() {
        return durability;
    }

    /**
     * Sets the durability of the item.
     * 
     * @param durability The new durability of the item.
     */
    public void setDurability(int durability) {
        this.durability = durability;
    }

    /**
     * Gets the maximum durability of the item.
     * 
     * @return The maximum durability of the item.
     */
    public int getMaxDurability() {
        return maxDurability;
    }

    /**
     * Sets the maximum durability of the item.
     * 
     * @param maxDurability The new maximum durability of the item.
     */
    public void setMaxDurability(int maxDurability) {
        this.maxDurability = maxDurability;
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
     * Sets the name of the item.
     * 
     * @param name The new name of the item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Checks if the item is broken (i.e., its durability is less than or equal to zero).
     * 
     * @return True if the item is broken, otherwise false.
     */
    public boolean isBroken() {
        return durability <= 0;
    }

    /**
     * Checks if the durability of the item can be increased.
     * 
     * @return True if the durability is less than the maximum durability, otherwise false.
     */
    public boolean canDurabilityIncrease() {
        return durability < maxDurability;
    }

    /**
     * Gets the template of the item.
     * 
     * @return The template of the item.
     */
    public ItemTemplate getTemplate() {
        return template;
    }

    /**
     * Updates the durability of the item by the specified amount.
     * 
     * @param durability The amount to update the durability by.
     */
    public void updateDurability(int durability) {
        this.durability += durability;
        if (this.durability < 0) {
            this.durability = 0;
        }
        if (this.durability > maxDurability) {
            this.durability = maxDurability;
        }
    }
}
