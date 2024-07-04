package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class UpdateDrugController {

    @FXML
    public TextField nameField;

    @FXML
    public TextField quantityField;

    @FXML
    public TextField priceField;

    @FXML
    public ComboBox<SupplierEntity> supplierComboBox;

    private DrugEntity currentDrug;

    public void initialize() {
        loadSuppliers();
    }

    public void setDrug(DrugEntity drug) {
        this.currentDrug = drug;
        loadDrugDetails();
    }

    private void loadDrugDetails() {
        nameField.setText(currentDrug.getName());
        quantityField.setText(String.valueOf(currentDrug.getQuantity()));
        priceField.setText(String.valueOf(currentDrug.getPrice()));
        // Set the supplier in the combo box based on currentDrug's supplierId
    }

    private void loadSuppliers() {
        List<SupplierEntity> suppliers = new ArrayList<>();
        // Load suppliers from database and add to suppliers list
        supplierComboBox.getItems().setAll(suppliers);
    }

    @FXML
    private void handleSave() {
        if (validateInput()) {
            updateDrug();
            // Close the window or show confirmation message
        } else {
            // Show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }

    private boolean validateInput() {
        // TODO: Add validation logic
        return true;
    }

    private void updateDrug() {
        DrugEntity drug = new DrugEntity(nameField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()), supplierComboBox.getValue().getId());
        try {
            DrugFunctions.updateOneDrug(currentDrug.getId(), drug);

            DrugController drugController = ControllerManager.getInstance().getDrugController();
            if (drugController != null) {
                drugController.refreshDrugsTable();
            }
            // Show success message
        } catch (Exception e) {
            e.printStackTrace();
            // Show error message
        }
    }
}