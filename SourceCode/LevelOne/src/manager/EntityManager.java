package manager;

import java.util.Map;
import java.util.HashMap;
import coordinates.Point2D;
import entity.DecorEntity;
import entity.DecorType;
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
import entity.ItemEntity;
import entity.Player;
import items.PlayerInventory;
import map.MapType;
import items.Item;
import items.ItemTemplate;
import chat.GlobalChat;

/**
 * The EntityManager class manages entities within a game map,
 * handling their addition, removal, movement, and interactions.
 */
public class EntityManager {
    private Map<Point2D, Entity> entityMap;
    private int gridWidth;
    private int gridHeight;
    private CoordinatesLoader coordinatesLoader;
    private List<Observer> observers = new ArrayList<>();
    private List<LivingEntity> movedEntities = new ArrayList<>();
    private SceneManager sceneManager;

    /**
     * Constructs an EntityManager with specified grid dimensions and a scene manager.
     * 
     * @param gridWidth        The width of the grid.
     * @param gridHeight       The height of the grid.
     * @param coordinatesLoader The coordinates loader for translating locations.
     * @param sceneManager     The scene manager for handling scene changes.
     */
    public EntityManager(int gridWidth, int gridHeight, CoordinatesLoader coordinatesLoader, SceneManager sceneManager) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.entityMap = new HashMap<>();
        this.coordinatesLoader = coordinatesLoader;
        this.sceneManager = sceneManager;
    }

    /**
     * Adds an observer to the entity manager.
     * 
     * @param observer The observer to add.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifies all observers of changes to the entity manager.
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Removes an observer from the entity manager.
     * 
     * @param observer The observer to remove.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Gets the coordinates loader used by the entity manager.
     * 
     * @return The coordinates loader.
     */
    public CoordinatesLoader getCoordinatesLoader() {
        return coordinatesLoader;
    }

    /**
     * Adds an entity to the entity manager at its current location.
     * 
     * @param entity The entity to add.
     */
    public void addEntity(Entity entity) {
        entityMap.put(entity.getLocation(), entity);
        System.out.println("EntityManager : addEntity() : Entity added at " + entity.getLocation() + " of type " + entity.getType());
    }

    /**
     * Removes an entity from the entity manager.
     * 
     * @param entity The entity to remove.
     */
    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getLocation());
    }

    /**
     * Gets an entity at a specified location.
     * 
     * @param location The location to check.
     * @return The entity at the location, or null if no entity exists there.
     */
    public Entity getEntity(Point2D location) {
        System.out.println("EntityManager : getEntity() : Getting entity at " + location);
        if (entityMap.get(location) == null) {
            System.out.println("EntityManager : getEntity() : No entity at " + location);
        }
        return entityMap.get(location);
    }

    /**
     * Gets all entities managed by the entity manager.
     * 
     * @return A collection of all entities.
     */
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    /**
     * Gets the entity in the specified direction from a given entity.
     * 
     * @param entity    The reference entity.
     * @param direction The direction to check.
     * @return The entity in the specified direction, or null if no entity exists there.
     */
    public Entity getDirectionEntity(LivingEntity entity, Direction direction) {
        Point2D newLocation = getNewLocation(entity.getLocation(), direction);
        return getEntity(newLocation);
    }

    /**
     * Moves a living entity in a specified direction and handles interactions with entities at the new location.
     * 
     * @param entity    The entity to move.
     * @param direction The direction to move.
     */
    public void moveEntity(LivingEntity entity, Direction direction) {
        entity.getSpriteManager().updateSprite(direction);
        Point2D oldLocation = entity.getLocation();
        Point2D newLocation = getNewLocation(oldLocation, direction);
        if (newLocation != null) {
            Entity entityAtNewLocation = getEntity(newLocation);
            if (canMoveTo(entity, newLocation)) {
                entity.setOldLocation(oldLocation);
                entity.setLocation(newLocation);
                entityMap.put(newLocation, entity);
                entityMap.remove(oldLocation);
                movedEntities.add(entity);
                System.out.println("EntityManager : moveEntity() : Moved entity to " + newLocation);
            } else if (entityAtNewLocation instanceof ItemEntity) {
                entity.getInventory().addItem(((ItemEntity) entityAtNewLocation).getItem());
                entityMap.remove(newLocation);
                entity.setOldLocation(oldLocation);
                entity.setLocation(newLocation);
                entityMap.put(newLocation, entity);
                entityMap.remove(oldLocation);
                movedEntities.add(entity);
                System.out.println("EntityManager : moveEntity() : Moved entity to " + newLocation + " and picked up item: " + ((ItemEntity) entityAtNewLocation).getItem().getName());
            } else if (entityAtNewLocation instanceof LivingEntity) {
                System.out.println("EntityManager : moveEntity() : Check for attack: Entity at " + newLocation + " is a living entity");
                if (entity instanceof Player) {
                    if (entityAtNewLocation instanceof NPC) {
                        startQuest(entity, direction);
                    } else if (entityAtNewLocation instanceof Monster) {
                        hitEntity(entity, direction);
                    }
                } else {
                    hitEntity(entity, direction);
                }
            }
        }
        notifyObservers();
    }

    /**
     * Moves a living entity to a specified location and handles interactions with entities at the new location.
     * 
     * @param entity     The entity to move.
     * @param newLocation The location to move to.
     */
    public void moveEntity(LivingEntity entity, Point2D newLocation) {
        Point2D oldLocation = entity.getLocation();
        if (newLocation != null) {
            Entity entityAtNewLocation = getEntity(newLocation);
            if (canMoveTo(entity, newLocation)) {
                entity.setOldLocation(oldLocation);
                entity.setLocation(newLocation);
                entityMap.put(newLocation, entity);
                entityMap.remove(oldLocation);
                movedEntities.add(entity);
                System.out.println("EntityManager : moveEntity() : Moved entity to " + newLocation);
            } else if (entityAtNewLocation instanceof ItemEntity) {
                entity.getInventory().addItem(((ItemEntity) entityAtNewLocation).getItem());
                entityMap.remove(newLocation);
                entity.setOldLocation(oldLocation);
                entity.setLocation(newLocation);
                entityMap.put(newLocation, entity);
                entityMap.remove(oldLocation);
                movedEntities.add(entity);
                System.out.println("EntityManager : moveEntity() : Moved entity to " + newLocation + " and picked up item: " + ((ItemEntity) entityAtNewLocation).getItem().getName());
            } else if (entityAtNewLocation instanceof LivingEntity) {
                System.out.println("EntityManager : moveEntity() : Check for attack: Entity at " + newLocation + " is a living entity");
                if (entity instanceof Player) {
                    if (entityAtNewLocation instanceof NPC) {
                        startQuest(entity, Direction.UP);
                    } else if (entityAtNewLocation instanceof Monster) {
                        hitEntity(entity, Direction.UP);
                    }
                } else {
                    hitEntity(entity, Direction.UP);
                }
            }
        }
        notifyObservers();
    }

    /**
     * Starts a quest for an NPC if the player has the required item.
     * 
     * @param entity    The entity interacting with the NPC.
     * @param direction The direction of the NPC.
     */
    public void startQuest(LivingEntity entity, Direction direction) {
        Point2D newLocation = getNewLocation(entity.getLocation(), direction);
        Entity entityAtNewLocation = getEntity(newLocation);
        if (entityAtNewLocation instanceof NPC) {
            NPC targetNPC = (NPC) entityAtNewLocation;
            if (entity instanceof Player) {
                Player player = (Player) entity;
                PlayerInventory playerInventory = (PlayerInventory) player.getInventory();
                Item selectedItem = playerInventory.getSelectedItem();
                if (selectedItem != null && selectedItem.getTemplate() == targetNPC.getNpcType().getSearchedItem()) {
                    playerInventory.removeItem(selectedItem);
                    targetNPC.setCurrentDialogue(targetNPC.getNpcType().getSecondDialogue());
                    lootEntity(player, targetNPC);
                }
                GlobalChat.addMessage(targetNPC.getNpcType().name() + " : " + targetNPC.getCurrentDialogue());
            }
        }
        notifyObservers();
    }

    /**
     * Transfers all items from a looted entity to the looting entity.
     * 
     * @param entity      The entity performing the looting.
     * @param lootedEntity The entity being looted.
     */
    public void lootEntity(LivingEntity entity, LivingEntity lootedEntity) {
        for (Item item : lootedEntity.getInventory().getItemList()) {
            entity.getInventory().addItem(item);
        }
        lootedEntity.getInventory().clearInventory();
    }

    /**
     * Hits an entity in a specified direction, reducing its health and possibly removing it if it dies.
     * 
     * @param entity    The entity performing the hit.
     * @param direction The direction of the target entity.
     */
    public void hitEntity(LivingEntity entity, Direction direction) {
        Point2D newLocation = getNewLocation(entity.getLocation(), direction);
        Entity entityAtNewLocation = getEntity(newLocation);
        if (entityAtNewLocation instanceof LivingEntity) {
            System.out.println("EntityManager : hitEntity() : Entity at " + newLocation + " is a living entity");
            LivingEntity targetLivingEntity = (LivingEntity) entityAtNewLocation;
            Item selectedItem = null;
            if (entity instanceof Player) {
                PlayerInventory playerInventory = (PlayerInventory) ((Player) entity).getInventory();
                selectedItem = playerInventory.getSelectedItem();
            } else {
                selectedItem = entity.getInventory().getItem(0, 0);
            }
            if (selectedItem == null) {
                targetLivingEntity.alterHealth(-(entity.getStrength()) / 2);
            } else {
                targetLivingEntity.alterHealth(-(entity.getStrength() * selectedItem.getTemplate().getWeight()) / 2);
            }
            if(targetLivingEntity instanceof Player) {
                if (targetLivingEntity.isDead()) {
                    sceneManager.changeScene(MapType.GAMEOVER);
                    GlobalChat.addMessage("You have been defeated by " + entity.getType() + " at " + newLocation + " with strength " + entity.getStrength());
                }
            }
            System.out.println("EntityManager : hitEntity() : Hit entity at " + newLocation + " with strength " + entity.getStrength());
            if (targetLivingEntity.isDead()) {
                lootEntity(entity, targetLivingEntity);
                entityMap.remove(newLocation);
                GlobalChat.addMessage("Killed entity at " + newLocation);
                System.out.println("EntityManager : hitEntity() : Killed entity at " + newLocation);
            }
            movedEntities.add(targetLivingEntity);
        }
        notifyObservers();
    }

    /**
     * Uses an item in a specified direction, performing actions based on the item's type.
     * 
     * @param entity    The entity using the item.
     * @param direction The direction to use the item.
     */
    public void useItem(LivingEntity entity, Direction direction) {
        Point2D newLocation = getNewLocation(entity.getLocation(), direction);
        Entity entityAtNewLocation = getEntity(newLocation);
        Item selectedItem = null;
        if (entity instanceof Player) {
            PlayerInventory playerInventory = (PlayerInventory) ((Player) entity).getInventory();
            selectedItem = playerInventory.getSelectedItem();
        } else {
            selectedItem = entity.getInventory().getItem(0, 0);
        }
        if (entityAtNewLocation instanceof LivingEntity) {
            System.out.println("EntityManager : useItem() : Entity at " + newLocation + " is a living entity");
            LivingEntity targetLivingEntity = (LivingEntity) entityAtNewLocation;
        } else {
            LivingEntity targetLivingEntity = null;
        }
        switch (selectedItem.getTemplate().getType()) {
            case FOOD:
                entity.alterHealth(10);
                if (selectedItem != null) {
                    entity.getInventory().useItem(selectedItem);
                }
                break;
            case WEAPON:
                if (entityAtNewLocation instanceof LivingEntity) {
                    hitEntity(entity, direction);
                }
                if (selectedItem != null) {
                    entity.getInventory().useItem(selectedItem);
                }
                break;
            case FORESTKEY:
                if (entityAtNewLocation instanceof DecorEntity) {
                    if (((DecorEntity) entityAtNewLocation).getDecorType() == DecorType.FORESTDOOR) {
                        GlobalChat.addMessage("You have unlocked the door to the forest");
                        sceneManager.changeScene(MapType.FOREST);
                    }
                }
                break;
            case WIN:
                if (entity instanceof Player) {
                    GlobalChat.addMessage("You have won the game!");
                    sceneManager.changeScene(MapType.WIN);
                }
                break;
            default:
                if (entityAtNewLocation instanceof LivingEntity) {
                    hitEntity(entity, direction);
                }
                break;
        }
        movedEntities.add(entity);
        Entity directionEntity = getDirectionEntity(entity, direction);
        if (directionEntity != null && directionEntity instanceof LivingEntity) {
            movedEntities.add((LivingEntity) directionEntity);
        }

        notifyObservers();
    }

    /**
     * Uses a passive item on the entity, performing actions based on the item's type.
     * 
     * @param entity The entity using the passive item.
     */
    public void usePassiveItem(LivingEntity entity) {
        Item selectedItem = null;
        if (entity instanceof Player) {
            PlayerInventory playerInventory = (PlayerInventory) ((Player) entity).getInventory();
            selectedItem = playerInventory.getSelectedItem();
        } else {
            selectedItem = entity.getInventory().getItem(0, 0);
        }
        switch (selectedItem.getTemplate().getType()) {
            case ARMOR:
                entity.alterHealth(10);
                break;
            default:
                break;
        }
        movedEntities.add(entity);
        notifyObservers();
    }

    /**
     * Gets the new location based on the current location and direction.
     * 
     * @param location  The current location.
     * @param direction The direction to move.
     * @return The new location after moving in the specified direction.
     */
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

    /**
     * Checks if an entity can move to a specified location.
     * 
     * @param entity   The entity attempting to move.
     * @param location The location to move to.
     * @return True if the entity can move to the location, otherwise false.
     */
    public boolean canMoveTo(LivingEntity entity, Point2D location) {
        Entity targetEntity = entityMap.get(location);
        if (targetEntity == null) {
            System.out.println("EntityManager : canMoveTo() : No entity at " + location);
            return true;
        }
        if (targetEntity instanceof LivingEntity) {
            System.out.println("EntityManager : canMoveTo() : Entity at " + location + " is a living entity");
            LivingEntity targetLivingEntity = (LivingEntity) targetEntity;
            return entity.getStrength() > targetLivingEntity.getWeight();
        }
        return false;
    }

    /**
     * Gets the list of moved entities and clears the internal list.
     * 
     * @return A list of moved entities.
     */
    public List<LivingEntity> getMovedEntities() {
        List<LivingEntity> movedEntities = new ArrayList<>(this.movedEntities);
        this.movedEntities.clear();
        return movedEntities;
    }

    /**
     * Returns a string representation of the entity manager.
     * 
     * @return A string representation of the entity manager.
     */
    public String toString() {
        return entityMap.toString();
    }

    /**
     * Gets a list of all monsters managed by the entity manager.
     * 
     * @return A list of all monsters.
     */
    public List<Monster> getMonsters() {
        List<Monster> monsters = new ArrayList<>();
        for (Entity entity : entityMap.values()) {
            if (entity instanceof Monster) {
                monsters.add((Monster) entity);
            }
        }
        return monsters;
    }

    /**
     * Gets a list of all NPCs managed by the entity manager.
     * 
     * @return A list of all NPCs.
     */
    public List<NPC> getNPCs() {
        List<NPC> npcs = new ArrayList<>();
        for (Entity entity : entityMap.values()) {
            if (entity instanceof NPC) {
                npcs.add((NPC) entity);
            }
        }
        return npcs;
    }

    /**
     * Gets the location of a specified entity.
     * 
     * @param entity The entity whose location is to be found.
     * @return The location of the entity, or null if the entity is not found.
     */
    public Point2D getEntityLocation(Entity entity) {
        for (Map.Entry<Point2D, Entity> entry : entityMap.entrySet()) {
            if (entry.getValue().equals(entity)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
