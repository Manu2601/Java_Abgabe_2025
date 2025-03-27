package design_pattern.ships.models;

public class Position {
    private int x;
    private int y;
    private boolean horizontal;

    public Position(int x, int y, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHorizontal() {
        return horizontal;
    }
}
