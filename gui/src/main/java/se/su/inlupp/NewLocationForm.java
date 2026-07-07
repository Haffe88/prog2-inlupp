package se.su.inlupp;

import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;



public class NewLocationForm extends Dialog<String> {

    private TextField name = new TextField();

    public NewLocationForm(){
        setTitle("Ny location");
        setHeaderText(null);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn:"), name);

        ButtonType placeraType =
                new ButtonType("Placera", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, placeraType);


        // Jag har behållit din ursprungliga struktur här ovan, men jag funderar på om vi
        // ska skapa knapparna själva istället för att använda DialogPane-knappar.
        // Det känns mer flexibelt och ligger närmare den struktur som används på
        // föreläsningarna, särskilt när vi vill ge knapparna egna namn och koppla
        // egen logik till dem.

        setResultConverter(button->{
            if (button == placeraType) {
                return name.getText();
            }
            return null;

        });

        // En andra förändring är att vi inte låter klassen skapa en location utan bara ett namn på en plats som vi sen
        // returnerar, för vi kan inte använda koordinater här på något bra sätt tror jag.

    }


        }

//Detta ska vara popup-fönstret till addLocation knappen - men fastnade i koden här.
