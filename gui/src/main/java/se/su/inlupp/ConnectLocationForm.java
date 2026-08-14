package se.su.inlupp;

import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ConnectLocationForm extends Dialog<Boolean> {

    private TextField name = new TextField();
    private TextField length = new TextField();

    public ConnectLocationForm(){
        setTitle("Koppla ihop platser");
        setHeaderText(null);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Vägens namn"), name);
        grid.addRow(1, new Label ("Längd:"), length);

        ButtonType connectType =
                new ButtonType("Koppla", ButtonBar.ButtonData.OK_DONE);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, connectType);


        setResultConverter(button->{
            if (button == connectType) {

                try {
                    if (name.getText().trim().isEmpty()){
                        throw new IllegalArgumentException("Namnet får inte vara tomt");
                    }

                    int value = Integer.parseInt(length.getText());

                    if (value < 0) {
                        throw new IllegalArgumentException("Längden får inte vara negativ.");
                    }

                    return true;

                } catch (NumberFormatException e) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Längden måste vara ett heltal.");
                    alert.showAndWait();

                    return null;

                } catch (IllegalArgumentException e) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();

                    return null;
                }
            }
            return null;

        });

        // Det här ovan är felhantering

    }

    public String getConnectionName() {
        return name.getText();
    }

    public int getConnectionLength(){
        return Integer.parseInt(length.getText());
    }

}

