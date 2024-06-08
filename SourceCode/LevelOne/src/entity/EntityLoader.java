package entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import coordinates.CoordinatesLoader;
import manager.EntityManager;
import coordinates.Point2D;
import design.SpriteManager;
import design.EntitySprite;
import items.ItemTemplate;

/**
 * The EntityLoader class is responsible for loading entities from a file and adding them to the entity manager.
 */
public class EntityLoader {

    /**
     * Loads entities from a specified file and adds them to the entity manager.
     * 
     * @param filePath          The path to the file containing entity data.
     * @param coordinatesLoader The CoordinatesLoader for loading entity coordinates.
     * @param entityManager     The EntityManager to which entities will be added.
     */
    public void loadEntities(String filePath, CoordinatesLoader coordinatesLoader, EntityManager entityManager) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int y = 0; // Current row (y coordinate)
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                for (int x = 0; x < parts.length; x++) { // x is the column coordinate
                    String part = parts[x].trim();
                    if (!part.isEmpty()) {
                        processString(part, x, y, coordinatesLoader, entityManager);
                    }
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes a string representation of an entity and adds it to the entity manager.
     * 
     * @param str                The string representation of the entity.
     * @param x                  The x coordinate.
     * @param y                  The y coordinate.
     * @param coordinatesLoader  The CoordinatesLoader for loading entity coordinates.
     * @param entityManager      The EntityManager to which entities will be added.
     */
    private void processString(String str, int x, int y, CoordinatesLoader coordinatesLoader, EntityManager entityManager) {
        String[] elements = str.split(":");
        if (elements.length == 0) {
            return;
        }

        switch (elements[0]) {
            case "LivingEntity":
                processLivingEntity(elements, x, y, coordinatesLoader, entityManager);
                break;
            case "NonLivingEntity":
                processNonLivingEntity(elements, x, y, coordinatesLoader, entityManager);
                break;
            default:
                System.out.println("ENTITYLOADER : PROCESSSTRING() : Unknown entity type: " + elements[0] + " at (" + x + ", " + y + ")");
        }
    }

    /**
     * Processes a living entity and adds it to the entity manager.
     * 
     * @param elements           The elements of the entity string.
     * @param x                  The x coordinate.
     * @param y                  The y coordinate.
     * @param coordinatesLoader  The CoordinatesLoader for loading entity coordinates.
     * @param entityManager      The EntityManager to which entities will be added.
     */
    private void processLivingEntity(String[] elements, int x, int y, CoordinatesLoader coordinatesLoader, EntityManager entityManager) {
        if (elements.length < 2) {
            return;
        }

        switch (elements[1]) {
            case "NPC":
                processNPC(elements, x, y, coordinatesLoader, entityManager);
                break;
            case "Monster":
                processMonster(elements, x, y, coordinatesLoader, entityManager);
                break;
            default:
                System.out.println("ENTITYLOADER : PROCESSLIVINGENTITY() : Unknown LivingEntity subtype: " + elements[1] + " at (" + x + ", " + y + ")");
        }
    }

    /**
     * Processes an NPC and adds it to the entity manager.
     * 
     * @param elements           The elements of the entity string.
     * @param x                  The x coordinate.
     * @param y                  The y coordinate.
     * @param coordinatesLoader  The CoordinatesLoader for loading entity coordinates.
     * @param entityManager      The EntityManager to which entities will be added.
     */
    private void processNPC(String[] elements, int x, int y, CoordinatesLoader coordinatesLoader, EntityManager entityManager) {
        if (elements.length < 3) {
            return;
        }

        Point2D npcLocation = coordinatesLoader.getCoordinates(x, y);
        SpriteManager npcSpriteManager = new SpriteManager(EntitySprite.valueOf(elements[2]));
        NPC npc = new NPC(npcLocation, "npc", entityManager, npcSpriteManager, NPCType.valueOf(elements[3]));
        for (ItemTemplate itemTemplate : NPCType.valueOf(elements[3]).getInventoryItems()) {
            npc.getInventory().addItem(itemTemplate, true);
        }
        entityManager.addEntity(npc);
    }

    /**
     * Processes a monster and adds it to the entity manager.
     * 
     * @param elements           The elements of the entity string.
     * @param x                  The x coordinate.
     * @param y                  The y coordinate.
     * @param coordinatesLoader  The CoordinatesLoader for loading entity coordinates.
     * @param entityManager      The EntityManager to which entities will be added.
     */
    private void processMonster(String[] elements, int x, int y, CoordinatesLoader coordinatesLoader, EntityManager entityManager) {
        if (elements.length < 3) {
            return;
        }

        Point2D monsterLocation = coordinatesLoader.getCoordinates(x, y);
        SpriteManager monsterSpriteManager = new SpriteManager(EntitySprite.valueOf(elements[2]));
        Monster monster = new Monster(monsterLocation, "monster", entityManager, monsterSpriteManager);
        for (ItemTemplate itemTemplate : MonsterType.valueOf(elements[3]).getInventoryItems()) {
            monster.getInventory().addItem(itemTemplate, true);
        }
        entityManager.addEntity(monster);
    }

    /**
     * Processes a non-living entity and adds it to the entity manager.
     * 
     * @param elements           The elements of the entity string.
     * @param x                  The x coordinate.
     * @param y                  The y coordinate.
     * @param coordinatesLoader  The CoordinatesLoader for loading entity coordinates.
     * @param entityManager      The EntityManager to which entities will be added.
     */
    private void processNonLivingEntity(String[] elements, int x, int y, CoordinatesLoader coordinatesLoader, EntityManager entityManager) {
        if (elements.length < 2) {
            return;
        }

        switch (elements[1]) {
            case "ItemEntity":
                processItemEntity(elements, x, y, coordinatesLoader, entityManager);
                break;
            case "DecorEntity":
                processDecorEntity(elements, x, y, coordinatesLoader, entityManager);
                break;
            default:
                System.out.println("ENTITYLOADER : PROCESSNONLIVINGENTITY() : Unknown NonLivingEntity subtype: " + elements[1] + " at (" + x + ", " + y + ")");
        }
    }

    /**
     * Processes an item entity and adds it to the entity manager.
     * 
     * @param elements           The elements of the entity string.
     * @param x                  The x coordinate.
     * @param y                  The y coordinate.
     * @param coordinatesLoader  The CoordinatesLoader for loading entity coordinates.
     * @param entityManager      The EntityManager to which entities will be added.
     */
    private void processItemEntity(String[] elements, int x, int y, CoordinatesLoader coordinatesLoader, EntityManager entityManager) {
        if (elements.length < 4) {
            return;
        }

        Point2D itemLocation = coordinatesLoader.getCoordinates(x, y);
        SpriteManager itemSpriteManager = new SpriteManager(EntitySprite.valueOf(elements[2]));
        ItemEntity item = new ItemEntity(itemLocation, ItemTemplate.valueOf(elements[3]), Integer.parseInt(elements[4]));
        entityManager.addEntity(item);
    }

    /**
     * Processes a decor entity and adds it to the entity manager.
     * 
     * @param elements           The elements of the entity string.
     * @param x                  The x coordinate.
     * @param y                  The y coordinate.
     * @param coordinatesLoader  The CoordinatesLoader for loading entity coordinates.
     * @param entityManager      The EntityManager to which entities will be added.
     */
    private void processDecorEntity(String[] elements, int x, int y, CoordinatesLoader coordinatesLoader, EntityManager entityManager) {
        if (elements.length < 3) {
            return;
        }

        Point2D decorLocation = coordinatesLoader.getCoordinates(x, y);
        SpriteManager decorSpriteManager = new SpriteManager(EntitySprite.valueOf(elements[2]));
        DecorEntity decor = new DecorEntity(decorLocation, 1, "decor", decorSpriteManager, DecorType.valueOf(elements[2]));
        entityManager.addEntity(decor);
    }
}
