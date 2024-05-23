package controls;

import entity.Direction;
import entity.Player;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import design.SpriteManager;

public class PlayerController {
	private Player player;
	private SpriteManager spriteManager;
	
	public PlayerController(Player player, SpriteManager spriteManager) {
		this.player = player;
		this.spriteManager = spriteManager;
	}
	
	public EventHandler<KeyEvent> getMovementHandler() {
		return event -> {
			Direction direction = null;
			switch (event.getCode()) {
				case UP:
					direction = Direction.UP;
					break;
				case DOWN:
					direction = Direction.DOWN;
					break;
				case LEFT:
					direction = Direction.LEFT;
					break;
				case RIGHT:
					direction = Direction.RIGHT;
					break;
				default:
					direction = Direction.DOWN;
					break;
			}
			if (direction != null) {
//				spriteManager.updateSprite(direction);
				player.move(direction);
				System.out.println("Player moved " + direction + " to " + player.getLocation() + " with sprite " + spriteManager.getSprite().getImage().getUrl());
			}
		};
	}
}
