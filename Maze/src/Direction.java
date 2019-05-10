public enum Direction {

    NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0);

    public Direction opposite() {
        return Direction.values()[(ordinal() + 2) % 4];

    }

    public final int x;
    public final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
