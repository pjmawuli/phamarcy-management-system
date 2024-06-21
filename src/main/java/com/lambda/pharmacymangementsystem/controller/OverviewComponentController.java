package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OverviewComponentController {

    @FXML
    private Label totalSalesLabel;

    @FXML
    private Label totalPrescriptionsLabel;

    @FXML
    private Label totalInventoryLabel;

    public void initialize() {
        // Here you can initialize your labels with real data from your database
        // For example:
        // totalSalesLabel.setText(getTotalSales());
        // totalPrescriptionsLabel.setText(getTotalPrescriptions());
        // totalInventoryLabel.setText(getTotalInventory());
    }

    // Add methods to get data from your database here...
}