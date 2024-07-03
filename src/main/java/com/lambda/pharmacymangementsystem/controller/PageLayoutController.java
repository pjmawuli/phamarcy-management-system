package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Stack;

public class PageLayoutController {

    @FXML
    private Label dashboardLabel;

    @FXML
    private Label purchaseHistoryLabel;

    @FXML
    private Label inventoryLabel;

    @FXML
    private Pane componentPane;

    // Stack to keep track of the navigation history in the application
private Stack<Node> pageHistory = new Stack<>();

/**
 * This method is called after all @FXML annotated members have been injected.
 * It's used to initialize the controller. In this case, it loads the dashboard page.
 */
@FXML
public void initialize() {
    loadPage("/com/lambda/pharmacymangementsystem/view/fxml/drug-manager.fxml", dashboardLabel);
}

/**
 * Getter for the dashboard label.
 *
 * @return the dashboard label
 */
public Label getDashboardLabel(){
    return dashboardLabel;
}

/**
 * This method is used to load a new page into the component pane.
 * It also keeps track of the navigation history by pushing the current page into the pageHistory stack.
 * If the loaded controller is an instance of DashboardController, it sets the page layout controller of it.
 * After loading the new page, it sets the active label.
 *
 * @param fxmlPath the path to the fxml file of the page to load
 * @param activeLabel the label to set as active
 */
public void loadPage(String fxmlPath, Label activeLabel) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Node page = loader.load();
        if (loader.getController() instanceof DashboardController) {
            ((DashboardController) loader.getController()).setPageLayoutController(this);
        }
        if (!componentPane.getChildren().isEmpty()) {
            pageHistory.push(componentPane.getChildren().get(0));
        }
        componentPane.getChildren().setAll(page);
        setActiveLabel(activeLabel);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @FXML
    public void handleDashboardLabelClick() {
        loadPage("/com/lambda/pharmacymangementsystem/view/fxml/drug-manager.fxml", dashboardLabel);
    }

    @FXML
    public void handlePurchaseHistoryLabelClick() {
        loadPage("/com/lambda/pharmacymangementsystem/view/fxml/purchase-history.fxml", purchaseHistoryLabel);
    }

    @FXML
    public void handleInventoryLabelClick() {
        loadPage("/com/lambda/pharmacymangementsystem/view/fxml/drug-details.fxml", inventoryLabel);
    }

    public void loadPreviousPage() {
        if (!pageHistory.isEmpty()) {
            componentPane.getChildren().setAll(pageHistory.pop());
        }
    }

    private void resetLabelStyles() {
        dashboardLabel.setStyle("");
        purchaseHistoryLabel.setStyle("");
        inventoryLabel.setStyle("");
    }

    private void setActiveLabel(Label activeLabel) {
        resetLabelStyles();
        activeLabel.setStyle("-fx-background-color: rgba(173,220,230,0.53);");
    }

}