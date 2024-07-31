package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DashboardEntity;
import com.lambda.pharmacymangementsystem.model.functions.DashboardFunctions;
import com.lambda.pharmacymangementsystem.utils.DataExport;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.function.Function;

public class DashboardController {
    @FXML
    public ProgressBar exportProgressBar;
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

            totalDrugsLabel.setText(Integer.toString(stats.getTotalDrugs()));
            totalSuppliersLabel.setText(Integer.toString(stats.getTotalSuppliers()));
            totalPurchasesLabel.setText(Integer.toString(stats.getTotalPurchases()));
            totalTodayPurchasesLabel.setText(Integer.toString(stats.getTotalTodayPurchases()));
            lowInStockLabel.setText(Integer.toString(stats.getTotalLowInStockDrugs()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // data exports
    public void exportSuppliers(ActionEvent actionEvent) {
        export("suppliers.csv", file -> exportSuppliersTask(file));
    }

    public void exportDrugs(ActionEvent actionEvent) {
        export("drugs.csv", file -> exportDrugsTask(file));
    }

    public void exportPurchases(ActionEvent actionEvent) {
        export("purchases.csv", file -> exportPurchasesTask(file));
    }

    private void export(String fileName, Function<File, Task<Void>> exportTask) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(fileName);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            Task<Void> task = exportTask.apply(file);
            exportProgressBar.progressProperty().bind(task.progressProperty());
            exportProgressBar.setVisible(true);

            task.setOnSucceeded(e -> {
                exportProgressBar.setVisible(false);
                // Show success message
                showAlert("Success", "Successfully exported data", Alert.AlertType.INFORMATION);
            });

            task.setOnFailed(e -> {
                exportProgressBar.setVisible(false);
                // Show error message
                showAlert("Success", "Failed to export data", Alert.AlertType.INFORMATION);

            });

            new Thread(task).start();
        }
    }

    public Task<Void> exportSuppliersTask(File file) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                DataExport.exportToCSV(file.getPath(), "suppliers");
                return null;
            }
        };
    }

    public Task<Void> exportDrugsTask(File file) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                DataExport.exportToCSV(file.getPath(), "drugs_view");
                return null;
            }
        };
    }

    public Task<Void> exportPurchasesTask(File file) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                DataExport.exportToCSV(file.getPath(), "purchases_view");
                return null;
            }
        };
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}