package entity;

import coordinates.Point2D;
import manager.EntityManager;
import items.Inventory;
import design.SpriteManager;
import items.PlayerInventory;

/**
 * The LivingEntity class represents a living entity in the game with various attributes such as health, armor, strength, and magic.
 */
public class LivingEntity extends Entity {
    private int level;
    private int experience;
    private int health;
    private int maxHealth;
    private int armor;
    private int maxArmor;
    private int strength;
    private int maxStrength;
    private int magic;
    private int maxMagic;
    private Inventory inventory;
    private EntityManager entityManager;
    private Point2D oldLocation;

    /**
     * Constructs a LivingEntity with the specified attributes.
     * 
     * @param location      The location of the entity.
     * @param weight        The weight of the entity.
     * @param health        The health of the entity.
     * @param maxHealth     The maximum health of the entity.
     * @param armor         The armor of the entity.
     * @param maxArmor      The maximum armor of the entity.
     * @param strength      The strength of the entity.
     * @param maxStrength   The maximum strength of the entity.
     * @param magic         The magic of the entity.
     * @param maxMagic      The maximum magic of the entity.
     * @param inventory     The inventory of the entity.
     * @param type          The type of the entity.
     * @param entityManager The entity manager for managing the entity.
     * @param level         The level of the entity.
     * @param experience    The experience of the entity.
     * @param spriteManager The sprite manager for the entity.
     */
    public LivingEntity(Point2D location, double weight, int health, int maxHealth, int armor, int maxArmor, int strength, int maxStrength, int magic, int maxMagic, Inventory inventory, String type, EntityManager entityManager, int level, int experience, SpriteManager spriteManager) {
        super(location, weight, type, spriteManager);
        this.setMaxHealth(maxHealth);
        this.setHealth(health);
        this.setArmor(maxArmor);
        this.setArmor(armor);
        this.setMaxStrength(maxStrength);
        this.setStrength(strength);
        this.setMaxMagic(maxMagic);
        this.setMagic(magic);
        this.setInventory(inventory);
        this.entityManager = entityManager;
        this.setLevel(level);
        this.setExperience(experience);
    }

    /**
     * Gets the level of the entity.
     * 
     * @return The level of the entity.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level of the entity.
     * 
     * @param level The new level of the entity.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the experience of the entity.
     * 
     * @return The experience of the entity.
     */
    public int getExperience() {
        return experience;
    }

    /**
     * Sets the experience of the entity.
     * 
     * @param experience The new experience of the entity.
     */
    public void setExperience(int experience) {
        this.experience = experience;
    }

    /**
     * Gets the health of the entity.
     * 
     * @return The health of the entity.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health of the entity.
     * 
     * @param health The new health of the entity.
     */
    public void setHealth(int health) {
        this.health = health;
        if (this.getHealth() < 0) {
            this.setHealth(0);
        } else if (this.getHealth() > this.getMaxHealth()) {
            this.setHealth(this.getMaxHealth());
        }
    }

    /**
     * Gets the maximum health of the entity.
     * 
     * @return The maximum health of the entity.
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * Sets the maximum health of the entity.
     * 
     * @param maxHealth The new maximum health of the entity.
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * Gets the armor of the entity.
     * 
     * @return The armor of the entity.
     */
    public int getArmor() {
        return armor;
    }

    /**
     * Sets the armor of the entity.
     * 
     * @param armor The new armor of the entity.
     */
    public void setArmor(int armor) {
        this.armor = armor;
        if (this.getArmor() < 0) {
            this.setArmor(0);
        } else if (this.getArmor() > this.getMaxArmor()) {
            this.setArmor(this.getMaxArmor());
        }
    }

    /**
     * Gets the maximum armor of the entity.
     * 
     * @return The maximum armor of the entity.
     */
    public int getMaxArmor() {
        return maxArmor;
    }

    /**
     * Sets the maximum armor of the entity.
     * 
     * @param maxArmor The new maximum armor of the entity.
     */
    public void setMaxArmor(int maxArmor) {
        this.maxArmor = maxArmor;
    }

    /**
     * Gets the strength of the entity.
     * 
     * @return The strength of the entity.
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Sets the strength of the entity.
     * 
     * @param strength The new strength of the entity.
     */
    public void setStrength(int strength) {
        this.strength = strength;
        if (this.getStrength() < 0) {
            this.setStrength(0);
        } else if (this.getStrength() > this.getMaxStrength()) {
            this.setStrength(this.getMaxStrength());
        }
    }

    /**
     * Gets the maximum strength of the entity.
     * 
     * @return The maximum strength of the entity.
     */
    public int getMaxStrength() {
        return maxStrength;
    }

    /**
     * Sets the maximum strength of the entity.
     * 
     * @param maxStrength The new maximum strength of the entity.
     */
    public void setMaxStrength(int maxStrength) {
        this.maxStrength = maxStrength;
    }

    /**
     * Gets the magic of the entity.
     * 
     * @return The magic of the entity.
     */
    public int getMagic() {
        return magic;
    }

    /**
     * Sets the magic of the entity.
     * 
     * @param magic The new magic of the entity.
     */
    public void setMagic(int magic) {
        this.magic = magic;
        if (this.getMagic() < 0) {
            this.setMagic(0);
        } else if (this.getMagic() > this.getMaxMagic()) {
            this.setMagic(this.getMaxMagic());
        }
    }

    /**
     * Gets the maximum magic of the entity.
     * 
     * @return The maximum magic of the entity.
     */
    public int getMaxMagic() {
        return maxMagic;
    }

    /**
     * Sets the maximum magic of the entity.
     * 
     * @param maxMagic The new maximum magic of the entity.
     */
    public void setMaxMagic(int maxMagic) {
        this.maxMagic = maxMagic;
    }

    /**
     * Gets the inventory of the entity.
     * 
     * @return The inventory of the entity.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Gets the player inventory of the entity.
     * 
     * @return The player inventory of the entity.
     */
    public PlayerInventory getPlayerInventory() {
        return (PlayerInventory) inventory;
    }

    /**
     * Sets the inventory of the entity.
     * 
     * @param inventory The new inventory of the entity.
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Checks if the entity is dead.
     * 
     * @return True if the entity's health is zero, otherwise false.
     */
    public boolean isDead() {
        return this.getHealth() == 0;
    }

    /**
     * Alters the health of the entity by the specified amount.
     * 
     * @param health The amount to alter the health.
     */
    public void alterHealth(int health) {
        this.setHealth(this.getHealth() + health);
        if (this.getHealth() < 0) {
            this.setHealth(0);
        } else if (this.getHealth() > this.getMaxHealth()) {
            this.setHealth(this.getMaxHealth());
        }
    }

    /**
     * Alters the maximum health of the entity by the specified amount.
     * 
     * @param maxHealth The amount to alter the maximum health.
     */
    public void alterMaxHealth(int maxHealth) {
        this.setMaxHealth(this.getMaxHealth() + maxHealth);
        if (this.getHealth() > this.getMaxHealth()) {
            this.setHealth(this.getMaxHealth());
        }
    }

    /**
     * Alters the armor of the entity by the specified amount.
     * 
     * @param armor The amount to alter the armor.
     */
    public void alterArmor(int armor) {
        this.setArmor(this.getArmor() + armor);
        if (this.getArmor() < 0) {
            this.setArmor(0);
        } else if (this.getArmor() > this.getMaxArmor()) {
            this.setArmor(this.getMaxArmor());
        }
    }

    /**
     * Alters the maximum armor of the entity by the specified amount.
     * 
     * @param maxArmor The amount to alter the maximum armor.
     */
    public void alterMaxArmor(int maxArmor) {
        this.setMaxArmor(this.getMaxArmor() + maxArmor);
        if (this.getArmor() > this.getMaxArmor()) {
            this.setArmor(this.getMaxArmor());
        }
    }

    /**
     * Alters the strength of the entity by the specified amount.
     * 
     * @param strength The amount to alter the strength.
     */
    public void alterStrength(int strength) {
        this.setStrength(this.getStrength() + strength);
        if (this.getStrength() < 0) {
            this.setStrength(0);
        } else if (this.getStrength() > this.getMaxStrength()) {
            this.setStrength(this.getMaxStrength());
        }
    }

    /**
     * Alters the maximum strength of the entity by the specified amount.
     * 
     * @param maxStrength The amount to alter the maximum strength.
     */
    public void alterMaxStrength(int maxStrength) {
        this.setMaxStrength(this.getMaxStrength() + maxStrength);
        if (this.getStrength() > this.getMaxStrength()) {
            this.setStrength(this.getMaxStrength());
        }
    }

    /**
     * Alters the magic of the entity by the specified amount.
     * 
     * @param magic The amount to alter the magic.
     */
    public void alterMagic(int magic) {
        this.setMagic(this.getMagic() + magic);
        if (this.getMagic() < 0) {
            this.setMagic(0);
        } else if (this.getMagic() > this.getMaxMagic()) {
            this.setMagic(this.getMaxMagic());
        }
    }

    /**
     * Alters the maximum magic of the entity by the specified amount.
     * 
     * @param maxMagic The amount to alter the maximum magic.
     */
    public void alterMaxMagic(int maxMagic) {
        this.setMaxMagic(this.getMaxMagic() + maxMagic);
        if (this.getMagic() > this.getMaxMagic()) {
            this.setMagic(this.getMaxMagic());
        }
    }

    /**
     * Moves the entity in the specified direction.
     * 
     * @param direction The direction to move the entity.
     */
    public void move(Direction direction) {
        entityManager.moveEntity(this, direction);
    }

    /**
     * Moves the entity to the specified location.
     * 
     * @param location The new location for the entity.
     */
    public void move(Point2D location) {
        entityManager.moveEntity(this, location);
    }

    /**
     * Gets the old location of the entity.
     * 
     * @return The old location of the entity.
     */
    public Point2D getOldLocation() {
        return oldLocation;
    }

    /**
     * Sets the old location of the entity.
     * 
     * @param oldLocation The old location of the entity.
     */
    public void setOldLocation(Point2D oldLocation) {
        this.oldLocation = oldLocation;
    }

    /**
     * Gets the entity manager for managing the entity.
     * 
     * @return The entity manager.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Sets the entity manager for managing the entity.
     * 
     * @param entityManager The new entity manager.
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Gets the sprite manager for the entity.
     * 
     * @return The sprite manager.
     */
    public SpriteManager getSpriteManager() {
        return super.getSpriteManager();
    }
}
