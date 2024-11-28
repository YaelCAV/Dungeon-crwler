package main.entities.entitiesEnums;

public enum Decorations {
    GRASS1(0,160),
    GRASS2(16,160),
    GRASS3(32,160),
    GRASS4(48,160),
    GRASS5(64,160),
    GRASS6(80,160),
    GRASS7(96,160),

    LOG1(64,128),
    LOG2(80,128),

    FLOWER1(0,176),
    FLOWER2(16,176),
    FLOWER3(48,176),

    CLOVER(32,176),

    TALLGRASS1(64,176),
    TALLGRASS2(80,176)
    ;
    private final int x;
    private final int y;

    Decorations(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

