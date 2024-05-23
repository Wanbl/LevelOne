package entity;

import coordinates.Point2D;
import items.Inventory;
import manager.EntityManager;
import design.SpriteManager;

public class Player extends LivingEntity {
	private String pseudo;
	
	public Player(Point2D location, double weight, int health, int maxHealth, int armor, int maxArmor, int strength, int maxStrength, int magic, int maxMagic, Inventory inventory, String type, EntityManager entityManager, int level, int experience, String pseudo, SpriteManager spriteManager) {
		super(location, weight, health, maxHealth, armor, maxArmor, strength, maxStrength, magic, maxMagic, inventory, type, entityManager, level, experience, spriteManager);
		this.setPseudo(pseudo);
	}
	
	public Player(Point2D location, String pseudo, EntityManager entityManager, SpriteManager spriteManager) {
        this(location, 70, 100, 100, 10, 10, 10, 10, 10, 10, new Inventory(), "player", entityManager, 1, 0, pseudo, spriteManager);
    }

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
}
