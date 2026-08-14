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

        ButtonType placeType =
                new ButtonType("Placera", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, placeType);

        setResultConverter(button->{
            if (button == placeType) {
                if (name.getText().trim().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Platsens namn får inte vara tomt.");
                    alert.showAndWait();

                    return null;
                }

                return name.getText().trim();
            }
            return null;

        });
        // En andra förändring är att vi inte låter klassen skapa en location utan bara ett namn på en plats som vi sen
        // returnerar, för vi kan inte använda koordinater här på något bra sätt tror jag.
    }
}

//Detta ska vara popup-fönstret till addLocation knappen - men fastnade i koden här.
