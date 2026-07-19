package se.su.inlupp;

import javafx.scene.control.Label;
import javafx.scene.shape.Line;

public class ConnectionLine extends Line {

//Hade hellre döpt den till bara "Line" men den klassen finns redan i JavaFx.

    private final Location from;
    private final Location to;
    private String name;
    private int length;
    private Label lineLabel = new Label();

    public ConnectionLine(String name, int length, Location from, Location to){
        this.from = from;
        this.to = to;
        this.name = name;
        this.length = length;

        lineLabel.setText(name + " (" + length + ")");
        //sätter label med namn och vikt för linjen
        //Nu tar konstruktorn även in namn och vikt för linjen


        update();

        from.layoutXProperty().addListener((obs, oldValue, newValue) -> update());
        from.layoutYProperty().addListener((obs, oldValue, newValue) -> update());

        to.layoutXProperty().addListener((obs, oldValue, newValue) -> update());
        to.layoutYProperty().addListener((obs, oldValue, newValue) -> update());

    }

    // De där "layoutXProperty" är metod från Node som hämtar en property från Location som kan se om den förändras.Sen lyssnar
    // vi till förändringar och då ser metoden för lyssnaren ut så. De där tre värderna är inte viktiga men vi vill veta om nåt förändras och
    // en "changelistener" ser ut så.

    public void update(){
        setStartX(from.getLayoutX());
        setStartY(from.getLayoutY());

        setEndX(to.getLayoutX());
        setEndY(to.getLayoutY());

        double midX = (getStartX() + getEndX()) /2;
        double midY = (getStartY() + getEndY()) /2;
        //Hittar mitten av linjen (x och y) via getStart och end)

        lineLabel.setLayoutX(midX);
        lineLabel.setLayoutY(midY);
        //gör att labeln flyttas med linjen när linjen flyttas i layouten

    }

    public Label getLabel(){
        return lineLabel;
    }

  // Notera att getLayoutX() också är den metod vi använder i DragHandler i Location. Det där som heter
  // exempelvis "SetStartX" är metoder från Line-klassen. Linjen skapas här och till update kommer man både vid
  // skapandet av klassen samt från lyssnaren.

    public boolean connects (Location location) {
        return from == location || to == location;
    }

    // Den här metoden finns för att kunna svara på en om en viss location har en linje för då ska linjen plockas bort.
    // metoden används när platser tas bort och då går man igenom listan med linjer och ser om någon av dem har den location
    // som plockas bort på sig. Det görs i DeleteLocationHandler.

}
