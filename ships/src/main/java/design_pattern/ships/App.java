package design_pattern.ships;

import design_pattern.ships.models.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class App extends Application implements Observer {
    private Playground playground;
    private List<Ship> ships;
    private Button[][] buttons;
    private static final int SIZE = 10;

    public App() {
        this.playground = new Playground(SIZE);
        this.ships = new ArrayList<>();
        playground.addObserver(this); 
    }

    @Override
    public void start(Stage primaryStage) {
        setupGame();
        primaryStage.setTitle("Schiffe versenken");
        GridPane grid = new GridPane();
        buttons = new Button[SIZE][SIZE];

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                Button btn = new Button();
                btn.setMinSize(40, 40);
                int finalX = x;
                int finalY = y;
                btn.setOnAction(e -> playground.shootAt(finalX, finalY)); 
                buttons[x][y] = btn;
                grid.add(btn, x, y);
            }
        }

        primaryStage.setScene(new Scene(grid, 400, 400));
        primaryStage.show();
    }

    public void setupGame() {
        System.out.println("Schiffe werden platziert...");
        ships.add(ShipFactory.create(5, playground)); // Schlachtschiff
        ships.add(ShipFactory.create(4, playground)); // Kreuzer
        ships.add(ShipFactory.create(4, playground));
        ships.add(ShipFactory.create(3, playground)); // Zerstörer
        ships.add(ShipFactory.create(3, playground));
        ships.add(ShipFactory.create(3, playground));
        ships.add(ShipFactory.create(2, playground)); // U-Boote
        ships.add(ShipFactory.create(2, playground));
        ships.add(ShipFactory.create(2, playground));
        ships.add(ShipFactory.create(2, playground));
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof int[]) {
            int[] data = (int[]) arg;
            int x = data[0];
            int y = data[1];
            boolean hit = data[2] == 1;
            buttons[x][y].setStyle(hit ? "-fx-background-color: red;" : "-fx-background-color: blue;");

            if (playground.allShipsSunk()) {
                System.out.println("Alle Schiffe wurden versenkt! Spiel vorbei.");
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
