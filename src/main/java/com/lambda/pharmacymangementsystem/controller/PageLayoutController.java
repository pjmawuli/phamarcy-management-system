package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class PageLayoutController {

    @FXML
    private Label dashboardLabel;

    @FXML
    private Label purchaseHistoryLabel;

    @FXML
    private Label inventoryLabel;

    @FXML
    private TextField searchBar;

    @FXML
    private AnchorPane componentPane;

    @FXML
    public void initialize() {
        loadDashboard();
        dashboardLabel.setStyle("-fx-background-color: #ADD8E6;"); // Change the style of the dashboardLabel to indicate it's selected
    }

    public void loadDashboard() {
    try {
        AnchorPane dashboard = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/dashboard.fxml"));
        componentPane.getChildren().setAll(dashboard);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public void loadPurchaseHistory() {
        try {
            AnchorPane purchaseHistory = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/purchase-history.fxml"));
            componentPane.getChildren().setAll(purchaseHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadInventory() {
        try {
            AnchorPane inventory = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/inventory.fxml"));
            componentPane.getChildren().setAll(inventory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add methods to handle user interactions here...
}