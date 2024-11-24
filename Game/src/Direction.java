public enum Direction {
    NORTH(2),
    SOUTH(1),
    WEST(3),
    EAST(4);
    private final int frameLineNumber;
    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }
    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}

