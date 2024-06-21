package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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
        setActiveLabel(dashboardLabel);
        dashboardLabel.setOnMouseClicked(this::loadDashboard);
        purchaseHistoryLabel.setOnMouseClicked(this::loadPurchaseHistory);
        inventoryLabel.setOnMouseClicked(this::loadInventory);
    }

    public void loadDashboard() {
        try {
            AnchorPane dashboard = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/dashboard.fxml"));
            componentPane.getChildren().setAll(dashboard);
            setActiveLabel(dashboardLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDashboard(MouseEvent event) {
        loadDashboard();
    }

    public void loadPurchaseHistory(MouseEvent event) {
        try {
            VBox purchaseHistory = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/purchase-history.fxml"));
            componentPane.getChildren().setAll(purchaseHistory);
            AnchorPane.setTopAnchor(purchaseHistory, 0.0);
            AnchorPane.setRightAnchor(purchaseHistory, 0.0);
            AnchorPane.setBottomAnchor(purchaseHistory, 0.0);
            AnchorPane.setLeftAnchor(purchaseHistory, 0.0);
            setActiveLabel(purchaseHistoryLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadInventory(MouseEvent event) {
        try {
            AnchorPane inventory = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/inventory.fxml"));
            componentPane.getChildren().setAll(inventory);
            setActiveLabel(inventoryLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetLabelStyles() {
        dashboardLabel.setStyle("");
        purchaseHistoryLabel.setStyle("");
        inventoryLabel.setStyle("");
    }

    private void setActiveLabel(Label activeLabel) {
        resetLabelStyles();
        activeLabel.setStyle("-fx-background-color: rgba(173,220,230,0.53);");
    }

    // Add methods to handle user interactions here...
}