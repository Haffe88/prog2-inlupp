package se.su.inlupp;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class Location extends StackPane {
    private String name;
    private double x;
    private double y;

    private double startX;
    private double startY;
//Instansvariabler - för tillgång hos DragHandler

    public Location(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;

        relocate(x, y);    //"Placera mig på koordinaterna (x,y) relativt min förälder". Skapar layoutX/layoutY, är det avgörande för platsen inte private double x och y.

        Circle locationSymbol = new Circle(7);
        locationSymbol.setFill(Color.WHITE);

        Label locationName = new Label(name);

        VBox struktur = new VBox(2, locationSymbol, locationName);
        struktur.setAlignment(Pos.CENTER);

        //Vboxen ser till att cirkeln på kartan samt texten hamnar rätt.

        getChildren().add(struktur);

        //Lägger till namn och cirkel i Stackpanen/Location - för att symbolisera en plats


        setOnMousePressed(new StartDragHandler());
        //Metoden kopplar händelsehanterare till Location när man trycker på musen på den
        setOnMouseDragged(new DragHandler());
        //Metoden kopplar händelsehanterare till Location när man drar musen över den, förflyttar location
    }

    public String getName() {
        return name;
    }

    class StartDragHandler implements EventHandler<MouseEvent>{

        public void handle(MouseEvent mouseEvent){
            startX = mouseEvent.getX();
            startY = mouseEvent.getY();

        }
    }

    //StartDragHandler - tar reda på var Location befann sig från början (var vi klickar på den), se instansvariabler - för tillgång i DragHandler

    @Override
    public String toString(){
        return name;
    }

    // Lagt till en ToString här så att det skrivs ut rätt när man ska hitta en väg.






    class DragHandler implements EventHandler<MouseEvent>{

        public void handle(MouseEvent mouseEvent){
            double newX = getLayoutX() + mouseEvent.getX() - startX;
            double newY = getLayoutY() + mouseEvent.getY() - startY;
            relocate(newX, newY);

        }

    }

    //Drag och StartHandler - baserade på föreläsningen F14 (låt denna kommentar stå kvar)

    //DragHandler för att kunna dra runt location.
    //Får in Mouseevent - räkna ut ny X och Y utifrån layouten(var vi befann oss), och mouseEventets x/y (rörelsen som musen gjorde - som gav eventet)
    //Instansvariabler startX/Y (från StartDragHandler) dras av i uträkningen (för att den inte ska hoppa till när man först tar tag i Location)
}

