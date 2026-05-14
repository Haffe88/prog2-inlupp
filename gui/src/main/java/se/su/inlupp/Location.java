package se.su.inlupp;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Location extends StackPane {
    private String name;
    private double x;
    private double y;

    public Location(String name, double x, double y){
        this.name = name;
        relocate(x, y);
        Circle locationSymbol = new Circle(7);
        Label locationName = new Label(name);
        locationSymbol.setFill(Color.WHITE);
        getChildren().addAll(locationName, locationSymbol);
        //Lägger till namn och cirkel i Stackpanen/Location - för att symbolisera en plats
    }
}
