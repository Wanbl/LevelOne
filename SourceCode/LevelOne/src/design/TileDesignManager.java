package design;

import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import coordinates.Point2D;
import entity.Entity;
import manager.EntityManager;
import entity.LivingEntity;
import entity.Monster;
import entity.NPC;
import entity.Player;

/**
 * The TileDesignManager class is responsible for creating and managing the design of tiles in the game.
 */
public class TileDesignManager {
    public EntityManager entityManager;

    /**
     * Constructs a TileDesignManager with the specified EntityManager.
     * 
     * @param entityManager The EntityManager to be used for managing entities on tiles.
     */
    public TileDesignManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Creates a StackPane representing a tile at the specified location with the given tile type.
     * 
     * @param tileType The type of the tile.
     * @param location The location of the tile.
     * @return The StackPane representing the tile.
     */
    public StackPane createTile(String tileType, Point2D location) {
        System.out.println("TILEDESIGNMANAGER : CREATETILE() : Creating tile at " + location);
        StackPane tile = new StackPane();
        ImageView tileImage = new ImageView();
        try {
            tileImage.setImage(new Image("file:data/templates/tiles/" + tileType + ".png"));
            System.out.println("TILEDESIGNMANAGER : CREATETILE() : Setting tile image to " + tileImage.getImage().getUrl());
        } catch (IllegalArgumentException e) {
            System.out.println("TILEDESIGNMANAGER : CREATETILE() : Tile does not exist");
            tileImage.setImage(new Image("file:data/templates/tiles/empty.png"));
        }
        tile.getChildren().add(tileImage);

        Entity entity = entityManager.getEntity(location);
        System.out.println("TILEDESIGNMANAGER : CREATETILE() : Checking for entity at " + location);
        System.out.println("TILEDESIGNMANAGER : CREATETILE() : EntityManager for the tile: " + entityManager);
        if (entity != null) {
            tile.getChildren().add(entity.getSpriteManager().getSprite());
            if (entity instanceof LivingEntity) {
                System.out.println("TILEDESIGNMANAGER : CREATETILE() : Entity is living");
                ProgressBar healthBar = new ProgressBar();
                double currentHealth = ((LivingEntity) entity).getHealth();
                double maxHealth = ((LivingEntity) entity).getMaxHealth();
                double progress = currentHealth / maxHealth;
                healthBar.setProgress(progress);
                System.out.println("TILEDESIGNMANAGER : CREATETILE() : Health: " + currentHealth + "/" + maxHealth);
                healthBar.getStyleClass().add("custom-progress-bar");
                if (entity instanceof Monster) {
                    healthBar.getStyleClass().add("monster-health-bar");
                } else if (entity instanceof NPC) {
                    healthBar.getStyleClass().add("npc-health-bar");
                } else if (entity instanceof Player) {
                    healthBar.getStyleClass().add("player-health-bar");
                }
                tile.getChildren().add(healthBar);
            } else {
                System.out.println("TILEDESIGNMANAGER : CREATETILE() : Entity is not living");
            }
            System.out.println("TILEDESIGNMANAGER : CREATETILE() : Added entity sprite to tile: " + entity.getType() + " at " + location);
        } else {
            System.out.println("TILEDESIGNMANAGER : CREATETILE() : No entity to display at " + location);
        }

        return tile;
    }
}
