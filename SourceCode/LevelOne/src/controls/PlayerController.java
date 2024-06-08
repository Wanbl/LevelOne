package controls;

import entity.Direction;
import entity.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import design.SpriteManager;
import chat.GlobalChat;

/**
 * The PlayerController class handles player movement and actions based on keyboard input.
 */
public class PlayerController {
    private Player player;
    private SpriteManager spriteManager;

    /**
     * Constructor for PlayerController.
     * 
     * @param player        The player entity to control.
     * @param spriteManager The sprite manager for the player.
     */
    public PlayerController(Player player, SpriteManager spriteManager) {
        this.player = player;
        player.setDirection(Direction.DOWN);
        this.spriteManager = spriteManager;
    }

    /**
     * Returns an EventHandler for handling key events for player movement and actions.
     * 
     * @return The EventHandler for key events.
     */
    public EventHandler<KeyEvent> getMovementHandler() {
        return event -> {
            Direction direction = null;
            switch (event.getCode()) {
                case Z:
                    direction = Direction.UP;
                    player.setDirection(Direction.UP);
                    break;
                case S:
                    direction = Direction.DOWN;
                    player.setDirection(Direction.DOWN);
                    break;
                case Q:
                    direction = Direction.LEFT;
                    player.setDirection(Direction.LEFT);
                    break;
                case D:
                    direction = Direction.RIGHT;
                    player.setDirection(Direction.RIGHT);
                    break;
                case E:
                    player.getEntityManager().useItem(player, player.getDirection());
                    break;
                default:
                    direction = Direction.DOWN;
                    player.setDirection(Direction.DOWN);
                    break;
            }
            if (direction != null) {
                player.move(direction);
                System.out.println("PLAYERCONTROLLER : GETMOVEMENTHANDLER() : Player moved " + direction + " to " + player.getLocation() + " with sprite " + spriteManager.getSprite().getImage().getUrl());
            }
        };
    }
}
