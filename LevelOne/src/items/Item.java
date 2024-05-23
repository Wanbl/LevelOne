package items;

import entity.Entity;

public class Item {
	private int durability;
	private int maxDurability;
	private String name;
	
	public Item(String name, int durability, int maxDurability) {
		this.name = name;
		this.durability = durability;
		this.maxDurability = maxDurability;
	}
	
	public int getDurability() {
		return durability;
	}
	
	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	public int getMaxDurability() {
		return maxDurability;
	}
	
	public void setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void use(Entity target) {
		if (durability > 0) {
			durability--;
		}
	}
	
	public boolean isBroken() {
		return durability <= 0;
	}
	
	public void repair(int amount) {
		durability += amount;
		if (durability > maxDurability) {
			durability = maxDurability;
		}
	}
	
	

}
