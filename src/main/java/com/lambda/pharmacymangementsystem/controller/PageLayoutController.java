package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Stack;

public class PageLayoutController {

    @FXML
    private Label dashboardLabel;

    @FXML
    private Label purchaseHistoryLabel;

    @FXML
    private Label inventoryLabel;

    @FXML
    private Pane componentPane;

    private Stack<Node> pageHistory = new Stack<>();

    @FXML
    public void initialize() {
        loadPage("/com/lambda/pharmacymangementsystem/view/fxml/dashboard.fxml", dashboardLabel);
    }

    public Label getDashboardLabel(){
        return dashboardLabel;
    }

    public void loadPage(String fxmlPath, Label activeLabel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Node page = loader.load();
            if (loader.getController() instanceof DashboardController) {
                ((DashboardController) loader.getController()).setPageLayoutController(this);
            }
            if (!componentPane.getChildren().isEmpty()) {
                pageHistory.push(componentPane.getChildren().get(0));
            }
            componentPane.getChildren().setAll(page);
            setActiveLabel(activeLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDashboardLabelClick() {
        loadPage("/com/lambda/pharmacymangementsystem/view/fxml/dashboard.fxml", dashboardLabel);
    }

    @FXML
    public void handlePurchaseHistoryLabelClick() {
        loadPage("/com/lambda/pharmacymangementsystem/view/fxml/purchase-history.fxml", purchaseHistoryLabel);
    }

    @FXML
    public void handleInventoryLabelClick() {
        loadPage("/com/lambda/pharmacymangementsystem/view/fxml/inventory.fxml", inventoryLabel);
    }

    public void loadPreviousPage() {
        if (!pageHistory.isEmpty()) {
            componentPane.getChildren().setAll(pageHistory.pop());
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

}