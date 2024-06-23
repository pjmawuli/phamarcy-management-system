package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.utils.Purchase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PurchaseHistoryController {

    @FXML
    private TableView<Purchase> purchaseHistoryTable;

    @FXML
    private TableColumn<?, ?> drugCodeColumn;

    @FXML
    private TableColumn<?, ?> drugNameColumn;

    @FXML
    private TableColumn<?, ?> supplierColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Pagination pagination;

    @FXML
    private TextField searchBar;

    @FXML
    public void addPurchase() {
        // Implement the logic to add a purchase
    }

    public void initialize() {
        // Populate the TableView with data from the database
        ObservableList<Purchase> data = FXCollections.observableArrayList(); // replace with your data
        FilteredList<Purchase> filteredData = new FilteredList<>(data, p -> true);

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(purchase -> {
                // Implement your search logic here based on purchase code or supplier
                // Return true if the purchase matches the search criteria, false otherwise
                return true;
            });
        });

        int itemsPerPage = 15; // replace with the number of items you want per page
        int pageCount = (data.size() + itemsPerPage - 1) / itemsPerPage;
        pagination.setPageCount(Math.min(pageCount,5));
        pagination.setPageFactory(pageIndex -> {
            int fromIndex = pageIndex * itemsPerPage;
            int toIndex = Math.min(fromIndex + itemsPerPage, data.size());
            purchaseHistoryTable.setItems(FXCollections.observableArrayList(data.subList(fromIndex, toIndex)));
            return purchaseHistoryTable;
        });
    }
}