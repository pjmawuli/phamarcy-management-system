package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.Database;
import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class UpdateDrugController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox<SupplierEntity> supplierComboBox;

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
        codeField.setText(currentDrug.getDrugCode());
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
        try (Connection conn = Database.connectDatabase();//works when connectDatabase is made static.
             PreparedStatement st = conn.prepareStatement("UPDATE drugs SET name = ?, drug_code = ?, quantity = ?, price = ?, supplier_id = ? WHERE id = ?")) {

            st.setString(1, nameField.getText());
            st.setString(2, codeField.getText());
            st.setInt(3, Integer.parseInt(quantityField.getText()));
            st.setDouble(4, Double.parseDouble(priceField.getText()));
            st.setInt(5, supplierComboBox.getValue().getId());
            st.setInt(6, currentDrug.getId());

            st.executeUpdate();

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