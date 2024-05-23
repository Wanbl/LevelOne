package items;

public enum ItemTemplate {
	APPLE("Apple", "food", 1, 1),
	ORANGE("Orange", "food", 1, 1),
	PEAR("Pear", "food", 1, 1);
	
	private String name;
	private String type;
	private int weight;
	private int value;
	
	private ItemTemplate(String name, String type, int weight, int value) {
		this.name = name;
		this.type = type;
		this.weight = weight;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getValue() {
		return value;
	}
}
