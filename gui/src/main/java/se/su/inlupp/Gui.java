package se.su.inlupp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class Gui extends Application {


  private Stage stage;
  private FileChooser fileChooser = new FileChooser();
  private ImageView backgroundMapView;
  private Pane center;
  private boolean removeMode = false;
  private boolean connectMode = false;
  private Location firstSelected = null;
  private Location secondSelected = null;

  private Graph<Location> graph = new ListGraph<>();

  public void start(Stage stage) {
    this.stage = stage;

   /*
    Graph<String> graph = new ListGraph<String>();
    String javaVersion = System.getProperty("java.version");
    String javafxVersion = System.getProperty("javafx.version");
    Label label =
        new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

    VBox root = new VBox(30, label);
    root.setAlignment(Pos.CENTER);

    */
    BorderPane root = new BorderPane();

    //Skapa borderpane

    MenuBar menuBar = new MenuBar();
    Menu menu = new Menu("Meny");
    menuBar.getMenus().add(menu);

    MenuItem newFile = new MenuItem("Ny");

    MenuItem openFile = new MenuItem("Öppna");
    openFile.setOnAction(new OpenFileHandler());

    MenuItem loadBackground = new MenuItem("Ladda in bild");
    loadBackground.setOnAction(new LoadBackgroundHandler());

    MenuItem saveFile = new MenuItem("Spara");
    saveFile.setOnAction(new SaveFileHandler());

    MenuItem quit = new MenuItem("Avsluta");

    menu.getItems().addAll(newFile, openFile, loadBackground, saveFile, quit);

    //Menyn med alternativen i menyn - samt setOnAction

    Button addLocation = new Button("Lägg till plats");
    addLocation.setOnAction(new AddLocationHandler());

    Button removeLocation = new Button("Ta bort plats");
    removeLocation.setOnAction (new DeleteButtonHandler());

    Button connectLocations = new Button("Ange väg mellan platser");
    connectLocations.setOnAction (new ConnectButtonHandler());


    Button findPath = new Button("Hitta väg");

    //Knappar i flowpane (bottom)



    VBox top = new VBox(0, menuBar);
    FlowPane bottom = new FlowPane(addLocation, removeLocation, connectLocations, findPath);


    backgroundMapView = new ImageView();
    center = new Pane();
    center.getChildren().add(backgroundMapView);

    //använd pos och setposition för att centrera den

    // Har bytt ut StackPane mot Pane för StackPane centrerar sina barn automatiskt så jag kan inte placera ut locations på olika ställen.


    root.setTop(top);
    root.setBottom(bottom);
    root.setCenter(center);
    bottom.setAlignment(Pos.CENTER);
    bottom.setHgap(20);
    bottom.setPadding(new Insets(10));

    //Sätta delarna i Borderpanen, sätta alignment för knapparna i bottom, samt padding 10 runt varje knapp, 20 i gap mellan knappar


    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.show();

    //Skapa och visa scenen

    backgroundMapView.fitWidthProperty().bind(center.widthProperty());
    backgroundMapView.fitHeightProperty().bind(center.heightProperty());

    //uppdaterar backgroundMapViews bredd och höjd, binder till center bredd och höjd
  }

  private class OpenFileHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent arg0){
      fileChooser.setInitialDirectory(new File("."));
      File fileToOpen = fileChooser.showOpenDialog(stage);
      System.out.println(fileToOpen);

    }
  }

  //Hanterare för att öppna en fil, sätta in funktionalitet här så filen faktiskt kan öppnas

  private class SaveFileHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent arg0){
      File fileToSave = fileChooser.showOpenDialog(stage);
      System.out.println(fileToSave);
    }
  }

  //Hanterare för att spara en fil, , sätta in funktionalitet här så filen faktiskt kan öppnas

  private class LoadBackgroundHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent arg0){
      fileChooser.setInitialDirectory(new File("."));
      File pictureToLoad = fileChooser.showOpenDialog(stage);

      if (pictureToLoad != null){
        Image image = new Image(pictureToLoad.toURI().toString());
        backgroundMapView.setImage(image);
      }
    }
  }

  //Hanterare för att ladda in en bild till bakgrunden


  private class AddLocationHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      NewLocationForm newLocationform = new NewLocationForm();
      newLocationform.showAndWait().ifPresent(locationName -> {
        center.setOnMouseClicked(new ClickHandler(locationName));
      });
    }
  }

  //Började försöka fixa popupfönster till addLocation-knappen men fastnade


  class ClickHandler implements EventHandler<MouseEvent>{

      private String locationName;

      public ClickHandler(String locationName) {
        this.locationName = locationName;
      }

        @Override
          public void handle(MouseEvent event) {
            double x = event.getX();
            double y = event.getY();

            Location location = new Location(locationName, x,y);      //Här skapas en location

            location.setOnMouseClicked(e -> {              //Här berättar jag för location att den ska reagera på musklick, men bara om
              if (removeMode){                                        //removemode är sant, vilket sätts av ta bort knappen.
                new DeleteLocationHandler(location).handle(e);
              }
              if (connectMode){
                new ConnectLocationHandler(location).handle(e);
              }
                    });

            graph.add(location);                                        //Denna gör att location läggs in i ListGraph-klassen.
            center.getChildren().add(location);

            center.setOnMouseClicked(null);
          }
  }
        // Denna eventhandler aktiveras bara om det finns ett namn från NewLocationForm, det är eventhandlern AddLocationHandler
        // som styr den logiken. Aktiveras den så tar den namnet som skrivits in tillsammans med koordinaterna där vi tryckt och skapar en location. Location i sin
        // tur placerar ut sig på kartan utifrån koordinaterna med cirkel och namn. Location läggs även till i ListGraph. Notera dock att den gör en massa mer sen, den berättar vad
        // som händer om man trycker om en location om olika knappar har tryckts innan.


  class DeleteLocationHandler implements EventHandler<MouseEvent> {

    private Location location;

    public DeleteLocationHandler(Location location){
      this.location = location;
    }
    @Override
    public void handle(MouseEvent event){
      DeleteLocationForm deleteLocationForm = new DeleteLocationForm(location);
      deleteLocationForm.showAndWait().ifPresent(result -> {
        if (result){
          graph.remove(location);
          center.getChildren().remove(location);
          removeMode = false;
      }
      });
    }
  }

  // Denna aktiveras av ett musklick på en plats man lagt till, den skapar först en DeleteLocationForm som det finns en egen klass för
  // Det är en meny där man får frågan om man verkligen vill ta bort ett objekt. Vill man det så får man tillbaka en boolean true (Alltså if (result))
  // Då tar den bort platsen i graf och i gui.

  class ConnectLocationHandler implements EventHandler<MouseEvent> {

    private Location location;

    public ConnectLocationHandler(Location location){
      this.location = location;
    }

    @Override
    public void handle(MouseEvent event){
      if (firstSelected == null){
        firstSelected = location;
        return;
      }
      if (secondSelected == null && location != firstSelected){
        secondSelected = location;

        // Ovan påminner om memory-spelet när två kort man tryck på ska användas men denna är enklare. Vad denna eventhandler gör är att koppla ihop två platser.


        ConnectLocationForm connectLocationForm = new ConnectLocationForm();      //skapar ett objekt av klassen och väntar sedan på resultat.
        connectLocationForm.showAndWait().ifPresent(result -> {

          graph.connect(firstSelected,secondSelected,connectLocationForm.getConnectionName(), connectLocationForm.getConnectionWeight()   // .connect ligger i ListGraph

          );

        // En metod kan inte returnera två stängar så det är lite svårare än tex NewLocationForm. Här hämtar vi instasvariablerna från klassen i stället, med
        // sånt som getConnectionName(). Det går att skapa ett nytt objekt för det vi behöver också men då behöver vi en ny klass.

          Line line = new Line(
                  firstSelected.getLayoutX(),
                  firstSelected.getLayoutY(),
                  secondSelected.getLayoutX(),
                  secondSelected.getLayoutY()
          );

          center.getChildren().add(line);

          //Den övre koden är för att rita ut en linje.

        });
      firstSelected = null;
      secondSelected = null;
      connectMode = false;
      }

      //allt måste nollställas så att vi kan lägga till fler kopplingar.

    }

  }

  private class DeleteButtonHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent event) {

      removeMode = true;
    }
  }
  // Det den här eventhandlern gör är bara att sätta en variabel "removeMode" till true så, den aktiviteras om man trycker
  // på "ta bort"-knappen och det är det enda "ta bort" knappen gör. När vi skapar en location så säger vi sedan till location
  // att om removeMode är "true" då får du aktivivera den kedja av händelser som gör att en location tas bort.
  // Själva deleteLocationHandler sätts på objekten redan i skapandet av dem.

  private class ConnectButtonHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {

      connectMode = true;
    }
  }
  //Den här är samma som metoden över fast för att connecta platser. De påverkar saker i Clickhandler.



  public static void main (String[]args){
        launch(args);
      }
    }