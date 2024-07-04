package com.lambda.pharmacymangementsystem.controller;

import com.lambda.pharmacymangementsystem.model.entities.DrugViewEntity;
import com.lambda.pharmacymangementsystem.model.functions.DrugFunctions;
import com.lambda.pharmacymangementsystem.utils.DataExport;
import com.lambda.pharmacymangementsystem.utils.TableActionButtons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DrugController {

    private final ObservableList<DrugViewEntity> drugs = FXCollections.observableArrayList();
    @FXML
    public TableColumn<DrugViewEntity, String> nameColumn;
    @FXML
    public TableColumn<DrugViewEntity, String> drugCodeColumn;
    @FXML
    public TableColumn<DrugViewEntity, Integer> quantityColumn;
    @FXML
    public TableColumn<DrugViewEntity, Double> priceColumn;
    @FXML
    public TableColumn<DrugViewEntity, String> supplierNameColumn;
    @FXML
    public TableColumn<DrugViewEntity, String> supplierLocationColumn;
    @FXML
    public TableColumn<DrugViewEntity, String> supplierContactColumn;
    @FXML
    private TableView<DrugViewEntity> datatable;
    @FXML
    private TableColumn<DrugViewEntity, Void> actionColumn;
    @FXML
    private Button addButton;

    // Initialization method
    @FXML
    private void initialize() {
        ControllerManager.getInstance().setDrugController(this);
        DataExport exportUtils = new DataExport();
        datatable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // bind tableview to observable list to allow for change listening
        datatable.setItems(drugs);

        // initialize columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        drugCodeColumn.setCellValueFactory(new PropertyValueFactory<>("drugCode"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierContactColumn.setCellValueFactory(new PropertyValueFactory<>("supplierContact"));
        supplierLocationColumn.setCellValueFactory(new PropertyValueFactory<>("supplierLocation"));

        // Setup action column using the utility method
        TableColumn<DrugViewEntity, Void> actionColumn = TableActionButtons.createActionColumn(
                this::handleEdit,
                this::handleDelete
        );
        datatable.getColumns().add(actionColumn);

        // load data into table view
        loadDrugs();

        // bind the action to the add drug button
//        addButton.setOnAction(actionEvent -> handleAdd());

        DataExport.exportToCSV("C:\\Users\\LAPTOP\\Documents\\projects\\school\\phamarcy-management-system\\drugs.csv", "drugs_view");
    }

    public void loadDrugs() {
        try {
            List<DrugViewEntity> drugsList = DrugFunctions.getAllDrugs();
            if (!drugsList.isEmpty()) {
                drugs.addAll(drugsList);
            }
        } catch (Exception e) {
            System.out.println("Error loading drugs: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void refreshDrugsTable() {
        drugs.clear(); // Clear the current list
        loadDrugs(); // Reload drugs from the database
    }

    private void handleAdd() {
        System.out.println(drugs.get(0).getDrugCode());
    }

    private void handleEdit(DrugViewEntity drug) {
        // Implement edit logic here
        System.out.println("Editing drug: " + drug.getDrugCode());
    }

    private void handleDelete(DrugViewEntity drug) {
        // Implement delete logic here
        System.out.println("Deleting drug: " + drug.getName());
    }


}
