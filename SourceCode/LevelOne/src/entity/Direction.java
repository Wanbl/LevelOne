package entity;

/**
 * The Direction enum represents the possible movement directions for entities in the game.
 */
public enum Direction {
    UP,    // Represents the upward direction
    DOWN,  // Represents the downward direction
    LEFT,  // Represents the leftward direction
    RIGHT; // Represents the rightward direction

    /**
     * Returns the opposite direction of the current direction.
     * 
     * @return The opposite direction.
     */
    public Direction opposite() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return UP;
        }
    }
}
