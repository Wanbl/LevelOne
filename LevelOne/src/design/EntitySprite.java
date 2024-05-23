package design;

import entity.Direction;

public enum EntitySprite {
    PLAYER("data/templates/sprites/player/up.png", "data/templates/sprites/player/down.png", "data/templates/sprites/player/left.png", "data/templates/sprites/player/right.png"),
    ZOMBIE("data/templates/sprites/zombie/up.png", "data/templates/sprites/zombie/down.png", "data/templates/sprites/zombie/left.png", "data/templates/sprites/zombie/right.png"),
    BADGUY("data/templates/sprites/badguy/up.png", "data/templates/sprites/badguy/down.png", "data/templates/sprites/badguy/left.png", "data/templates/sprites/badguy/right.png"),
    FIRSTNPC("data/templates/sprites/firstnpc/up.png", "data/templates/sprites/firstnpc/down.png", "data/templates/sprites/firstnpc/left.png", "data/templates/sprites/firstnpc/right.png"),;

    private String upSpritePath;
    private String downSpritePath;
    private String leftSpritePath;
    private String rightSpritePath;

    private EntitySprite(String upSpritePath, String downSpritePath, String leftSpritePath, String rightSpritePath) {
        this.upSpritePath = upSpritePath;
        this.downSpritePath = downSpritePath;
        this.leftSpritePath = leftSpritePath;
        this.rightSpritePath = rightSpritePath;
    }

    public String getSpritePath(Direction direction) {
        switch (direction) {
            case UP:
                return upSpritePath;
            case DOWN:
                return downSpritePath;
            case LEFT:
                return leftSpritePath;
            case RIGHT:
                return rightSpritePath;
            default:
                return downSpritePath;
        }
    }
}
