package com.lambda.pharmacymangementsystem.utils;

import com.lambda.pharmacymangementsystem.model.Database;
import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;

public class DataExport {

    public static void exportToCSV(String path, String source) {
        try (
                Connection conn = Database.connectDatabase();) {
            CopyManager copyManager = new CopyManager((BaseConnection) conn);
            // create file at specified path
            File file = new File(path);

            // create an output stream to write to the file
            FileOutputStream fos = new FileOutputStream(file);

            // write the data from the query to the file
            copyManager.copyOut("COPY (SELECT * FROM " + source + ") TO STDOUT WITH (FORMAT CSV, HEADER)", fos);
        } catch (Exception e) {
            System.out.println("Error exporting data to Excel File");
            e.printStackTrace();
        }
    }
}
