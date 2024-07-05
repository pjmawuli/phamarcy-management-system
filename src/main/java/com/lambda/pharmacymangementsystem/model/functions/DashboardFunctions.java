package com.lambda.pharmacymangementsystem.model.functions;

import com.lambda.pharmacymangementsystem.model.Database;
import com.lambda.pharmacymangementsystem.model.entities.DashboardEntity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DashboardFunctions {
    static Database db = new Database();

    /**
     * @return - DashoardEntity : a summary of all dashboard statistics in the pharmacy
     * @throws SQLException
     */
    public static DashboardEntity getDashboardSummary() throws SQLException {
//        DashboardEntity dashboardSummary = new DashboardEntity();
//        use `try with resources` to automatically release the resources when done
        try
                (
                        Connection conn = db.connectDatabase();
                        Statement st = conn.createStatement()
                ) {

//            add a batch query
            st.addBatch("SELECT COUNT(*) AS total_drugs FROM drugs");
            st.addBatch("SELECT COUNT(*) AS total_suppliers FROM suppliers");
            st.addBatch("SELECT COUNT(*) AS total_purchases FROM purchases");
            st.addBatch("SELECT COUNT(*) AS total_low_in_stock_drugs FROM drugs WHERE quantity = 0");
            st.addBatch("SELECT COUNT(*) AS total_drugs FROM drugs");

            // execute the batch query
            int[] output = st.executeBatch();
            List<Integer> stats = new ArrayList<>();

            //unpack into a list for efficient processing
            for (int val : output) {
                System.out.println(val);
                if (val < 0) return null;
                stats.add(val);
            }

            //process results

            return new DashboardEntity(
                    stats.get(0),
                    stats.get(1),
                    stats.get(2),
                    stats.get(3),
                    stats.get(4)
            );
        } catch (Exception e) {
//            TODO: handle errors properly
            System.out.println("Could not retrieve dashboard statistics");
            e.printStackTrace();
            throw e;
        }
    }
}
