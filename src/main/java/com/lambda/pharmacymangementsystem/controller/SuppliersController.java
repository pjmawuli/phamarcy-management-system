package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import

public class SuppliersController {

    @FXML
    private TextField searchBar;

    @FXML
    private TableView<SupplierEntity> suppliersTable;

    @FXML
    private TableColumn<SupplierEntity, Integer> colId;

    @FXML
    private TableColumn<SupplierEntity, String> colName;

    @FXML
    private TableColumn<SupplierEntity, String> colContact;

    @FXML
    private TableColumn<Supplier, String> colEmail;

    private ObservableList<Supplier> suppliersList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Sample data
        suppliersList.add(new Supplier(1, "Supplier A", "123456789", "a@example.com"));
        suppliersList.add(new Supplier(2, "Supplier B", "987654321", "b@example.com"));

        suppliersTable.setItems(suppliersList);
    }

    @FXML
    private void handleSearch() {
        String searchText = searchBar.getText().toLowerCase();
        if (searchText.isEmpty()) {
            suppliersTable.setItems(suppliersList);
            return;
        }

        ObservableList<Supplier> filteredList = FXCollections.observableArrayList();
        for (Supplier supplier : suppliersList) {
            if (supplier.getName().toLowerCase().contains(searchText) ||
                    supplier.getContact().contains(searchText) ||
                    supplier.getEmail().toLowerCase().contains(searchText)) {
                filteredList.add(supplier);
            }
        }
        suppliersTable.setItems(filteredList);
    }

    @FXML
    private void handleAddSupplier(ActionEvent event) {
        // Code to open add supplier form
    }

    @FXML
    private void handleRemoveSupplier(ActionEvent event) {
        Supplier selectedSupplier = suppliersTable.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            suppliersList.remove(selectedSupplier);
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        // Code to navigate back to the dashboard
    }
}
