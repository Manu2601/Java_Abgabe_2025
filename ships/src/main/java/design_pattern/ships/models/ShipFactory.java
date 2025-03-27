package design_pattern.ships.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShipFactory {
    private static final Random rand = new Random();

    public static Ship create(int len, Playground playground) {
        boolean placed = false;
        Ship ship = null;
        while (!placed) {
            int x = rand.nextInt(10);
            int y = rand.nextInt(10);
            boolean horizontal = rand.nextBoolean();
            
            List<ShipPart> parts = new ArrayList<>();
            boolean valid = true;
            for (int i = 0; i < len; i++) {
                int newX = horizontal ? x + i : x;
                int newY = horizontal ? y : y + i;
                
                if (newX >= 10 || newY >= 10 || !shipPossible(newX, newY, playground)) {
                    valid = false;
                    break;
                }
                parts.add(new ShipPart(new Position(newX, newY, horizontal)));
            }
            
            if (valid) {
                ship = new Ship(parts);
                playground.placeShip(ship);
                placed = true;
            }
        }
        return ship;
    }

    private static boolean shipPossible(int x, int y, Playground playground) {
        if (playground.grid[x][y] != null) return false;
        
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int nx = x + dx, ny = y + dy;
                if (nx >= 0 && ny >= 0 && nx < 10 && ny < 10 && playground.grid[nx][ny] instanceof ShipPart) {
                    return false;
                }
            }
        }
        return true;
    }
}
