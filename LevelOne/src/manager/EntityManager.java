package manager;

import java.util.Map;
import java.util.HashMap;
import coordinates.Point2D;
import entity.Direction;
import entity.Entity;
import entity.LivingEntity;
import java.util.Collection;
import coordinates.CoordinatesLoader;
import java.util.List;
import java.util.ArrayList;
import observer.Observer;
import entity.Monster;
import entity.NPC;

public class EntityManager {
	private Map<Point2D, Entity> entityMap;
	private int gridWidth;
	private int gridHeight;
	private CoordinatesLoader coordinatesLoader;
	private List<Observer> observers = new ArrayList<>();
	private List<LivingEntity> movedEntities = new ArrayList<>();
	
	public EntityManager(int gridWidth, int gridHeight, CoordinatesLoader coordinatesLoader) {
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		this.entityMap = new HashMap<>();
		this.coordinatesLoader = coordinatesLoader;
	}
	
	public void addObserver(Observer observer) {
		observers.add(observer);
	}
	
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
	
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
	
	public CoordinatesLoader getCoordinatesLoader() {
		return coordinatesLoader;
	}
	
	public void addEntity(Entity entity) {
		entityMap.put(entity.getLocation(), entity);
		System.out.println("Entity added at " + entity.getLocation() + " of type " + entity.getType());
	}
	
	public void removeEntity(Entity entity) {
		entityMap.remove(entity.getLocation());
	}
	
	public Entity getEntity(Point2D location) {
		System.out.println("Getting entity at " + location);
		if (entityMap.get(location) == null) {
			System.out.println("No entity at " + location);
		}
		return entityMap.get(location);
	}
	
	public Collection<Entity> getEntities() {
		return entityMap.values();
	}
	
	public void moveEntity(LivingEntity entity, Direction direction) {
		entity.getSpriteManager().updateSprite(direction);
        Point2D oldLocation = entity.getLocation();
        Point2D newLocation = getNewLocation(oldLocation, direction);
		if (newLocation != null && canMoveTo(entity, newLocation)) {
			Entity entityAtNewLocation = getEntity(newLocation);
			if (entityAtNewLocation == null) {
				entity.setOldLocation(oldLocation);
				entity.setLocation(newLocation);
				entityMap.put(newLocation, entity);
				entityMap.remove(oldLocation);
				movedEntities.add(entity);
				System.out.println("Moved entity to " + newLocation);
			}
		}
		notifyObservers();

	}
	
	public Point2D getNewLocation(Point2D location, Direction direction) {
		switch (direction) {
		case UP:
			return coordinatesLoader.translateCoordinates(location, 0, -1);
		case DOWN:
			return coordinatesLoader.translateCoordinates(location, 0, 1);
		case LEFT:
			return coordinatesLoader.translateCoordinates(location, -1, 0);
		case RIGHT:
			return coordinatesLoader.translateCoordinates(location, 1, 0);
		default:
			return location;
		}
	}
	
//	Useless
//	public boolean isWithinBounds(Point2D location) {
//		System.out.println("Checking bounds for " + location);
//		if (location.getX() >= 0 && location.getX() < gridWidth && location.getY() >= 0
//				&& location.getY() < gridHeight) {
//			System.out.println("Within bounds");
//			return true;
//		}
//		System.out.println("Out of bounds");
//		return false;
//	}
	
	public boolean canMoveTo(LivingEntity entity, Point2D location) {
		Entity targetEntity = entityMap.get(location);
		if (targetEntity == null) {
			System.out.println("No entity at " + location);
			return true;
		}
		if (targetEntity instanceof LivingEntity) {
			System.out.println("Entity at " + location + " is a living entity");
			LivingEntity targetLivingEntity = (LivingEntity) targetEntity;
			return entity.getStrength() > targetLivingEntity.getWeight();
		}
		return false;
	}
	
	public List<LivingEntity> getMovedEntities() {
		List<LivingEntity> movedEntities = new ArrayList<>(this.movedEntities);
		this.movedEntities.clear();
		return movedEntities;
	}
	
	public String toString() {
		return entityMap.toString();
	}
	
	public List<Monster> getMonsters() {
		List<Monster> monsters = new ArrayList<>();
        for (Entity entity : entityMap.values()) {
            if (entity instanceof Monster) {
                monsters.add((Monster) entity);
            }
        }
        return monsters;
    }
	
	public List<NPC> getNPCs() {
		List<NPC> npcs = new ArrayList<>();
		for (Entity entity : entityMap.values()) {
			if (entity instanceof NPC) {
				npcs.add((NPC) entity);
			}
		}
		return npcs;
	}
	
	
	
}
