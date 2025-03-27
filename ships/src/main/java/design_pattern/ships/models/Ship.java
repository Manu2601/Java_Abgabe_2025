package design_pattern.ships.models;

import java.util.List;

public class Ship {
    private List<ShipPart> parts;

    public Ship(List<ShipPart> parts) {
        this.parts = parts;
    }

    public List<ShipPart> getParts() {
        return parts;
    }
}
