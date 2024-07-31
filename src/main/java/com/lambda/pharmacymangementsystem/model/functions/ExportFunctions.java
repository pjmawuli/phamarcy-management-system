package com.lambda.pharmacymangementsystem.model.functions;

import com.lambda.pharmacymangementsystem.utils.DataExport;
import javafx.concurrent.Task;

import java.io.File;

public class ExportFunctions {

    public static Task<Void> exportSuppliers(File file) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                DataExport.exportToCSV(file.getPath(), "suppliers");
                return null;
            }
        };
    }

    public static Task<Void> exportDrugs(File file) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                DataExport.exportToCSV(file.getPath(), "drugs_view");
                return null;
            }
        };
    }

    public static Task<Void> exportPurchases(File file) {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                DataExport.exportToCSV(file.getPath(), "purchases_view");
                return null;
            }
        };
    }

}
