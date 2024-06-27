package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentArea;

    @FXML
    private void initialize() {
        // Load initial view if needed
        loadView("dashboard-view.fxml");
    }

    @FXML
    private void handleDashboardButtonAction() {
        loadView("dashboard-view.fxml");
    }

    @FXML
    private void handleSuppliersButtonAction() {
        loadView("suppliers-view.fxml");
    }

    @FXML
    private void handleDrugsButtonAction() {
        loadView("drugs-view.fxml");
    }

    @FXML
    private void handlePurchasesButtonAction() {
        loadView("purchases-view.fxml");
    }

    @FXML
    private void handleReportsButtonAction() {
//        loadView("reports-view.fxml");
    }

    private void loadView(String viewName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/" + viewName));
            Node view = loader.load();
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
