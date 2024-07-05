package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

import java.sql.SQLException;
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
    public TextField drugCodeField;

    @FXML
    public ComboBox<SupplierEntity> supplierComboBox;

    private DrugEntity currentDrug;

    public void initialize() throws SQLException {
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
        drugCodeField.setText(String.valueOf(currentDrug.getDrugCode()));//TODO: attend to it for clearance
        supplierComboBox.getSelectionModel().select(currentDrug.getSupplierId());
    }

    private void loadSuppliers() throws SQLException {
        List<SupplierEntity> suppliers = SupplierFunctions.getAllSuppliers(); // Assume this method fetches suppliers from the database
        supplierComboBox.getItems().setAll(suppliers);

        // Set a cell factory to display the supplier name in the ComboBox list
        supplierComboBox.setCellFactory(lv -> new ListCell<SupplierEntity>() {
            @Override
            protected void updateItem(SupplierEntity item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getName());
            }
        });

        // Set the ComboBox to display the supplier name when selected
        supplierComboBox.setConverter(new StringConverter<SupplierEntity>() {
            @Override
            public String toString(SupplierEntity supplier) {
                return supplier == null ? "" : supplier.getName();
            }

            @Override
            public SupplierEntity fromString(String string) {
                return supplierComboBox.getItems().stream().filter(supplier ->
                        supplier.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    @FXML
    private void handleUpdateAction() {
        if (validateInput()) {
            updateDrug();
        } else {
            showAlert("Validation Error", "Please fill in all fields correctly.", Alert.AlertType.ERROR);
        }
    }

    private boolean validateInput() {
        return !nameField.getText().isEmpty() && !quantityField.getText().isEmpty() && !priceField.getText().isEmpty() && !drugCodeField.getText().isEmpty() && supplierComboBox.getValue() != null;
    }

    private void updateDrug() {
        //include drug code in the update
        DrugEntity drug = new DrugEntity(nameField.getText(), drugCodeField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()), supplierComboBox.getValue().getId());
        try {
            DrugFunctions.updateOneDrug(currentDrug.getId(), drug);
            showAlert("Success", "Drug updated successfully.", Alert.AlertType.INFORMATION);

            // refresh the table view
            ControllerManager.getInstance().getDrugController().refreshDrugsTable();

        } catch (Exception e) {
            showAlert("Update Error", "An error occurred while updating the drug.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}