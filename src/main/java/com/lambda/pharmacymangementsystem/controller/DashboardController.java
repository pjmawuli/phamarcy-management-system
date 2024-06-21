package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {

    @FXML
    private VBox dashboardComponents;

    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/components/overview-component.fxml"));
            VBox overviewComponent = loader.load();
            dashboardComponents.getChildren().add(overviewComponent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}