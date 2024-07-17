package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugViewEntity;
import com.lambda.pharmacymangementsystem.model.entities.PurchaseEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.model.functions.PurchaseFunctions;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
    public void initialize() throws SQLException {
        List<DrugViewEntity> drugs = DrugFunctions.getAllDrugs();
        drugComboBox.setItems(FXCollections.observableArrayList(drugs.stream().map(DrugViewEntity::getName).collect(Collectors.toList())));
    }

    @FXML
    public void updateTotalPrice(ActionEvent event) {
        try {
            String selectedDrug = drugComboBox.getValue();
            double price = DrugFunctions.getDrugPriceByName(selectedDrug); // This method needs to be implemented in DrugFunctions
            int quantity = Integer.parseInt(quantityField.getText());
            double totalPrice = price * quantity;
            totalPriceLabel.setText(String.format("%.2f", totalPrice));
        } catch (NumberFormatException e) {
            totalPriceLabel.setText("Invalid quantity");
        } catch (SQLException e) {
            totalPriceLabel.setText("Error fetching price");
        }
    }

    @FXML
    public void addPurchase(ActionEvent event) {
        try {
            String selectedDrug = drugComboBox.getValue();
            int quantity = Integer.parseInt(quantityField.getText());
            double totalPrice = Double.parseDouble(totalPriceLabel.getText());
            String customerName = customerNameField.getText();

            if (selectedDrug == null || quantity <= 0 || customerName.isEmpty()) {
                System.out.println("All fields are required and quantity must be positive.");
                return;
            }

            //Retrieve drug id from the database
            int drugId = DrugFunctions.getDrugIdByName(selectedDrug); // This method needs to be implemented in DrugFunctions

            PurchaseEntity purchase = new PurchaseEntity();
            purchase.setPurchaseCode(PurchaseFunctions.getRecentPurchaseCode()); // Adjust this method to generate a new purchase code
            purchase.setQuantity(quantity);
            purchase.setTotalPrice(totalPrice);
            purchase.setCustomerName(customerName);
            purchase.setDrugId(drugId);

            PurchaseFunctions.addOnePurchase(purchase);

            System.out.println("Purchase added successfully.");
            drugComboBox.setValue(null);
            quantityField.clear();
            totalPriceLabel.setText("");
            customerNameField.clear();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}