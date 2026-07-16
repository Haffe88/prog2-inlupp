package se.su.inlupp;

import javafx.scene.shape.Line;

public class ConnectionLine extends Line {

//Hade hellre döpt den till bara "Line" men den klassen finns redan i JavaFx.

    private final Location from;
    private final Location to;

    public ConnectionLine(Location from, Location to){
        this.from = from;
        this.to = to;

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
