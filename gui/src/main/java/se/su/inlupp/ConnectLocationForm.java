package se.su.inlupp;

import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConnectLocationForm extends Dialog<Boolean> {

    private TextField name = new TextField();
    private TextField weight = new TextField();
    //Ändra från vikt till avstånd (som vi skrev i planen vi skickade in)?

    public ConnectLocationForm(){
        setTitle("Koppla ihop platser");
        setHeaderText(null);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Vägens namn"), name);
        grid.addRow(1, new Label ("Vikt:"), weight);

        ButtonType connectType =
                new ButtonType("Koppla", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, connectType);


        setResultConverter(button->{
            if (button == connectType) {
                return true;
            }
            return null;

        });
    }

    public String getConnectionName() {
        return name.getText();
    }

    public int getConnectionWeight(){
        return Integer.parseInt(weight.getText());
    }

}

