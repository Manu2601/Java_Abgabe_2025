package design_pattern.ships.models;

public class Field {
    private boolean isHit;
    private Position position;

    public Field(Position position) {
        this.isHit = false;
        this.position = position;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public Position getPosition() {
        return position;
    }
}
