package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    public BorderPane dashboardPane; // defining the border pane in the fxml file

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void showSuppliers() {
        loadView("com/lambda/pharmacymangementsystem/view/suppliers-view.fxml");
    }

    @FXML
    private void showDrugs() {
        loadView("com/lambda/pharmacymangementsystem/view/drugs-view.fxml");
    }

    @FXML
    private void showPurchaseHistory() {
        loadView("com/labda/pharmacymanagementsystem/view/purchase-history-view.fxml");
    }

    private void loadView(String fxmlpath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlpath));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) dashboardPane.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
