package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class InventoryController {

    @FXML
    private ListView<String> formList;

    @FXML
    private StackPane formContainer;

    @FXML
    public void initialize() {
        formList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                Parent form = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/forms/" + newValue.toLowerCase().replace(" ", "-") + ".fxml"));
                formContainer.getChildren().setAll(form);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}