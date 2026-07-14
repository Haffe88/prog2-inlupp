package se.su.inlupp;

import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class FindPathForm extends Dialog<String> {

    public FindPathForm() {
        setTitle("Hitta väg");
        setHeaderText(null);

        GridPane grid = new GridPane();

        ButtonType dfsType =
                new ButtonType("DFS", ButtonBar.ButtonData.OK_DONE);

        ButtonType bfsType =
                new ButtonType("BFS", ButtonBar.ButtonData.OTHER);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(dfsType, bfsType);


        setResultConverter(button -> {
            if (button == dfsType) {
                return "DFS";
            }

            if (button == bfsType) {
                return "BFS";
            }

            return null;

        });
    }
}

