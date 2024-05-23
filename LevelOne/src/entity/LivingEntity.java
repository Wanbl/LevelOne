package entity;
import coordinates.Point2D;
import manager.EntityManager;
import items.Inventory;
import design.SpriteManager;

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
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getHealth() {
		return health;
	}
	

	public void setHealth(int health) {
		this.health = health;
		if(this.getHealth() < 0) {
			this.setHealth(0);
		}
		else if(this.getHealth() > this.getMaxHealth()) {
			this.setHealth(this.getMaxHealth());
		}
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
		if(this.getArmor() < 0) {
			this.setArmor(0);
		}
		else if(this.getArmor() > this.getMaxArmor()) {
			this.setArmor(this.getMaxArmor());
		}
	}
	
	public int getMaxArmor() {
		return maxArmor;
	}

	public void setMaxArmor(int maxArmor) {
		this.maxArmor = maxArmor;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
		if(this.getStrength() < 0) {
			this.setStrength(0);
		}
		else if(this.getStrength() > this.getMaxStrength()) {
			this.setStrength(this.getMaxStrength());
		}
	}
	
	public int getMaxStrength() {
		return maxStrength;
	}

	public void setMaxStrength(int maxStrength) {
		this.maxStrength = maxStrength;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
		if(this.getMagic() < 0) {
			this.setMagic(0);
		}
		else if(this.getMagic() > this.getMaxMagic()) {
			this.setMagic(this.getMaxMagic());
		}
	}
	
	public int getMaxMagic() {
		return maxMagic;
	}

	public void setMaxMagic(int maxMagic) {
		this.maxMagic = maxMagic;
	}
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public boolean isDead() {
		return this.getHealth() == 0;
	}
	
	public void alterHealth(int health) {
		this.setHealth(this.getHealth()+health);
		if(this.getHealth() < 0) {
			this.setHealth(0);
		}
		else if(this.getHealth() > this.getMaxHealth()) {
			this.setHealth(this.getMaxHealth());
		}
	}
	
	public void alterMaxHealth(int maxHealth) {
		this.setMaxHealth(this.getMaxHealth()+maxHealth);
		if(this.getHealth() > this.getMaxHealth()) {
			this.setHealth(this.getMaxHealth());
		}
	}
	
	public void alterArmor(int armor) {
		this.setArmor(this.getArmor()+armor);
		if(this.getArmor() < 0) {
			this.setArmor(0);
		}
		else if(this.getArmor() > this.getMaxArmor()) {
			this.setArmor(this.getMaxArmor());
		}
	}
	
	public void alterMaxArmor(int maxArmor) {
		this.setMaxArmor(this.getMaxArmor()+maxArmor);
		if(this.getArmor() > this.getMaxArmor()) {
			this.setArmor(this.getMaxArmor());
		}
	}
	
	public void alterStrength(int strength) {
		this.setStrength(this.getStrength()+strength);
		if(this.getStrength() < 0) {
			this.setStrength(0);
		}
		else if(this.getStrength() > this.getMaxStrength()) {
			this.setStrength(this.getMaxStrength());
		}
	}
	
	public void alterMaxStrength(int maxStrength) {
		this.setMaxStrength(this.getMaxStrength()+maxStrength);
		if(this.getStrength() > this.getMaxStrength()) {
			this.setStrength(this.getMaxStrength());
		}
	}
	
	public void alterMagic(int magic) {
		this.setMagic(this.getMagic()+magic);
		if(this.getMagic() < 0) {
			this.setMagic(0);
		}
		else if(this.getMagic() > this.getMaxMagic()) {
			this.setMagic(this.getMaxMagic());
		}
	}
	
	public void alterMaxMagic(int maxMagic) {
		this.setMaxMagic(this.getMaxMagic()+maxMagic);
		if(this.getMagic() > this.getMaxMagic()) {
			this.setMagic(this.getMaxMagic());
		}
	}
	
	public void move(Direction direction) {
		entityManager.moveEntity(this, direction);
	}
	
	public Point2D getOldLocation() {
		return oldLocation;
	}
	
	public void setOldLocation(Point2D oldLocation) {
		this.oldLocation = oldLocation;
	}


}
