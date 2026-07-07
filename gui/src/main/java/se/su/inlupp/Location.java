package se.su.inlupp;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Location extends StackPane {
    private String name;
    private double x;
    private double y;


    public Location(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;

        relocate(x, y);

        Circle locationSymbol = new Circle(7);
        locationSymbol.setFill(Color.WHITE);

        Label locationName = new Label(name);

        VBox struktur = new VBox(2, locationSymbol, locationName);
        struktur.setAlignment(Pos.CENTER);

        //Vboxen ser till att cirkeln på kartan samt texten hamnar rätt.

        getChildren().add(struktur);

        //Lägger till namn och cirkel i Stackpanen/Location - för att symbolisera en plats

    }

    public String getName() {
        return name;
    }

}
