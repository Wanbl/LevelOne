package entity;

import coordinates.Point2D;
import items.Inventory;
import manager.EntityManager;
import design.SpriteManager;
import items.PlayerInventory;

/**
 * The Player class represents a player character in the game.
 * It extends the LivingEntity class and includes additional properties specific to players.
 */
public class Player extends LivingEntity {
    private String pseudo;

    /**
     * Constructs a Player with the specified attributes.
     * 
     * @param location      The location of the player.
     * @param weight        The weight of the player.
     * @param health        The health of the player.
     * @param maxHealth     The maximum health of the player.
     * @param armor         The armor of the player.
     * @param maxArmor      The maximum armor of the player.
     * @param strength      The strength of the player.
     * @param maxStrength   The maximum strength of the player.
     * @param magic         The magic of the player.
     * @param maxMagic      The maximum magic of the player.
     * @param inventory     The inventory of the player.
     * @param type          The type of the player.
     * @param entityManager The entity manager for managing the player.
     * @param level         The level of the player.
     * @param experience    The experience of the player.
     * @param pseudo        The pseudo (nickname) of the player.
     * @param spriteManager The sprite manager for the player.
     */
    public Player(Point2D location, double weight, int health, int maxHealth, int armor, int maxArmor, int strength, int maxStrength, int magic, int maxMagic, PlayerInventory inventory, String type, EntityManager entityManager, int level, int experience, String pseudo, SpriteManager spriteManager) {
        super(location, weight, health, maxHealth, armor, maxArmor, strength, maxStrength, magic, maxMagic, inventory, type, entityManager, level, experience, spriteManager);
        this.setPseudo(pseudo);
    }

    /**
     * Constructs a Player with default attributes.
     * 
     * @param location      The location of the player.
     * @param pseudo        The pseudo (nickname) of the player.
     * @param entityManager The entity manager for managing the player.
     * @param spriteManager The sprite manager for the player.
     */
    public Player(Point2D location, String pseudo, EntityManager entityManager, SpriteManager spriteManager) {
        this(location, 70, 100, 100, 10, 10, 10, 10, 10, 10, new PlayerInventory(), "player", entityManager, 1, 0, pseudo, spriteManager);
    }

    /**
     * Gets the pseudo (nickname) of the player.
     * 
     * @return The pseudo of the player.
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Sets the pseudo (nickname) of the player.
     * 
     * @param pseudo The new pseudo of the player.
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
}
