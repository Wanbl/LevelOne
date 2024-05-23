package entity;
import coordinates.Point2D;
import design.SpriteManager;

public class Entity {
	private Point2D location;
	private double weight;
	private Direction direction;
	private String Type;
	private SpriteManager spriteManager;

	public Point2D getLocation() {
		return location;
	}

	public void setLocation(Point2D location) {
		this.location = location;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}	
	
	public Entity(Point2D location, double weight, String type, SpriteManager spriteManager) {
		this.setLocation(location);
		this.setWeight(weight);
		this.Type = type;
		this.spriteManager = spriteManager;
	}
	
	public void move(int x, int y) {
		this.location.translateX(x);
		this.location.translateY(y);
	}
	
	public String getType() {
		return Type;
	}
	
	public SpriteManager getSpriteManager() {
		return spriteManager;
	}

}
