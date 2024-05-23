package items;

public class Inventory {
	private int size;
	private int sizeModifier;
	private int nbSlotUsed;
	
	public Inventory(int size, int sizeModifier, int nbSlotUsed) {
		this.setSize(size);
		this.setSizeModifier(sizeModifier);
		this.setNbSlotUsed(nbSlotUsed);
	}
	
	public Inventory() {
        this(10, 0, 0);
    }

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getSizeModifier() {
		return sizeModifier;
	}
	
	public void setSizeModifier(int sizeModifier) {
		this.sizeModifier = sizeModifier;
	}
	
	public int getNbSlotUsed() {
		return nbSlotUsed;
	}
	
	public void setNbSlotUsed(int nbSlotUsed) {
		this.nbSlotUsed = nbSlotUsed;
	}
	
	public Inventory(int size) {
		this.setSize(size);
	}
}
