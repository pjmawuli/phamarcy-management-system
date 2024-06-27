package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DashboardEntity;
import com.lambda.pharmacymangementsystem.model.functions.DashboardFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {

    @FXML
    private Label totalDrugsLabel;
    @FXML
    private Label totalSuppliersLabel;
    @FXML
    private Label totalPurchasesLabel;
    @FXML
    private Label totalTodayPurchasesLabel;
    @FXML
    private Label lowInStockLabel;

    @FXML
    public void initialize() {
        updateDashboard();
    }

    private void updateDashboard() {
        try {
            DashboardEntity stats = DashboardFunctions.getDashboardSummary();
            if (stats != null) {
                totalDrugsLabel.setText(Integer.toString(stats.getTotalDrugs()));
                totalSuppliersLabel.setText(Integer.toString(stats.getTotalSuppliers()));
                totalPurchasesLabel.setText(Integer.toString(stats.getTotalPurchases()));
                totalTodayPurchasesLabel.setText(Integer.toString(stats.getTotalTodayPurchases()));
                lowInStockLabel.setText(Integer.toString(stats.getTotalLowInStockDrugs()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}