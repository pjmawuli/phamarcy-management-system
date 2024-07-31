package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import com.lambda.pharmacymangementsystem.utils.DatabaseErrorSanitization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class UpdateSupplierController {

    @FXML
    public TextField nameField;
    @FXML
    public TextField contactField;
    @FXML
    public TextField locationField;
    @FXML
    Button updateButton;
    SupplierEntity supplier;

    @FXML
    public void initialize() {
    }

    // load the current supplier data
    public void loadSupplier(SupplierEntity supplier) {
        this.supplier = supplier;

        // set the field values
        nameField.setText(supplier.getName());
        contactField.setText(supplier.getContact());
        locationField.setText(supplier.getLocation());
    }

    public void handleUpdateAction(ActionEvent actionEvent) {
        // set updated values
        supplier.setName(nameField.getText());
        supplier.setContact(contactField.getText());
        supplier.setLocation(locationField.getText());

        if (!validateInput()) {
            showAlert("Validation Error", "Please fill in all fields correctly.", Alert.AlertType.ERROR);
            return;
        }

        try {
            SupplierFunctions.updateOneSupplier(supplier.getId(), supplier);
            showAlert("Success", "Supplier updated successfully.", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            String message = DatabaseErrorSanitization.getErrorMessage(e);
            showAlert("Could not update supplier", message, Alert.AlertType.ERROR);
        }
    }

    private boolean validateInput() {
        return !nameField.getText().isEmpty() && !contactField.getText().isEmpty()
                && !locationField.getText().isEmpty();
    }

    private void showAlert(String title, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
