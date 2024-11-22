public enum Direction {
    NORTH(3),
    SOUTH(1),
    WEST(2),
    EAST(4);
    private final int frameLineNumber;
    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }
    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}

