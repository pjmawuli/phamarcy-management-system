package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import com.lambda.pharmacymangementsystem.model.functions.SupplierFunctions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        updateButton.setOnAction(actionEvent -> handleSave(actionEvent));
    }

    // load the current supplier data
    public void loadSupplier(SupplierEntity supplier) {
        this.supplier = supplier;

        // set the field values
        nameField.setText(supplier.getName());
        contactField.setText(Integer.toString(supplier.getContact()));
        locationField.setText(supplier.getLocation());
    }


    public void handleSave(ActionEvent actionEvent) {
        // set updated values
        supplier.setName(nameField.getText());
        supplier.setContact(Integer.parseInt(contactField.getText()));
        supplier.setLocation(locationField.getText());

        try {
            SupplierFunctions.updateOneSupplier(supplier.getId(), supplier);
            System.out.println("Supplier updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            // show error label
        }
    }
}
