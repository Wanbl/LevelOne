package entity;

import coordinates.Point2D;
import items.Inventory;
import manager.EntityManager;
import design.SpriteManager;

/**
 * The Monster class represents a monster entity in the game, which is a type of LivingEntity.
 */
public class Monster extends LivingEntity {

    /**
     * Constructs a Monster with the specified attributes.
     * 
     * @param location      The location of the monster.
     * @param weight        The weight of the monster.
     * @param health        The health of the monster.
     * @param maxHealth     The maximum health of the monster.
     * @param armor         The armor of the monster.
     * @param maxArmor      The maximum armor of the monster.
     * @param strength      The strength of the monster.
     * @param maxStrength   The maximum strength of the monster.
     * @param magic         The magic of the monster.
     * @param maxMagic      The maximum magic of the monster.
     * @param inventory     The inventory of the monster.
     * @param type          The type of the monster.
     * @param entityManager The entity manager for managing the monster.
     * @param level         The level of the monster.
     * @param experience    The experience of the monster.
     * @param spriteManager The sprite manager for the monster.
     */
    public Monster(Point2D location, double weight, int health, int maxHealth, int armor, int maxArmor, int strength, int maxStrength, int magic, int maxMagic, Inventory inventory, String type, EntityManager entityManager, int level, int experience, SpriteManager spriteManager) {
        super(location, weight, health, maxHealth, armor, maxArmor, strength, maxStrength, magic, maxMagic, inventory, type, entityManager, level, experience, spriteManager);
    }

    /**
     * Constructs a Monster with default attributes.
     * 
     * @param location      The location of the monster.
     * @param type          The type of the monster.
     * @param entityManager The entity manager for managing the monster.
     * @param spriteManager The sprite manager for the monster.
     */
    public Monster(Point2D location, String type, EntityManager entityManager, SpriteManager spriteManager) {
        super(location, 70, 100, 100, 10, 10, 10, 10, 10, 10, new Inventory(), type, entityManager, 1, 0, spriteManager);
    }
}
