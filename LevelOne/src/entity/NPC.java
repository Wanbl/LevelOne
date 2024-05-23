package entity;

import coordinates.Point2D;
import items.Inventory;
import manager.EntityManager;
import design.SpriteManager;

public class NPC extends LivingEntity {
	public NPC(Point2D location, double weight, int health, int maxHealth, int armor, int maxArmor, int strength, int maxStrength, int magic, int maxMagic, Inventory inventory, String type, EntityManager entityManager, int level, int experience, SpriteManager spriteManager) {
		super(location, weight, health, maxHealth, armor, maxArmor, strength, maxStrength, magic, maxMagic, inventory, type, entityManager, level, experience, spriteManager);
	}
	public NPC(Point2D location, Inventory inventory, String type, EntityManager entityManager, SpriteManager spriteManager) {
		super(location, 70, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, inventory, type, entityManager, 1, 0, spriteManager);
	}
	
	public NPC(Point2D location, String type, EntityManager entityManager, SpriteManager spriteManager) {
		this(location, new Inventory(), type, entityManager, spriteManager);
	}
}
