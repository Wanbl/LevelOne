package entity;

import coordinates.Point2D;
import design.SpriteManager;

public class NonLivingEntity extends Entity {
	public NonLivingEntity(Point2D location, double weight, String type, SpriteManager spriteManager) {
		super(location, weight, type, spriteManager);
	}

}
