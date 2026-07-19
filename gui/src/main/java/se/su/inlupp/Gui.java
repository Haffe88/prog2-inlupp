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
import javafx.stage.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.util.Optional;

public class Gui extends Application {

  //Julia testkommentar

  private Stage stage;
  private FileChooser fileChooser = new FileChooser();
  private ImageView backgroundMapView;
  private Pane center;
  private boolean removeMode = false;
  private boolean connectMode = false;

  private Location firstSelected = null;
  private Location secondSelected = null;
  // Dessa två är är för metoden som connectar

  private boolean findPathMode = false;
  private Location pathStart = null;
  private Location pathEnd = null;

  // De här är för att hitta en väg

  private Boolean hasChanges = false;
  //boolsk flagga för att se om programmet har ändrats eller ej (används i meny-alterantiven sen)

  private final List<ConnectionLine> connectionLines = new ArrayList<>();
  // Lista med linjer för att vi ska kunna ta bort dem när en location försvinner.

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
    quit.setOnAction(new ExitHandler());
    //ExitHandler anropas även ifall vi väljer alternativet avsluta i menyn

    menu.getItems().addAll(newFile, openFile, loadBackground, saveFile, quit);

    //Menyn med alternativen i menyn - samt setOnAction

    Button addLocation = new Button("Lägg till plats");
    addLocation.setOnAction(new AddLocationHandler());

    Button removeLocation = new Button("Ta bort plats");
    removeLocation.setOnAction (new DeleteButtonHandler());

    Button connectLocations = new Button("Ange väg mellan platser");
    connectLocations.setOnAction (new ConnectButtonHandler());

    Button findPath = new Button("Hitta väg");
    findPath.setOnAction (new FindPathButtonHandler());


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


    stage.setOnCloseRequest(new WindowExitHandler());
    //om man försöker stänga fönstret innan man sparar - så anropas ExitHandler


    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.show();

    //Skapa och visa scenen

    backgroundMapView.fitWidthProperty().bind(center.widthProperty());
    backgroundMapView.fitHeightProperty().bind(center.heightProperty());

    //uppdaterar backgroundMapViews bredd och höjd, binder till center bredd och höjd
  }

  private class OpenFileHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      fileChooser.setInitialDirectory(new File("."));
      File fileToOpen = fileChooser.showOpenDialog(stage);
      try{
        FileReader fileReader = new FileReader(fileToOpen);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //Tänker att filen kan läsas från som en CSV samt med filsökvägen/filnamnet till bilden som användaren laddade in i programmet
        // (comma separated file, eftersom vi inte får sparas om binärfil) där varje objekt (med nod,nodnamn, edges med namn och vikt - komma, mellan varje) sparas i en arraylist??, och hur ska det läsas in i programmet?
        //bufferedReader.close();

      } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        alert.showAndWait();
      }

      hasChanges = false;


      //System.out.println(fileToOpen);

    }
  }

  //Hanterare för att öppna en fil, sätta in funktionalitet här så filen faktiskt kan öppnas
  //Skapar alert-fönster med error-typ när vi väljer en fil som är fel.
  //hasChanges - sätts till false, inga ändringar när vi öppnar en fil
  //Kod från föreläsning F16,F7

  private class SaveFileHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent arg0) {
      File fileToSave = fileChooser.showOpenDialog(stage);
      try {
        FileWriter fileWriter = new FileWriter(fileToSave);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        //Tänker att filen kan sparas som en CSV samt med filsökvägen/filnamnet till bilden som användaren laddade in i programmet
        // (comma separated file, eftersom vi inte får sparas om binärfil) där varje objekt (med nod,nodnamn, edges med namn och vikt - komma, mellan varje) sparas i en arraylist??, och hur ska det läsas in i programmet?
        // bufferedReader.close();
        //bufferedWriter.close();
      } catch (IOException e) {
        e.printStackTrace();
      }

      hasChanges = false;
      // System.out.println(fileToSave);
    }
  }

  //Hanterare för att spara en fil, , sätta in funktionalitet här så filen faktiskt kan öppnas
  //hasChanges - sätts till false, inga ändringar när vi sparar en fil
  //Kod från föreläsning F16, F7


  private class ExitHandler implements EventHandler<ActionEvent>{
  public void handle(ActionEvent event){
    if(hasChanges){
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setContentText("Du har osparade ändringar i programmet. Är du säker på att du vill avsluta?");
      Optional<ButtonType> clicked = alert.showAndWait();
      if (clicked.isPresent() && clicked.get().equals(ButtonType.CANCEL)){
        return;
      }
    }
    stage.close();
  }

  }
  //Hanterare för när man väljer alternativet Avsluta i menyn (varningsdialogruta vid osparade ändringar)
  //Ser inte om fungerar än - måste implementera hasChanges när något förändras först
  //Kod från föreläsning F16

  private class WindowExitHandler implements EventHandler<WindowEvent>{
    @Override
    public void handle(WindowEvent event){
      if(hasChanges){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Du har osparade ändringar i programmet. Är du säker på att du vill avsluta?");
        Optional<ButtonType> clicked = alert.showAndWait();
        if (clicked.isPresent() && clicked.get().equals(ButtonType.CANCEL)){
          event.consume();
        }
      }
    }
  }
  //Hanterare för när man försöker stänga fönstret utan att spara (varningsdialogruta för osparade ändringar)
  //Ta reda på vilken knapp som trycks på med showAndWait(), hittar button-typen som tryckdes på
  //Ser inte om fungerar än - måste implementera hasChanges när något förändras först
  //Kod från föreläsning F16

  private class LoadBackgroundHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent arg0){
      fileChooser.setInitialDirectory(new File("."));
      File pictureToLoad = fileChooser.showOpenDialog(stage);

      if (pictureToLoad != null){
        Image image = new Image(pictureToLoad.toURI().toString());
        backgroundMapView.setImage(image);
        hasChanges = true;
      }
      //Förändringar har utförts i programmet - när vi laddat in bild
    }
  }

  //Hanterare för att ladda in en bild till bakgrunden
  //Kod från föreläsning


  private class AddLocationHandler implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent event) {
      NewLocationForm newLocationform = new NewLocationForm();
      newLocationform.showAndWait().ifPresent(locationName -> {
        center.setOnMouseClicked(new ClickHandler(locationName));
      });
    }
  }

  //addLocation-knappen trycks på _ nytt formulär skapas, väntar på att användaren skriver in ett namn på location i formuläret och trycker ok, då skapas en Click-Handler, namn skickas med som argument
  //ClickHandler körs nästa gång användaren klickar på center (positionen i center används för att placera location)


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
              if (findPathMode) {
                new FindPathHandler(location).handle(e);            //Som du ser så är logiken likadan för de andra knapparna. Knapparna
              }                                                     //styr vad som händer vid musklicken på platsen.
                    });

            graph.add(location);                                        //Denna gör att location läggs in i ListGraph-klassen.
            center.getChildren().add(location);
            hasChanges = true;
            //lägger till location - programmet har ändrats

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

          connectionLines.removeIf(line -> {
            if (line.connects(location)){
              center.getChildren().remove(line);
              return true;
            }
            return false;
          });

          //Det här är en metod som går igenom listan men linjer och kollar på linjerna om den plats vi vill ta bort
          //finns på dem, i så fall tas linjen bort också.

          graph.remove(location);
          center.getChildren().remove(location);
          hasChanges = true;
          //tar bort location (samt dess linjer) - programmet har förändrats
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


        try {

          graph.connect(
                  firstSelected,
                  secondSelected,
                  connectLocationForm.getConnectionName(),
                  connectLocationForm.getConnectionLength()   // .connect ligger i ListGraph

          );

        // En metod kan inte returnera två stängar så det är lite svårare än tex NewLocationForm. Här hämtar vi instasvariablerna från klassen i stället, med
        // sånt som getConnectionName(). Det går att skapa ett nytt objekt för det vi behöver också men då behöver vi en ny klass.

         ConnectionLine line =
                 new ConnectionLine(
                         connectLocationForm.getConnectionName(),
                         connectLocationForm.getConnectionLength(),
                         firstSelected,
                         secondSelected);

         center.getChildren().add(line);
         center.getChildren().add(line.getLabel());
         //nu läggs även label till linjen till på center

         connectionLines.add(line);

         hasChanges = true;
         //connection skapas mellan två locations - förändring har skett i programmet

         //Här skapas linjen och så lägger vi till den till center. Den använder samma de två locations för att göra det.
         // linjen läggs även till i en lista så att vi ska kunna plocka bort den om noden tas bort.


        } catch (IllegalStateException e ) {
          showError ("Det finns redan en väg mellan platserna");
        }
        });

      firstSelected = null;
      secondSelected = null;
      connectMode = false;

      }
      //allt måste nollställas så att vi kan lägga till fler kopplingar.
    }
  }

  private void showError (String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("fel");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();

  }




  class FindPathHandler implements EventHandler<MouseEvent> {

    private Location location;

    public FindPathHandler(Location location) {
      this.location = location;
    }

    @Override
    public void handle(MouseEvent event) {

      if (pathStart == null) {
        pathStart = location;
        return;
      }

      if (pathEnd == null && location != pathStart) {
        pathEnd = location;

        FindPathForm findPathForm = new FindPathForm();

        findPathForm.showAndWait().ifPresent(choice -> {

          //Fram till hit är den rätt lik Connection-metoden den behöver att vi trycker på två olika location. Sen startas
          //FintPathForm upp och vi får välja DFS eller BFS se nedan:


          PathFinder<Location> pathFinder;

          if (choice.equals("DFS")) {
            pathFinder = new DFSPathFinder<>();

          } else {
            pathFinder = new BFSPathFinder<>();
          }

          Path<Location> path =
                  pathFinder.findPath(graph,pathStart,pathEnd);

          // Antingen ett objekt av DFS eller BFS skapas, utifrån klasserna som heter så, klasserna som objekten tillhör
          //har sedan metoden .findPath som vi sedan använder för att hitta en väg genom att vi kör metoden på objekten.
          // Vad vi får är en Path utifrån ListPath som vi sedan kan skriva ut nedan. Allt det här är egentligen "backend-logik" vi
          //höll på med det tidigare men använder det här.

          showPathResult(path);

        });

        pathStart = null;
        pathEnd = null;
        findPathMode = false;

      }
    }
  }

  private void showPathResult(Path<Location> path){
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setHeaderText(null);

    if (path ==null){
      alert.setContentText("Det finns ingen väg mellan platserna");
    } else {
        alert.setContentText(path.toString());
    }

    alert.showAndWait();
  }

// Det här är rutan som skriver ut pathen, den kan utvecklas.

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

  private class FindPathButtonHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent event){
      findPathMode = true;
      pathStart = null;
      pathEnd = null;
    }
  }

// Samma princip igen, en variabel som styr vad ett musklick ska reagera på skapas.
  //Sätter findPathMode till true när man trycker på knappen findPath

  public static void main (String[]args){
        launch(args);
      }
    }