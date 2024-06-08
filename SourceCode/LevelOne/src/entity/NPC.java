package entity;

import coordinates.Point2D;
import items.Inventory;
import manager.EntityManager;
import design.SpriteManager;

/**
 * The NPC class represents a non-player character (NPC) in the game.
 * It extends the LivingEntity class and includes additional properties specific to NPCs.
 */
public class NPC extends LivingEntity {
    private NPCType npcType;
    private String currentDialogue;

    /**
     * Constructs an NPC with the specified attributes.
     * 
     * @param location      The location of the NPC.
     * @param weight        The weight of the NPC.
     * @param health        The health of the NPC.
     * @param maxHealth     The maximum health of the NPC.
     * @param armor         The armor of the NPC.
     * @param maxArmor      The maximum armor of the NPC.
     * @param strength      The strength of the NPC.
     * @param maxStrength   The maximum strength of the NPC.
     * @param magic         The magic of the NPC.
     * @param maxMagic      The maximum magic of the NPC.
     * @param inventory     The inventory of the NPC.
     * @param type          The type of the NPC.
     * @param entityManager The entity manager for managing the NPC.
     * @param level         The level of the NPC.
     * @param experience    The experience of the NPC.
     * @param spriteManager The sprite manager for the NPC.
     * @param npcType       The NPCType of the NPC.
     */
    public NPC(Point2D location, double weight, int health, int maxHealth, int armor, int maxArmor, int strength, int maxStrength, int magic, int maxMagic, Inventory inventory, String type, EntityManager entityManager, int level, int experience, SpriteManager spriteManager, NPCType npcType) {
        super(location, weight, health, maxHealth, armor, maxArmor, strength, maxStrength, magic, maxMagic, inventory, type, entityManager, level, experience, spriteManager);
        this.setNpcType(npcType);
        this.setCurrentDialogue(this.npcType.getFirstDialogue());
    }

    /**
     * Constructs an NPC with the specified attributes, using default maximum values for health, armor, strength, and magic.
     * 
     * @param location      The location of the NPC.
     * @param inventory     The inventory of the NPC.
     * @param type          The type of the NPC.
     * @param entityManager The entity manager for managing the NPC.
     * @param spriteManager The sprite manager for the NPC.
     * @param npcType       The NPCType of the NPC.
     */
    public NPC(Point2D location, Inventory inventory, String type, EntityManager entityManager, SpriteManager spriteManager, NPCType npcType) {
        super(location, 70, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, inventory, type, entityManager, 1, 0, spriteManager);
        this.setNpcType(npcType);
        this.setCurrentDialogue(this.npcType.getFirstDialogue());
    }

    /**
     * Constructs an NPC with the specified location, type, entity manager, sprite manager, and npcType.
     * 
     * @param location      The location of the NPC.
     * @param type          The type of the NPC.
     * @param entityManager The entity manager for managing the NPC.
     * @param spriteManager The sprite manager for the NPC.
     * @param npcType       The NPCType of the NPC.
     */
    public NPC(Point2D location, String type, EntityManager entityManager, SpriteManager spriteManager, NPCType npcType) {
        this(location, new Inventory(), type, entityManager, spriteManager, npcType);
    }

    /**
     * Gets the NPCType of the NPC.
     * 
     * @return The NPCType of the NPC.
     */
    public NPCType getNpcType() {
        return npcType;
    }

    /**
     * Sets the NPCType of the NPC.
     * 
     * @param npcType The new NPCType of the NPC.
     */
    public void setNpcType(NPCType npcType) {
        this.npcType = npcType;
    }

    /**
     * Gets the current dialogue of the NPC.
     * 
     * @return The current dialogue of the NPC.
     */
    public String getCurrentDialogue() {
        return currentDialogue;
    }

    /**
     * Sets the current dialogue of the NPC.
     * 
     * @param currentDialogue The new current dialogue of the NPC.
     */
    public void setCurrentDialogue(String currentDialogue) {
        this.currentDialogue = currentDialogue;
    }
}
