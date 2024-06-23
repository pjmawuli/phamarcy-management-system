package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ViewDrugsController {

    @FXML
    private TableView<?> drugsTable;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> drugCodeColumn;

    @FXML
    private TableColumn<?, ?> quantityColumn;

    @FXML
    private TableColumn<?, ?> priceColumn;

    @FXML
    private TableColumn<?, ?> supplierIdColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private Button backButton;

    private PageLayoutController pageLayoutController;

    public ViewDrugsController() {
    }

    public ViewDrugsController(PageLayoutController pageLayoutController) {
        this.pageLayoutController = pageLayoutController;
    }

    @FXML
    public void initialize() {
        // TODO: Load the drugs from the database and populate the table
    }

    @FXML
    public void handleSearch() {
        String searchText = searchField.getText();
        // TODO: Perform a search in the drugs table using the searchText
        // TODO: Update the table view with the search results
    }

    @FXML
    public void handleBack() {
        pageLayoutController.loadPreviousPage();
    }
}