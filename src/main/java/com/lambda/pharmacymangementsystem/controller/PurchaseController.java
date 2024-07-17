package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.PurchaseViewEntity;
import com.lambda.pharmacymangementsystem.model.functions.PurchaseFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.List;

public class PurchaseController {


    @FXML
    public TableColumn<PurchaseViewEntity, Double> unitPriceColumn;
    @FXML
    private TableView<PurchaseViewEntity> datatable;
    @FXML
    private TableColumn<PurchaseViewEntity, String> purchaseCodeColumn;
    @FXML
    private TableColumn<PurchaseViewEntity, Integer> quantityColumn;
    @FXML
    private TableColumn<PurchaseViewEntity, Double> totalPriceColumn;

    @FXML
    private TableColumn<PurchaseViewEntity, String> customerNameColumn;

    @FXML
    private TableColumn<PurchaseViewEntity, LocalDateTime> createdAtColumn;

    @FXML
    private TableColumn<PurchaseViewEntity, String> drugNameColumn;

    @FXML
    private TableColumn<PurchaseViewEntity, String> drugCodeColumn;

    @FXML
    private TableColumn<PurchaseViewEntity, Double> drugPriceColumn;

    private ObservableList<PurchaseViewEntity> purchases = FXCollections.observableArrayList();


    public void initialize() {
        datatable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // bind tableview to observable list to allow for change listening
        datatable.setItems(purchases);

        // initialize columns
        purchaseCodeColumn.setCellValueFactory(new PropertyValueFactory<>("purchaseCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceColumn.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        drugNameColumn.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        drugCodeColumn.setCellValueFactory(new PropertyValueFactory<>("drugCode"));
//        drugPriceColumn.setCellValueFactory(new PropertyValueFactory<>("drugPrice"));

        // fetch all purchases on initial purchases view load
        loadPurchases();

    }

    private void loadPurchases() {
        // load data into table view
        try {
            List<PurchaseViewEntity> purchasesList = PurchaseFunctions.getAllPurchases();
            if (!purchasesList.isEmpty()) {
                purchases.addAll(purchasesList);
            }
        } catch (Exception e) {
            //  TODO: show error label with appropriate description
            System.out.println("Error loading purchases: " + e.getMessage());
        }
    }

    @FXML
    public void showAddPurchaseView() {
        try{
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/add-purchases-view.fxml"));
            Parent root = fxmlloader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

            purchases.clear();
            loadPurchases();
        } catch (Exception e) {
            e.printStackTrace();

        }
        // This method should be implemented in the MainController
    }
}
