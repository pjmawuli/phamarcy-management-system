package com.lambda.pharmacymangementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddPurchasesController {

    @FXML
    private ComboBox<String> drugComboBox;

    @FXML
    private TextField quantityField;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private TextField customerNameField;

    @FXML
    public void Initialize() {
        // Initialize the combo box with drug names

    }

    private double getPriceForDrug(String drugName) {

        return 10.00;
    }

    @FXML
    public void updateTotalPrice(ActionEvent actionEvent) {

    }

    public void addPurchase(ActionEvent actionEvent) {

    }

    private void calculateTotalPrice() {

    }

    private void clearForm() {

    }
}
