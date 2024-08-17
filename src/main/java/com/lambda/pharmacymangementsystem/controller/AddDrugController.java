package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import com.lambda.pharmacymangementsystem.utils.Drug;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public class AddDrugController {

    private final HashMap<String, Integer> supplierMap = new HashMap<>();
    @FXML
    public TextField nameField;
    @FXML
    public TextField quantityField;
    @FXML
    public ComboBox<String> supplierComboBox;
    @FXML
    public TextField priceField;
    @FXML
    public Button addButton;

    @FXML
    public void initialize() {
        loadSuppliers();
        addButton.setOnAction(e -> addDrug());
    }

    private void loadSuppliers() {
        try {
            List<SupplierEntity> suppliers = SupplierFunctions.getAllSuppliers();
            for (SupplierEntity supplier : suppliers) {
                supplierComboBox.getItems().add(supplier.getName());
                supplierMap.put(supplier.getName(), supplier.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle error
        }
    }

    private Stage dialogStage;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void addDrug() {
        try {
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            int supplierId = supplierMap.get(supplierComboBox.getValue());
            String lastDrugCode = DrugFunctions.getRecentDrugCode();
            String drugCode = Drug.generateNextDrugCode(lastDrugCode);
            double price = Double.parseDouble(priceField.getText());

            DrugEntity newDrug = new DrugEntity(name, drugCode, quantity, price, supplierId);
            DrugFunctions.addOneDrug(newDrug);

            DrugController drugController = ControllerManager.getInstance().getDrugController();
            if (drugController != null) {
                drugController.refreshDrugsTable();
            }

            showSuccessDialog("Drug added successfully!");

            // Clear all inputs and fields
            clearInputs();

            // Close the dialog
            if (dialogStage != null) {
                dialogStage.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Show error message
            showErrorDialog("Please check your input and try again.");
        }
    }

    private void clearInputs() {
        nameField.clear();
        quantityField.clear();
        priceField.clear();
        supplierComboBox.getSelectionModel().clearSelection();
    }

    private void showSuccessDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);
        alert.getButtonTypes().setAll(ButtonType.OK);

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Close the dialog when OK is pressed
                if (dialogStage != null) {
                    dialogStage.close();
                }
            }
        });
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);
        alert.getButtonTypes().setAll(ButtonType.OK);
        alert.showAndWait();
    }
}
