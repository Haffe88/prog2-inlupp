package se.su.inlupp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Gui extends Application {


  private Stage stage;
  private FileChooser fileChooser = new FileChooser();

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

    MenuBar menuBar = new MenuBar();
    Menu menu = new Menu("Meny");
    menuBar.getMenus().add(menu);

    MenuItem newFile = new MenuItem("Ny");
    MenuItem openFile = new MenuItem("Öppna");
    openFile.setOnAction(new OpenFileHandler());

    MenuItem loadBackground = new MenuItem("Ladda in bild");
    MenuItem saveFile = new MenuItem("Spara");
    MenuItem quit = new MenuItem("Avsluta");

    menu.getItems().addAll(newFile, openFile, loadBackground, saveFile, quit);

    //Menyn med alternativen i menyn

    VBox top = new VBox(0, menuBar);
    FlowPane bottom = new FlowPane();


    root.setTop(top);
    root.setBottom(bottom);

    //Sätta delarna i Borderpanen


    Scene scene = new Scene(root, 640, 480);
    stage.setScene(scene);
    stage.show();

    //Skapa och visa scenen
  }

  private class OpenFileHandler implements EventHandler<ActionEvent>{
    @Override
    public void handle(ActionEvent arg0){
      fileChooser.setInitialDirectory(new File("."));
      File fileToOpen = fileChooser.showOpenDialog(stage);
      System.out.println(fileToOpen);

    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
