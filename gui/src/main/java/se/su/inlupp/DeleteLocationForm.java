package se.su.inlupp;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DeleteLocationForm extends Dialog<Boolean> {

    private Location location;

    public DeleteLocationForm(Location location){
        this.location = location;

        setTitle("Ändra location");
        setHeaderText(null);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Är du säker på att du vill ta bort  " + location.getName()));

        ButtonType deleteType =
                new ButtonType("Ja", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, deleteType);


        setResultConverter(button->{
            if (button == deleteType) {
                return true;
            }
            return null;

        });


    }



}
