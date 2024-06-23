package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {

    @FXML
    private Button viewDrugsButton;

    @FXML
    private Button makePurchaseButton;

    @FXML
    private Button viewReportsButton;

    @FXML
    private VBox dashboardComponents;

    @FXML
    private Label dashboardLabel;

    private PageLayoutController pageLayoutController;

    public DashboardController() {
    }

    public DashboardController(PageLayoutController pageLayoutController) {
        this.pageLayoutController = pageLayoutController;
    }

    public void setPageLayoutController(PageLayoutController pageLayoutController) {
        this.pageLayoutController = pageLayoutController;
    }

    @FXML
    public void handleViewDrugs() {
        pageLayoutController.loadPage("/com/lambda/pharmacymangementsystem/view/fxml/components/view-drugs.fxml", pageLayoutController.getDashboardLabel());
    }

    @FXML
    public void handleMakePurchase() {
        // Implement your logic here
    }

    @FXML
    public void handleViewReports() {
        // Implement your logic here
    }

    @FXML
    public void initialize() {
        // Implement your logic here
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/components/overview-component.fxml"));
            VBox overviewComponent = loader.load();
            dashboardComponents.getChildren().add(overviewComponent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}