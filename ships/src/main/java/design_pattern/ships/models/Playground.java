package design_pattern.ships.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Playground extends Observable {
    private int size;
    Field[][] grid;
    private List<Ship> ships;

    public Playground(int size) {
        this.size = size;
        this.grid = new Field[size][size];
        this.ships = new ArrayList<>();
    }

    public boolean shootAt(int x, int y) {
        if (grid[x][y] instanceof ShipPart) {
            grid[x][y].setHit(true);
            setChanged(); 
            notifyObservers(new int[]{x, y, 1}); 
            return true;
        } else {
            setChanged();
            notifyObservers(new int[]{x, y, 0}); 
            return false;
        }
    }

    public boolean allShipsSunk() {
        return ships.stream().allMatch(ship -> ship.getParts().stream().allMatch(ShipPart::isHit));
    }

    public void placeShip(Ship ship) {
        ships.add(ship);
        for (ShipPart part : ship.getParts()) {
            grid[part.getPosition().getX()][part.getPosition().getY()] = part;
        }
    }
}
