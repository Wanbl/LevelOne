package manager;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import entity.Monster;
import entity.NPC;
import entity.Direction;
import java.util.List;
import entity.LivingEntity;

/**
 * The RandomMovementManager class handles the random movement of entities such as monsters and NPCs within the game.
 */
public class RandomMovementManager {
    private EntityManager entityManager;
    private Random random = new Random();
    
    /**
     * Constructs a RandomMovementManager with the specified EntityManager.
     * 
     * @param entityManager The EntityManager that manages the entities.
     */
    public RandomMovementManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    /**
     * Starts the random movement for monsters and NPCs.
     */
    public void startRandomMovement() {
        Timeline timelineMonsters = new Timeline(new KeyFrame(Duration.seconds(1), event -> moveMonsterEntities()));
        timelineMonsters.setCycleCount(Timeline.INDEFINITE);
        timelineMonsters.play();
        Timeline timelineNPCs = new Timeline(new KeyFrame(Duration.seconds(1), event -> moveNPCs()));
        timelineNPCs.setCycleCount(Timeline.INDEFINITE);
        // timelineNPCs.play();
    }
    
    /**
     * Moves the monster entities in random directions.
     */
    private void moveMonsterEntities() {
        List<Monster> monsters = entityManager.getMonsters();
        for (Monster monster : monsters) {
            Direction direction = getRandomDirection();
            entityManager.moveEntity(monster, direction);
            scheduleNextMove(monster);
        }
    }
    
    /**
     * Moves the NPC entities in random directions.
     */
    private void moveNPCs() {
        List<NPC> npcs = entityManager.getNPCs();
        for (NPC npc : npcs) {
            Direction direction = getRandomDirection();
            entityManager.moveEntity(npc, direction);
            scheduleNextMove(npc);
        }
    }
    
    /**
     * Gets a random direction for entity movement.
     * 
     * @return A random Direction.
     */
    private Direction getRandomDirection() {
        int randomDirection = random.nextInt(4);
        switch (randomDirection) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.LEFT;
            case 3:
                return Direction.RIGHT;    
            default:
                return Direction.DOWN;
        }
    }
    
    /**
     * Schedules the next move for a given entity after a random delay.
     * 
     * @param entity The entity to move.
     */
    private void scheduleNextMove(LivingEntity entity) {
        int delay = random.nextInt(5) + 1;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(delay), event -> {
            Direction direction = getRandomDirection();
            entity.move(direction);
            scheduleNextMove(entity);
        }));
        // timeline.play();  // Uncomment this line if the schedule needs to be played immediately
    }
}
