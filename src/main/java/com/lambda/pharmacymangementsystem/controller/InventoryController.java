package com.lambda.pharmacymangementsystem.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class InventoryController {

    @FXML
    private ListView<String> formList;

    @FXML
    private StackPane formContainer;

   /**
 * Initializes the InventoryController.
 *
 * This method is annotated with @FXML, which means it gets called after all
 * FXML annotated fields are populated. In this method, a listener is added to
 * the selectedItemProperty of the formList ListView. This listener gets triggered
 * whenever the selected item in the formList changes.
 *
 * When the selected item changes, the method tries to load a form based on the
 * new selected item. The form is loaded from an FXML file located in the
 * "/com/lambda/pharmacymangementsystem/view/fxml/forms/" directory. The name of
 * the FXML file is determined by converting the new selected item to lower case
 * and replacing all spaces with "-". The loaded form is then set as the only
 * child of the formContainer StackPane.
 *
 * If the form cannot be loaded (for example, if the FXML file does not exist),
 * an IOException is caught and its stack trace is printed.
 */
@FXML
public void initialize() {
    formList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        try {
            Parent form = FXMLLoader.load(getClass().getResource("/com/lambda/pharmacymangementsystem/view/fxml/forms/" + newValue.toLowerCase().replace(" ", "-") + ".fxml"));
            formContainer.getChildren().setAll(form);
        } catch (IOException e) {
            e.printStackTrace();
        }
    });
}
}