//package com.lambda.pharmacymangementsystem.utils;
//
//import com.lambda.pharmacymangementsystem.controller.DrugController;
//import com.lambda.pharmacymangementsystem.model.entities.DrugEntity;
//import com.lambda.pharmacymangementsystem.model.entities.SupplierEntity;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Dialog;
//import javafx.scene.control.DialogPane;
//
//import java.io.IOException;
//import java.util.List;
//
//public class DrugFormDialog extends Dialog<DrugEntity> {
//
//    private DrugController controller;
//
//    public DrugFormDialog(Drug drug, List<SupplierEntity> suppliers) {
//        setTitle("Add/Edit Drug");
//        setHeaderText(null);
//        setResizable(true);
//
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/add-drug-view.fxml"));
//            DialogPane dialogPane = new DialogPane();
//            dialogPane.setContent(loader.load());
//            setDialogPane(dialogPane);
//
//            controller = loader.getController();
//            controller.set(drug);
//            controller.setSuppliers(suppliers);
//
//            getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
//
//            setResultConverter(buttonType -> {
//                if (buttonType == ButtonType.OK) {
//                    return controller.getDrug();
//                }
//                return null;
//            });
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}